import sbt._


object SimXProductionBuild extends SimXBuildBase{
  // version settings
  val usedJavaVersion = "1.8"
  val usedScalaVersion = "2.11.7"
  val projectName = "simx-production"

  override def rootProject =
    Some(basicexamples)


  /* Components */
  //AI
  lazy val atn		        = SimXComponent ( id = "atn", 	     	       base = file( "components/ai/atn")).
    dependsOn( editor )
  lazy val feature	      = SimXComponent ( id = "feature", 	         base = file( "components/ai/feature")).
    dependsOn( core )

  //Editor
  lazy val editor         = SimXComponent ( id = "editor", 		         base = file( "components/editor")).
    dependsOn( core )
  //IO
  lazy val vrpn           = SimXComponent ( id = "vrpn", 		           base = file( "components/io/vrpn")).
    dependsOn( core )

  lazy val j4k            = SimXComponent ( id = "j4k", 	 	           base = file( "components/io/j4k")).
    dependsOn( core )

  lazy val leapmotion     = SimXComponent ( id = "leapmotion",         base = file( "components/io/leapmotion")).
    dependsOn( core )

  //Physics
  lazy val jbullet 	      = SimXComponent ( id = "jbullet", 		       base = file( "components/physics/jbullet" )).
    dependsOn( core )
  
  //Renderer
  lazy val jvr            = SimXComponent ( id = "jvr", 		           base = file( "components/renderer/jvr")).
    dependsOn( core )

  lazy val gui            = SimXComponent ( id = "gui", 		           base = file( "components/renderer/gui")).
    dependsOn( core, jvr )

  //Sound
  lazy val lwjgl_sound    = SimXComponent ( id = "lwjgl_sound",        base = file( "components/sound/lwjgl-sound")).
    dependsOn( core )

  /* Applications*/
  lazy val basicexamples  = SimXApplication ( id = "examples-basic",     base = file( "applications/examples/basic")).
    dependsOn(core, jbullet, jvr, lwjgl_sound, editor, vrpn, gui).
    aggregate(core, jbullet, jvr, lwjgl_sound, editor, vrpn, gui)

  lazy val aiexamples  = SimXApplication ( id = "examples-ai",     base = file( "applications/examples/ai")).
    dependsOn(core, jbullet, jvr, lwjgl_sound, editor, vrpn, gui, atn, feature, j4k, leapmotion).
    aggregate(core, jbullet, jvr, lwjgl_sound, editor, vrpn, gui, atn, feature, j4k, leapmotion)

}

