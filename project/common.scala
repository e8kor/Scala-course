import sbt.Keys._
import sbt._

object common {

  def commonSettings = Seq(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "1.0.2",
      "org.scala-lang" % "scala-reflect" % (scalaVersion value),
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2",
      "org.scalatest" %% "scalatest" % "2.2.4" % "test",
      "org.scalacheck" %% "scalacheck" % "1.12.2" % "test",
      "org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % "test",
      "org.pegdown" % "pegdown" % "1.4.2" % "test",
      "org.mockito" % "mockito-core" % "2.0.7-beta",
      "org.scalaz" %% "scalaz-core" % "7.1.1",
      "org.hibernate.javax.persistence" % "hibernate-jpa-2.0-api" % "1.0.1.Final"
    )
  )

  def macrosSettings = Seq(
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full),

    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided"
    ) ++ (
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, scalaMajor)) if scalaMajor == 10 => Seq("org.scalamacros" %% "quasiquotes" % "2.1.0-M5")
        case _ => Nil
      }
      ),

    unmanagedSourceDirectories in Compile +=
      (sourceDirectory in Compile).value / "macros" / s"scala-${scalaBinaryVersion.value}"
  )

}