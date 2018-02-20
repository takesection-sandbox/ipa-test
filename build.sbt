import Dependencies._

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.12.4",
    name := "ipa-test",
    version := "0.0.1-SNAPSHOT",
    libraryDependencies ++= Seq(
      bcpkix
    )
  )