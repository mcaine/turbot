package com.mikeycaine.turbot.components

import org.scalajs.dom
import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{className, div, id, p}
import typings.std.global.document
import typings.three.webGLRendererMod.{WebGLRenderer, WebGLRendererParameters}

import scala.util.Random

@react
class LittlePicture extends Component {
  case class Props(elemId: String, name: String, drawingName: String)
  case class State(count: Long)

  override def initialState: State = State(31337L)

  override def render(): ReactElement = {
    div(className := "test_scene")(
//      p(className := "test_scene")(
//        "Little Picture: " + props.name
//      ),
      div(id := props.elemId)
    )
  }

  override def componentDidMount(): Unit = {

    val width = Math.min(1500, document.getElementById(props.elemId).clientWidth - 5)
    //val width = 1000
    val height = 600

    try {
      val renderer: WebGLRenderer = WebGL.webGLRenderer(props.elemId, width, height)
      val scene = SceneWithLights.anotherScene()
      val camera = DefaultCamera.createCamera(width, height)

      if (props.drawingName.equals("cubes")) {
        CubesAndStuff(renderer, scene, camera).doDrawing()
      }

      if (props.drawingName.equals("knitwear")) {
        Knitwear(renderer, scene, camera).doDrawing()
      }

      if (props.drawingName.equals("torus")) {
        TorusKnotDemo(renderer, scene, camera).doDrawing()
      }
    } catch {
      case ex: Throwable => println("Failed in componentDidMount() " + ex)
    }
  }
}
