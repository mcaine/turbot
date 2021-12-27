package com.mikeycaine.turbot

// import com.mikeycaine.turbot.components.{TodoApp, SvgClock, Kitchen}

//import com.mikeycaine.turbot.Bumper
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@react class App extends StatelessComponent {
  type Props = Unit

  private val css = AppCSS

  //noinspection SpellCheckingInspection
  override def render(): ReactElement = {
    //noinspection SpellCheckingInspection
    div(className := "App")(
      header(className := "App-header")(
        h1(className := "App-title")("TURBOT")
      ),
      //TodoApp(),
      //SvgClock(10, 15, 0, true),
      //SvgClock(12, 0, 35, false),
      //SvgClock(4, 20, 60, false),
      //SvgClock(18, 45, 17, true),
      //SvgClock(23, 25, 11, true),
      //SvgClock(4, 53, 480, true),
      //SvgClock(4, 53, -480, true),
      //SvgClock(3, 30, 30, true),
      //Kitchen("abc", "def"),
      Bumper("This is a title")
    )
  }
}

