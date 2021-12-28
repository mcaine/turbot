package com.mikeycaine.turbot

import com.mikeycaine.turbot.components.TestScene
import com.mikeycaine.turbot.components.LittlePicture
import com.mikeycaine.turbot.components.WorldContainer
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.Date
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

    val date = new Date()
    val clockHours = date.getHours().toInt
    val clockMinutes = date.getMinutes().toInt
    val clockSeconds = date.getSeconds().toInt

    //noinspection SpellCheckingInspection
    div(className := "App")(
//      header(className := "App-header")(
//        h1(className := "App-title")("TURBOT")
//      ),
      //TodoApp(),
      //SvgClock(10, 15, 0, true),
      //SvgClock(12, 0, 35, false),
      //SvgClock(18, 45, 17, true),

      //SvgClock(clockHours, clockMinutes, clockSeconds, true),
      //Kitchen("abc", "def"),
      //TestScene("amazing", "12", "torus"),
      //LittlePicture("amazing", "12", "cubes"),
      //LittlePicture("amazing2", "12", "knitwear"),
      //LittlePicture("amazing3", "12", "torus"),
      //SvgClock(clockHours, clockMinutes, clockSeconds, true),
      WorldContainer("awesome", "11", "world")
    )
  }
}

