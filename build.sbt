import Dependencies._

val scalaTestVersion = "3.2.16"
val scalaCheckVersion = "1.15.4"

ThisBuild / scalaVersion := "3.3.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.fpinscala"
ThisBuild / organizationName := "scarlet"

addCommandAlias("ll", "projects")
addCommandAlias("cd", "project")
addCommandAlias("r", "run")
addCommandAlias("t", "test")
addCommandAlias("c", "console")

lazy val commonSettings = Seq(
  organization := "samsung",
  ThisBuild / turbo := true,
  Global / onChangedBuildSource := ReloadOnSourceChanges,
  watchTriggeredMessage := Watch.clearScreenOnTrigger,
  Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-s", "10"),
  resolvers ++= Resolver.sonatypeOssRepos("snapshots"),
  libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  libraryDependencies += "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test",
  libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.36" % "test",

  // munit test framework
  libraryDependencies += "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test,

  /** PPrint
    */
  libraryDependencies += "com.lihaoyi" %% "pprint" % "0.7.1",
  scalacOptions := Seq(
    "-deprecation", // emit warning and location for usages of deprecated APIs
    "-feature", // emit warning and location for usages of features that should be imported explicitly
    "-language:implicitConversions",
    "-language:postfixOps",
    "-language:higherKinds",
    "-Ykind-projector", // allow `*` as wildcard to be compatible with kind projector
    "-indent", // allow significant indentation.
    "-new-syntax", // require `then` and `do` in control expressions.
    "-print-lines", // show source code line numbers.
    "-unchecked", // enable additional warnings where generated code depends on assumptions
    "-Xfatal-warnings", // fail the compilation if there are any warnings
    "-Xmigration" // warn about constructs whose behavior may have changed since version
  )
)

lazy val root = (project in file("."))
  .settings(
    name := "FPinScala",
    libraryDependencies += munit % Test
  )
  .aggregate(
    basics,
    chapter1,
    chapter2,
    chapter3,
    chapter4,
    chapter5,
    chapter6,
    parsers,
    utils
  )

lazy val basics = (project in file("Basics"))
  .settings(commonSettings: _*)
  .settings(
    name := "Basics"
  )
  .dependsOn(utils)

lazy val chapter1 = (project in file("Chapter1"))
  .settings(commonSettings: _*)
  .settings(
    name := "Chapter1"
  )
  .dependsOn(utils)

lazy val chapter2 = (project in file("Chapter2"))
  .settings(commonSettings: _*)
  .settings(
    name := "Chapter2"
  )
  .dependsOn(utils)

lazy val chapter3 = (project in file("Chapter3"))
  .settings(commonSettings: _*)
  .settings(
    name := "Chapter3"
  )
  .dependsOn(utils)

lazy val chapter4 = (project in file("Chapter4"))
  .settings(commonSettings: _*)
  .settings(
    name := "Chapter4"
  )
  .dependsOn(utils)

lazy val chapter5 = (project in file("Chapter5"))
  .settings(commonSettings: _*)
  .settings(
    name := "Chapter5"
  )
  .dependsOn(utils)

lazy val chapter6 = (project in file("Chapter6"))
  .settings(commonSettings: _*)
  .settings(
    name := "Chapter6",
    console / initialCommands := """
      import fpinscala.chapter6.rng.*, RNG.*
      import fpinscala.chapter6.rand.*, Rand.*
      import fpinscala.chapter6.datatypes.state.*, State.*
      """
  )
  .dependsOn(utils)

lazy val parsers = (project in file("Parsers"))
  .settings(commonSettings: _*)
  .settings(
    name := "Parsers"
  )
  .dependsOn(utils)

lazy val utils = (project in file("Utils"))
  .settings(commonSettings: _*)
  .settings(
    name := "Utils"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
