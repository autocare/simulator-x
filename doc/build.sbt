scalaSource in Compile <<= baseDirectory(_ / "")

unmanagedSourceDirectories in Compile <++= baseDirectory { base =>
  Seq(
    base / "../core/src",
    base / "../components/physics/jbullet/src",
    base / "../components/renderer/jvr/src",
    base / "../components/sound/lwjgl-sound/src",
    base / "../components/remote/src",
    base / "../components/io/vrpn/src",
    base / "../components/io/tuio/src",
    base / "../applications/benchmarks/src",
    base / "../applications/basicexamples/src",
    base / "../applications/simthief/src",
    base / "../components/mmi/src"
  )
}

unmanagedJars in Compile <<= baseDirectory map { base => ((base ** "lib") ** "*.jar").classpath }

autoCompilerPlugins := true

addCompilerPlugin("org.scala-lang.plugins" % "continuations" % "2.9.2")

scalacOptions += "-P:continuations:enable"
