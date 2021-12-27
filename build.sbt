import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport.npmDevDependencies

enablePlugins(ScalaJSBundlerPlugin)
enablePlugins(ScalablyTypedConverterPlugin)

name := "turbot"

scalaVersion := "2.13.2"

stFlavour := Flavour.Slinky

stIgnore += "react-dom"
stIgnore += "react-proxy"

Compile / npmDependencies ++= Seq(
  "react" -> "16.13.1",
  "@types/react" -> "16.9.49",
  "react-dom" -> "16.13.1",
  "react-proxy" -> "1.1.8",
  "three" -> "0.120.1"


)

//npmDependencies in Compile += "react" -> "16.13.1"
//npmDependencies in Compile += "@types/react" -> "16.9.49"
//
//npmDependencies in Compile += "react-dom" -> "16.13.1"
//npmDependencies in Compile += "react-proxy" -> "1.1.8"
//
//npmDependencies in Compile += "three" -> "0.120.1"
//
//npmDependencies in Compile += "leaflet" -> "1.7.1"
//npmDependencies in Compile += "react-leaflet" -> "2.7.0"
//npmDependencies in Compile += "@types/react-leaflet" -> "2.5.2"

Compile /npmDevDependencies ++= Seq (
  "file-loader" -> "6.0.0",
  "style-loader" -> "1.2.1",
  "css-loader" -> "3.5.3",
  "html-webpack-plugin" -> "4.3.0",
  "copy-webpack-plugin" -> "5.1.1",
  //"@types/copy-webpack-plugin" -> "5.1.1",
  "webpack-merge" -> "4.2.2"
)
//npmDevDependencies in Compile += "file-loader" -> "6.0.0"
//npmDevDependencies in Compile += "style-loader" -> "1.2.1"
//npmDevDependencies in Compile += "css-loader" -> "3.5.3"
//npmDevDependencies in Compile += "html-webpack-plugin" -> "4.3.0"
//npmDevDependencies in Compile += "copy-webpack-plugin" -> "5.1.1"
//npmDevDependencies in Compile += "webpack-merge" -> "4.2.2"

libraryDependencies += "me.shadaj" %%% "slinky-web" % "0.6.5"
libraryDependencies += "me.shadaj" %%% "slinky-hot" % "0.6.5"

libraryDependencies += "org.scalatest" %%% "scalatest" % "3.1.1" % Test

scalacOptions += "-Ymacro-annotations"

webpack / version := "4.43.0"
startWebpackDevServer / version := "3.11.0"

webpackResources := baseDirectory.value / "webpack" * "*"

fastOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-fastopt.config.js")
fullOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-opt.config.js")
Test / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-core.config.js")

fastOptJS / webpackDevServerExtraArgs := Seq("--inline", "--hot")
fastOptJS / webpackBundlingMode := BundlingMode.LibraryOnly()

Test / requireJsDomEnv := true

addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS")

addCommandAlias("build", "fullOptJS::webpack")
