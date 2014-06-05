import sbt._
import Keys._

object SirisProductionBuild extends Build {

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "The Siris Team",
    version := "1.0",
    scalaVersion := "2.11.0"
  )

  val simxBase = file(".")
  //TODO: Check for thread safety in unusual cases(e.g. when compiling multiple SimXApplications at once)
  var workingDirectory: File = file(".")

  lazy val generateOntology = taskKey[Unit]("Generates the ontology for the current SimX application project.")
  lazy val ontologyGen = Project(
    id = "ontology-generator",
    base = file( "components/ontology/generating"),
    settings = buildSettings ++ Seq(
      generateOntology := {
        toError((runner in Compile).value.run(
          mainClass = mainClass.value.getOrElse(throw new Exception("No 'mainClass' defined in build.sbt.")),
          classpath = (fullClasspath in Compile).value.map(_.data),
          options = Seq(simxBase.getAbsolutePath, workingDirectory.getAbsolutePath), //program arguments
          log = streams.value.log)
        )
      }
  ))

  lazy val callOntoGen = taskKey[Unit]("Calls the ontology generation task.")

  lazy val core = Project(
    id = "core",
    base = file( "core"),
    settings = buildSettings ++ Seq(
      callOntoGen := {(generateOntology in ontologyGen).value},
      (compile in Compile) <<= (compile in Compile) dependsOn callOntoGen
  ))

  object SimXComponent {
  	def apply(id: String, base: File) = Project(id, base, settings = buildSettings)
  }

  lazy val setOntoDir =
    taskKey[Unit]("Sets the working directory for the ontology generation task to the projects baseDirectory.")

  object SimXApplication {
  	def apply(id: String, base: File) = Project(id, base, settings = buildSettings ++ Seq(
      setOntoDir := {workingDirectory = baseDirectory.value},
      (compile in Compile) <<= (compile in Compile) dependsOn setOntoDir
    ))
  }

  /* Components */
   //Editor
  lazy val editor         = SimXComponent ( id = "editor", 		         base = file( "components/editor")).
                              dependsOn( core ).aggregate( core )
  //IO
  lazy val vrpn           = SimXComponent ( id = "vrpn", 		           base = file( "components/io/vrpn")).
                              dependsOn( core ).aggregate( core )
  //Physics
  lazy val jbullet 	      = SimXComponent ( id = "jbullet", 		       base = file( "components/physics/jbullet" )).
                              dependsOn( core ).aggregate(core)
  //Renderer
  lazy val jvr            = SimXComponent ( id = "jvr", 		           base = file( "components/renderer/jvr")).
                              dependsOn( core ).aggregate( core )
  //Sound
  lazy val lwjgl_sound    = SimXComponent ( id = "lwjgl_sound",        base = file( "components/sound/lwjgl-sound")).
                              dependsOn( core ).aggregate( core )
   /* Applications*/
  lazy val basicexamples  = SimXApplication ( id = "examples-basic",     base = file( "applications/examples/basic")).
                              dependsOn(core, jbullet, jvr, lwjgl_sound, editor, vrpn).
                              aggregate(core, jbullet, jvr, lwjgl_sound, editor, vrpn)
  /* Documentation */
  lazy val doc            = SimXComponent ( id = "doc", 		           base = file( "doc")).
                              dependsOn( core, jbullet, jvr, lwjgl_sound, vrpn, basicexamples )
}