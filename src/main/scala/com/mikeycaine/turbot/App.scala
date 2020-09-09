package com.mikeycaine.turbot

import com.mikeycaine.turbot.components.{Kitchen, SvgClock}
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

  override def render(): ReactElement = {
    div(className := "App")(
      header(className := "App-header")(
        h1(className := "App-title")("TURBOT")
      ),
      SvgClock(18, 45, 0),
      SvgClock(12, 0, 0),
      SvgClock(4, 20, 0),
      SvgClock(18, 45, 0),
      Kitchen("abc", "def"),
      Bumper("BUMPA BOYZ")
    )
  }
}

