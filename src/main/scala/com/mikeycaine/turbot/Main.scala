package com.mikeycaine.turbot

import com.mikeycaine.turbot.components.WebGLContainer
import japgolly.scalajs.react.component.Scala.BackendScope
import org.scalajs.dom
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.{LinkingInfo, js}

@JSImport("resources/index.css", JSImport.Default)
@js.native
object IndexCSS extends js.Object

object Main {
  val css = IndexCSS

  @JSExportTopLevel("main")
  def main(): Unit = {
//    if (LinkingInfo.developmentMode) {
//      hot.initialize()
//    }

    val rootElement = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

//    val Timer = ScalaComponent.builder[Unit]
//      .initialState(State(0))
//      .renderBackend[Backend]
//      .componentDidMount(_.backend.start)
//      .componentWillUnmount(_.backend.clear)
//      .build

    WebGLContainer("xyz").renderIntoDOM(rootElement)

    //ReactDOM.render(App(), container)
  }
}

//case class State(secondsElapsed: Long)
//
//class Backend($: BackendScope[Unit, State]) {
//  var interval: js.UndefOr[js.timers.SetIntervalHandle] =
//    js.undefined
//
//  def tick =
//    $.modState(s => State(s.secondsElapsed + 1))
//
//  def start = Callback {
//    interval = js.timers.setInterval(1000)(tick.runNow())
//  }
//
//  def clear = Callback {
//    interval foreach js.timers.clearInterval
//    interval = js.undefined
//  }
//
//  def render(s: State) = {
//    <.div(
//      <.div("Seconds elapsed: ", s.secondsElapsed),
//      WebGLContainer("dasd")
//    )
//  }
//}




