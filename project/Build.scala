import sbt._
import Keys._

object SirisProductionBuild extends Build {
  
  object BuildSettings {
    val buildSettings = Defaults.defaultSettings ++ Seq(
      organization := "The Siris Team",
      version := "1.0",
      scalaVersion := "2.10.2"
    )
  }
  
  object SimXProject {
  	def apply(id: String, base: File) = Project(id = id, base = base, settings = BuildSettings.buildSettings)
  }
  
  lazy val core 	      = SimXProject ( id = "core",		    base = file( "core" ))
  lazy val ontologygen    = SimXProject ( id = "ontologygen",   base = file( "components/ontology/generating"))
  lazy val jbullet 	      = SimXProject ( id = "jbullet", 		base = file( "components/physics/jbullet" )) 	dependsOn( core )
  lazy val jvr            = SimXProject ( id = "jvr", 		    base = file( "components/renderer/jvr"))    	dependsOn( core )
  lazy val lwjgl_sound    = SimXProject ( id = "lwjgl_sound", 	base = file( "components/sound/lwjgl-sound"))   dependsOn( core )
  lazy val vrpn           = SimXProject ( id = "vrpn", 		    base = file( "components/io/vrpn")) 		    dependsOn( core )
  lazy val editor         = SimXProject ( id = "editor", 		base = file( "components/editor")) 	   	        dependsOn( core )
  lazy val basicexamples  = SimXProject ( id = "basicexamples",	base = file( "applications/basicexamples"))	    dependsOn( core, jbullet, jvr, editor, ontologygen, lwjgl_sound )
  lazy val simthief	      = SimXProject ( id = "simthief",		base = file( "applications/simthief"))		    dependsOn( core, jbullet, jvr, editor, vrpn, lwjgl_sound )
  lazy val doc            = SimXProject ( id = "doc", 		    base = file( "doc")) 		                    dependsOn( core, jbullet, jvr, lwjgl_sound, vrpn, editor, basicexamples, simthief ) 
}
	
