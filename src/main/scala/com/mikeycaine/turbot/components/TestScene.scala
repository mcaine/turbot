package com.mikeycaine.turbot.components

import org.scalajs.dom
import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{className, div, id, p}
import typings.std.global.document
import typings.three.colorMod.Color
import typings.three.directionalLightMod.DirectionalLight
import typings.three.fontLoaderMod.FontLoader
import typings.three.fontMod.Font
import typings.three.geometriesMod.BoxGeometry
import typings.three.meshLambertMaterialMod.{MeshLambertMaterial, MeshLambertMaterialParameters}
import typings.three.meshMod.Mesh
import typings.three.perspectiveCameraMod.PerspectiveCamera
import typings.three.sceneMod.Scene
import typings.three.textGeometryMod.{TextBufferGeometry, TextGeometryParameters}
import typings.three.webGLRendererMod.{WebGLRenderer, WebGLRendererParameters}

import scala.scalajs.js

@react
class TestScene extends Component {
  case class Props(elemId: String, name: String)
  case class State(count: Long)
  //override type Snapshot = this.type

  override def initialState: State = State(31337L)



  override def render(): ReactElement = {
    div(className := "test_scene")(
      p(className := "test_scene")(
        "Test Scene: " + props.name
      ),
      div(id := props.elemId)
    )
  }

  override def componentDidMount(): Unit = {

    val width = document.getElementById(props.elemId).clientWidth
    val height = dom.window.innerHeight.toInt

    println(s">> width is $width")
    println(s">> height is $height")

    try {
      val renderer: WebGLRenderer = webGLRenderer(width, height)
      val scene = SceneWithLights.sceneWithLights()
      val camera = DefaultCamera.createCamera(width, height)

//      val cubesAndStuff = CubesAndStuff(renderer, scene, camera)
      //CubesAndStuff(renderer, scene, camera).doDrawing()
      Knitwear(renderer, scene, camera).doDrawing()
    } catch {
      case ex: Throwable => println("Failed in componentDidMount() " + ex)
    }
  }

  private def webGLRenderer(innerWidth: Long, innerHeight: Long) = {
    println("Creating a WebGLRenderer")
    val webGLRendererParameters = WebGLRendererParameters()
    val renderer = new WebGLRenderer(webGLRendererParameters)
    renderer.setSize(innerWidth, innerHeight)
    document.getElementById(props.elemId).appendChild(renderer.domElement)
    renderer
  }

//  def sceneWithLights() = {
//    val scene = new Scene()
//
//    val lightColour = new Color(0xaaffff)
//
//    val lights =
//      for (i <- 1 to 2) yield {
//        val light = new DirectionalLight()
//        light.color = lightColour
//        light.position.set(150 * i, 150* i, 200 * i)
//        scene.add(light)
//        light
//      }
//
//    scene
//  }

//  def createCamera(width: Long, height: Long): PerspectiveCamera =
//    new PerspectiveCamera(60, width / height, 0.1, 10000)

}
