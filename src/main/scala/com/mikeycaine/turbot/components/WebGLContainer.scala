package com.mikeycaine.turbot.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.BackendScope
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import org.scalajs.dom.html.Div
import typings.std.global.document

import scala.scalajs.js

object WebGLContainer {

 //case class State()

  case class Props(elemId: String)

  class Backend($: BackendScope[Props, Unit]) {

    var elemId = $.props.runNow().elemId
    var world: World = null

    def start = Callback {
      val width = document.getElementById(elemId).clientWidth
      val height = dom.window.innerHeight.toInt

      println(s">> width is really $width")
      println(s">> height is really $height")

      val world = World(elemId, width, height)
    }

    def stop = Callback {
      if (world != null) world.stop
    }
  }

  val Component
  = ScalaComponent.builder[Props]
 //   .initialState(State(4,20,0))
    .backend(new Backend(_))
    .render_P(props => <.div(^.id := props.elemId))
    .componentDidMount(_.backend.start)
    .componentWillUnmount(_.backend.stop)
    .build

  def apply(elemId: String) = Component(Props(elemId))
}
