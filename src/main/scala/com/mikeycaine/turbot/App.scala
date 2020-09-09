package com.mikeycaine.turbot

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import com.mikeycaine.turbot.components.SvgClock
import com.mikeycaine.turbot.components.Kitchen

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@react class App extends StatelessComponent {
  type Props = Unit

  private val css = AppCSS

  def render() = {
    div(className := "App")(
      header(className := "App-header")(
        h1(className := "App-title")("TURBOT")
      ),
      SvgClock(4, 47, 0),
      Kitchen("abc", "def"),
      //Bumper("BUMPA BOYZ")
    )
  }
}

