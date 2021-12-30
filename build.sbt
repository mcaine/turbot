import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport.npmDevDependencies

enablePlugins(ScalaJSBundlerPlugin)
enablePlugins(ScalablyTypedConverterPlugin)

name := "turbot"

scalaVersion := "2.13.2"

stFlavour := Flavour.Slinky

stIgnore += "react-dom"
stIgnore += "react-proxy"

Compile / npmDependencies ++= Seq(
  "react" -> "17.0.2",
  "@types/react" -> "17.0.38",
  "react-dom" -> "17.0.2",
  "react-proxy" -> "1.1.8",
  //"react-router"-> "6.2.0",
  //"react-router-dom" -> "6.2.1",
  //"@types/react-router-dom" -> "5.3.2",
  "three" -> "0.135.0",
  "@types/three" -> "0.135.0"
)

Compile / npmDevDependencies ++= Seq (
  "file-loader" -> "6.0.0",
  "style-loader" -> "1.2.1",
  "css-loader" -> "3.5.3",
  "html-webpack-plugin" -> "4.3.0",
  "copy-webpack-plugin" -> "5.1.1",
  "webpack-merge" -> "4.2.2"
)

libraryDependencies ++= Seq(
  "me.shadaj" %%% "slinky-web" % "0.7.0",
  "me.shadaj" %%% "slinky-hot" % "0.7.0",
  //"me.shadaj" %%% "slinky-react-router" % "0.7.0",
  //"com.github.japgolly.scalajs-react" %%% "extra" % "2.0.0",
  "org.scalatest" %%% "scalatest" % "3.1.1" % Test
)

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
