package com.mikeycaine.turbot.components

import org.scalajs.dom
import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.{ReactElement, ReactRef}
import slinky.web.html.{className, div, id, p}
import typings.std.global.document
import typings.three.webGLRendererMod.{WebGLRenderer, WebGLRendererParameters}

@react
class SmallWorldContainer extends Component {

  case class Props(elemId: String, name: String, drawingName: String)
  case class State(count: Long)

  override def initialState: State = State(31337L)

  override def render(): ReactElement = {
    div(className := "world_container")(
      div(id := props.elemId)
    )
  }

  override def componentDidMount(): Unit = {

//    val width = document.getElementById(props.elemId).clientWidth - 5
//    val height = dom.window.innerHeight.toInt - 5

    val width = 500
    val height = 500

    println(s">> width is $width")
    println(s">> height is $height")

    try {
      World(props.elemId, width, height)

    } catch {
      case ex: Throwable => println("WorldContainer failed in componentDidMount() " + ex)
    }
  }
}
