package com.mikeycaine.turbot.components

import org.scalajs.dom

import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{className, div, id}

import typings.std.global.document

@react
class FullsizeWorldContainer extends Component {

  case class Props(elemId: String, name: String, drawingName: String)
  case class State(count: Long)

  override def initialState: State = State(31337L)

  override def render(): ReactElement = {
    div(className := "fullsize_container")(
      div(id := props.elemId)
    )
  }

  override def componentDidMount(): Unit = {

    val width = document.getElementById(props.elemId).clientWidth - 5
    val height = dom.window.innerHeight.toInt - 5

    println(s">> width is $width")
    println(s">> height is $height")

    try {
      World(props.elemId, width, height)

    } catch {
      case ex: Throwable => println("FullsizeContainer failed in componentDidMount() " + ex)
    }
  }
}