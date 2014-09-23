scalaSource in Compile <<= baseDirectory(_ / "")

unmanagedSourceDirectories in Compile <++= baseDirectory { base =>
  Seq(
    base / "../core/src",
    base / "../components/editor/src",
    base / "../components/io/vrpn/src",       
    base / "../components/physics/jbullet/src",
    base / "../components/renderer/jvr/src",
    base / "../components/sound/lwjgl-sound/src",
    base / "../applications/examples/basic/src"
  )
}

resolvers += "snapshots-repo" at "https://oss.sonatype.org/content/repositories/snapshots"

unmanagedJars in Compile <<= baseDirectory map { base => ((base ** "lib") ** "*.jar").classpath }

autoCompilerPlugins := true

addCompilerPlugin("org.scala-lang.plugins" % "scala-continuations-plugin_2.11.2" % "1.0.2")

scalacOptions += "-P:continuations:enable"

ivyXML := scala.xml.XML.load( documentation.base + "/ivy.xml" ) \ "dependencies"
