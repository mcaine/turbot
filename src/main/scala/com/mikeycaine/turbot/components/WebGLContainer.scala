package com.mikeycaine.turbot.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.BackendScope
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import org.scalajs.dom.html.Div
import typings.std.global.document

import scala.scalajs.js

object WebGLContainer {

  case class State(hours: Int, minutes: Int, seconds: Int, elemId: String)

  case class Props(elemId: String, width: Int, height: Int)

  class Backend($: BackendScope[Unit, State]) {
    var interval: js.UndefOr[js.timers.SetIntervalHandle] =
      js.undefined

    def tick =
      $.modState(s => State(s.hours, s.minutes, (s.seconds + 1) % 60, s.elemId))

    def start = Callback {
      interval = js.timers.setInterval(1000)(tick.runNow())

      val targetElementId = "yrmum"

      val width = document.getElementById(targetElementId).clientWidth
      val height = dom.window.innerHeight.toInt

      println(s">> width is $width")
      println(s">> height is $height")

          try {
            World("yrmum", width, height)
          } catch {
            case ex: Throwable => println("FullsizeContainer failed in componentDidMount() " + ex)
          }
    }

    def clear = Callback {
      interval foreach js.timers.clearInterval
      interval = js.undefined
    }

    def render(state: State): VdomTagOf[Div] = {
      <.div(^.id := state.elemId,
        <.div("Hours: ", state.hours, " Minutes: ", state.minutes, " Seconds: ", state.seconds)
      )
    }
  }

//  val Component =
//    ScalaComponent.builder[Props]
//      .render_P(p => <.div("WEBGLCONTAINER ${p.elemId}"))
//      .build

  val Component
  = ScalaComponent.builder[Unit]
    .initialState(State(4,20,0, "yrmum"))
    .renderBackend[Backend]
    .componentDidMount(_.backend.start)
    .componentWillUnmount(_.backend.clear)
    .build

  def apply(elemId: String) = Component()
}
