import sbt._


object SimXProductionBuild extends SimXBuildBase{
  // version settings
  val usedJavaVersion = "1.7"
  val usedScalaVersion = "2.11.2"
  val projectName = "simx-production"

  override def rootProject =
    Some(basicexamples)


  /* Components */
  //Editor
  lazy val editor         = SimXComponent ( id = "editor", 		         base = file( "components/editor")).
    dependsOn( core )
  //IO
  lazy val vrpn           = SimXComponent ( id = "vrpn", 		           base = file( "components/io/vrpn")).
    dependsOn( core )

  //Physics
  lazy val jbullet 	      = SimXComponent ( id = "jbullet", 		       base = file( "components/physics/jbullet" )).
    dependsOn( core )
  
  //Renderer
  lazy val jvr            = SimXComponent ( id = "jvr", 		           base = file( "components/renderer/jvr")).
    dependsOn( core )

  //Sound
  lazy val lwjgl_sound    = SimXComponent ( id = "lwjgl_sound",        base = file( "components/sound/lwjgl-sound")).
    dependsOn( core )

  /* Applications*/
  lazy val basicexamples  = SimXApplication ( id = "examples-basic",     base = file( "applications/examples/basic")).
    dependsOn(core, jbullet, jvr, lwjgl_sound, editor, vrpn).
    aggregate(core, jbullet, jvr, lwjgl_sound, editor, vrpn)

}

