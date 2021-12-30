package com.mikeycaine.turbot.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.BackendScope
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import org.scalajs.dom.html.Div
import typings.std.global.document

import scala.scalajs.js

object WebGLContainer {

  case class State(hours: Int, minutes: Int, seconds: Int)

  case class Props(elemId: String)

  class Backend($: BackendScope[Props, State]) {

    var interval: js.UndefOr[js.timers.SetIntervalHandle] =
      js.undefined

    def tick =
      $.modState(s => State(s.hours, s.minutes, (s.seconds + 1) % 60))

//    def elementId = CallbackTo ( (p:Props) => {
//      println("Getting elemId...")
//      p.elemId
//    } )

    def start = Callback {
    //def start = elementId >> Callback { targetElementId: String => {
      interval = js.timers.setInterval(1000)(tick.runNow())

      //val e = elementId.runNow()

      val targetElementId = "xyz"

      val width = document.getElementById(targetElementId).clientWidth
      val height = dom.window.innerHeight.toInt

      println(s">> width is really $width")
      println(s">> height is really $height")

      val w = World(targetElementId, width, height)
//
//          try {
//            //World("yrmum", width, height)
//          } catch {
//            case ex: Throwable => println("FullsizeContainer failed in componentDidMount() " + ex)
//          }
    }

    def stop = Callback {
      interval foreach js.timers.clearInterval
      interval = js.undefined
    }

    def render(state: State, props: Props): VdomElement = {
      <.div(^.id := props.elemId,
        <.div("Hours: ", state.hours, " Minutes: ", state.minutes, " Seconds: ", state.seconds)
      )
    }
  }

//  val Component =
//    ScalaComponent.builder[Props]
//      .render_P(p => <.div("WEBGLCONTAINER ${p.elemId}"))
//      .build

  //val world = World("thisxasxs", 1000, 1000)

  val Component
  = ScalaComponent.builder[Props]
    .initialState(State(4,20,0))
    .backend(new Backend(_))
    //.renderBackend[Backend]
    .render_P(props => <.div(^.id := props.elemId))
    .componentDidMount(_.backend.start)
    .componentWillUnmount(_.backend.stop)
    .build

  def apply(elemId: String) = Component(Props(elemId))
}
