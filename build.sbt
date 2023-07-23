import Dependencies._

val scalaTestVersion = "3.2.16"
val scalaCheckVersion = "1.15.4"

Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / scalaVersion := "3.3.0" // "3.3.0", "2.13.11"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.fpinscala"
ThisBuild / organizationName := "scarlet"
ThisBuild / watchTriggeredMessage := Watch.clearScreenOnTrigger
ThisBuild / watchBeforeCommand := Watch.clearScreen
ThisBuild / watchForceTriggerOnAnyChange := true
Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-s", "10")

addCommandAlias("ll", "projects")
addCommandAlias("cd", "project")
addCommandAlias("r", "run")
addCommandAlias("t", "test")
addCommandAlias("c", "console")

lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test",
    // "org.scalatestplus" %% "scalacheck-1-17" % "3.2.16.0" % "test",
    // "org.scalatestplus" %% "junit-4-13" % "3.2.16.0" % "test",
    // "org.slf4j" % "slf4j-simple" % "1.7.36" % "test",
    "com.lihaoyi" %% "pprint" % "0.7.1",
    // munit test framework
    "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test
  ),
  scalacOptions := Seq(
    "-deprecation", // emit warning and location for usages of deprecated APIs
    "-feature", // emit warning and location for usages of features that should be imported explicitly
    "-language:implicitConversions",
    "-language:postfixOps",
    "-language:higherKinds",
    "-Ykind-projector", // allow `*` as wildcard to be compatible with kind projector
    // "-explain", // explain errors in more detail
    // "-explain-types", // explain type errors in more detail
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
    parser,
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
    name := "Chapter1",
    console / initialCommands := """
      import fpinscala.intro.*, Cafes.*
      import pprint.*
      """
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
    name := "Chapter4",
    console / initialCommands := """
      import fpinscala.datatype.*
      import Option.*
      """
  )
  .dependsOn(utils)

lazy val chapter5 = (project in file("Chapter5"))
  .settings(commonSettings: _*)
  .settings(
    name := "Chapter5",
    console / initialCommands := """
      import fpinscala.datatypes.*, LazyList.*
      """
  )
  .dependsOn(utils)

lazy val chapter6 = (project in file("Chapter6"))
  .settings(commonSettings: _*)
  .settings(
    name := "Chapter6",
    console / initialCommands := """
      import fpinscala.rng.*
      import fpinscala.rand.*
      import fpinscala.datatypes.*, State.*
      val rng = SimpleRNG(42)
      """
  )
  .dependsOn(utils)

lazy val parser = (project in file("Parser"))
  .settings(commonSettings: _*)
  .settings(
    name := "Parser"
  )
  .dependsOn(utils, chapter6)

lazy val utils = (project in file("Utils"))
  .settings(commonSettings: _*)
  .settings(
    name := "Utils"
  )
