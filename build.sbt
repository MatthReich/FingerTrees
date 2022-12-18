val scala3Version = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "FingerTrees",
    version := "1.0.0",
    scalaVersion := scala3Version,

    /** test dependencies */
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.14" % "test",
      "org.scalactic" %% "scalactic" % "3.2.14",
       "org.scalatestplus" %% "mockito-4-6" % "3.2.14.0" % "test"
    )
  )

coverageExcludedPackages := "<empty>;Main.scala"
