package com.mikeycaine.turbot

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

//noinspection SpellCheckingInspection
@react class Bumper extends Component{
  def elemId: String = {
    "the" + "Bumper"
  }

  case class Props(name: String)

  case class State(count: Long)

  override def initialState: State = State(31337L)

  override def render(): ReactElement = {
    div(className := "Bumper")(
      p(className := "Bumper")(
        "CHECK IT OUT ", props.name
      ),
      div(id := elemId)
    )
  }

  override def componentDidMount(): Unit = {
    println("Widget componentDidMount()")

    //val window = dom.window

    val width = document.getElementById(elemId).clientWidth
    val height = dom.window.innerHeight.toInt

    println(s">> width is $width")
    println(s">> height is $height")

    try {

      val renderer = webGLRenderer(width, height)
      val scene = sceneWithLights()
      val camera = createCamera(width, height)

      doDrawing(renderer, scene, camera)

    } catch {
      case ex: Throwable => println("Failed in componentDidMount() " + ex)
    }
  }

  def doDrawing(renderer: WebGLRenderer, scene: Scene, camera: PerspectiveCamera): Unit = {

    println("in doDrawing()...")

    //new FontLoader().load("fonts/Pacifico_Regular.json", (font: Font) => {
    new FontLoader().load("fonts/helvetiker_regular.typeface.json", (font: Font) => {
      println("Loaded that font oh yes.....")

    val cubes = randomCubes(100)
    val objects = spinText(font, "the light inside the body", 500)

    for (obj <- objects) {
      scene.add(obj)
    }

    for (cube <- cubes) {
      scene.add(cube)
    }

    var theta: Double = 0
    val radius = 3000

    def render(): Unit = {
      theta = theta + 0.1

      camera.position.x = radius * Math.sin(Math.PI * theta / 180)
      camera.position.y = 0
      camera.position.z = radius * Math.cos(Math.PI * theta / 180)

      camera.lookAt(scene.position)
      camera.updateMatrixWorld()
      renderer.render(scene, camera)
    }

    def animate(p: Double): Unit = {
      println("animation frame")
      dom.window.requestAnimationFrame(animate)
      render()
    }

    animate(0)

    })
  }

  def createCamera(width: Long, height: Long): PerspectiveCamera = {
    println("Creating a PerspectiveCamera")
    val camera = new PerspectiveCamera(60, width / height, 0.1, 10000)
    camera
  }

  private def createAScene(): Scene = {
    println("Creating a Scene")
    val scene = new Scene()
    scene
  }

  private def webGLRenderer(innerWidth: Long, innerHeight: Long) = {
    println("Creating a WebGLRenderer")
    val webGLRendererParameters = js.Dynamic.literal("antialias" -> true).asInstanceOf[WebGLRendererParameters]
    val renderer = new WebGLRenderer(webGLRendererParameters)
    renderer.setSize(innerWidth, innerHeight)
    document.getElementById(elemId).appendChild(renderer.domElement)
    renderer
  }

  private def randomCubes(n: Integer) = {
    val geometry = new BoxGeometry(50.0, 50.0, 50.0, 10.0, 10.0, 10.0)

    val cubes = for (i <- 1 to n) yield {
      val material = randomMeshLambert()
      val obj = new Mesh(geometry, material)

      obj.position.x = Math.random() * 1800 - 400
      obj.position.y = Math.random() * 1800 - 400
      obj.position.z = Math.random() * 1800 - 400

      //      obj.rotation.x = Math.random() * 2 * Math.PI
      //      obj.rotation.y = Math.random() * 2 * Math.PI
      //      obj.rotation.z = Math.random() * 2 * Math.PI

      obj.rotation.x = 0
      obj.rotation.y = 0
      obj.rotation.z = 0

      obj
    }

    cubes
  }

  private def randomMeshLambert() = {
    val meshLambertMaterialParameters = js.Dynamic.literal(
      "color" -> (Math.random() * 0x00ffff).toInt
    ).asInstanceOf[MeshLambertMaterialParameters]

    val material = new MeshLambertMaterial(meshLambertMaterialParameters)
    material
  }

  private def sceneWithLights() = {
    val scene = new Scene()

    val lightColour = new Color(0xaaffff)

    val lights =
      for (i <- 1 to 20) yield {
        val light = new DirectionalLight()
        light.color = lightColour
        light.position.set(150 * i, 150* i, 200 * i)
        scene.add(light)
        light
      }

    scene
  }

  private def randomText(font: Font, textStr: String, n: Integer) = {

    val objects = for (i <- 1 to n) yield {
      val material = randomMeshLambert()

      val textGeometryParameters = js.Dynamic.literal(
        "font" -> font,
        "size" -> (50 + 50 * Math.random()),
        "height" -> (5 + 5 * Math.random())
      ).asInstanceOf[TextGeometryParameters]

      //var textGeometry = new TextGeometry(textStr, textGeometryParameters)
      val textGeometry = new TextBufferGeometry(textStr, textGeometryParameters)

      val obj = new Mesh(textGeometry, material)
      obj.position.x = Math.random() * 800 - 400
      obj.position.y = Math.random() * 800 - 400
      obj.position.z = Math.random() * 800 - 400

      //      obj.rotation.x = Math.random() * 2 * Math.PI
      //      obj.rotation.y = Math.random() * 2 * Math.PI
      //      obj.rotation.z = Math.random() * 2 * Math.PI
      //
      //      obj.scale.x = Math.random() + 0.5
      //      obj.scale.y = Math.random() + 0.5
      //      obj.scale.z = Math.random() + 0.5

      obj
    }

    objects
  }

  private def spinText(font: Font, textStr: String, n: Integer) = {

    val objects = for (i <- 1 to n) yield {
      val material = randomMeshLambert()

      val textGeometryParameters = js.Dynamic.literal(
        "font" -> font,
        "size" -> 300,
        "height" -> 5
      ).asInstanceOf[TextGeometryParameters]

      //var textGeometry = new TextGeometry(textStr, textGeometryParameters)
      val textGeometry = new TextBufferGeometry(textStr, textGeometryParameters)

      val obj = new Mesh(textGeometry, material)
      obj.position.x = 800 * Math.cos(Math.PI * i/18)
      obj.position.y = 800 * Math.sin(Math.PI * i/18)
      obj.position.z = 0

      obj.rotation.x = 0
      obj.rotation.y = Math.PI * i/18
      obj.rotation.z = 0
      //
      //      obj.scale.x = Math.random() + 0.5
      //      obj.scale.y = Math.random() + 0.5
      //      obj.scale.z = Math.random() + 0.5

      obj
    }

    objects
  }
}
