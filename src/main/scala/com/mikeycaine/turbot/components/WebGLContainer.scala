package com.mikeycaine.turbot.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.BackendScope
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import org.scalajs.dom.html.Div
import typings.std.global.document

object WebGLContainer {

  case class Props(elemId: String)

  class Backend($: BackendScope[Props, Unit]) {

    var elemId = $.props.runNow().elemId
    var world: World = null

    def start = Callback {
      //val width = document.getElementById(elemId).clientWidth
      //val height = dom.window.innerHeight.toInt

      val width = 1500
      val height = 900

      //println(s">> width is actually $width")
      //println(s">> height is truly $height")

      world = World(elemId, width, height)
    }

    def stop = Callback {
      if (world != null) world.stop
    }
  }

  val Component
  = ScalaComponent.builder[Props]
    .backend(new Backend(_))
    .render_P(props => <.div(^.id := props.elemId))
    .componentDidMount(_.backend.start)
    .componentWillUnmount(_.backend.stop)
    .build

  def apply(elemId: String) = Component(Props(elemId))
}
