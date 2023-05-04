ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "SCALA"
  )
libraryDependencies += "org.scalafx" %% "scalafx" % "16.0.0-R25"
javaOptions += "--add-modules=javafx.controls,javafx.fxml"

libraryDependencies ++= {
  // Determine OS version of JavaFX binaries
  lazy val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux") => "linux"
    case n if n.startsWith("Mac") => "mac"
    case n if n.startsWith("Windows") => "win"
    case _ => throw new Exception("Unknown platform!")
  }
  Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map(m => "org.openjfx" % s"javafx-$m" % "18.0.1" classifier osName)
}
libraryDependencies ++= Seq(
  "org.openjfx" % "javafx-controls" % "17",
  "org.openjfx" % "javafx-fxml" % "17",
  "org.openjfx" % "javafx-base" % "17",
  "org.openjfx" % "javafx-graphics" % "17"
)
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"


