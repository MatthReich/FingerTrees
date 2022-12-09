val scala3Version = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "FingerTrees",
    version := "1.0.0",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
  )

coverageExcludedPackages := "Main.scala"
