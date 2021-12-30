package com.mikeycaine.turbot.components

import com.mikeycaine.turbot.patterns.Pattern
import org.scalajs.dom
import typings.three.boxGeometryMod.BoxGeometry
import typings.three.cameraMod.Camera
import typings.three.fontLoaderMod.FontLoader
import typings.three.fontLoaderMod.{Font, FontLoader}
import typings.three.meshLambertMaterialMod.{MeshLambertMaterial, MeshLambertMaterialParameters}
import typings.three.meshMod.Mesh
import typings.three.sceneMod.Scene
import typings.three.textGeometryMod.{TextGeometry, TextGeometryParameters}
import typings.three.webGLRendererMod.WebGLRenderer
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.BackendScope
import japgolly.scalajs.react.vdom.html_<^._
import typings.std.global.document
import typings.three.perspectiveCameraMod.PerspectiveCamera
import typings.three.vector3Mod.Vector3

object KnitwearContainer {
  case class Props(elemId: String)

  class Backend($: BackendScope[Props, Unit]) {

    var elemId = $.props.runNow().elemId
    var world: World = null

    def start = Callback {
      val width = document.getElementById(elemId).clientWidth
      val height = dom.window.innerHeight.toInt

      println(s"knitwear width is actually $width")
      println(s"knitwear height is, so they say, $height")

      val knitwear = Knitwear(elemId, width, height)
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

//case class Knitwear(renderer: WebGLRenderer, scene: Scene, camera: Camera) {

case class Knitwear(elemId: String, width: Int, height: Int) {

  println("Loading Knitwear in elem " + elemId)

  val colours = ColourScheme.colours


  val materials = colours map (col => {
    val meshLambertMaterialParameters = MeshLambertMaterialParameters()
    meshLambertMaterialParameters.color = col
    new MeshLambertMaterial(meshLambertMaterialParameters)
  })

  //val scene: Scene = SceneUtils.sceneWithDirectionalLight
  val scene: Scene = SceneUtils.sceneWithAmbientLight
  val camera: PerspectiveCamera = CameraUtils.newCamera(width, height)
  val renderer: WebGLRenderer = WebGL.webGLRenderer(elemId, width, height)

//
  //val radius = 4000.0

  val pattern1 = Pattern(
    "###***@@*---*@@***###",
    "##**@@*--***--*@@**##",
    "#**@@*--**-**--*@@**#",
    "**@@*--**---**--*@@**",
    "*@@*--**-----**--*@@*",
    "**@@*--**---**--*@@**",
    "#**@@*--**-**--*@@**#",
    "##**@@*--***--*@@**##",
    "###***@@*---*@@***###",
  )

  val pattern2 = Pattern(
    "*********************",
    "*********************",
    "*********************",
    "*********************",
    "*********************",
    "*********************",
    "*********************",
    "*********************",
    "*********************",
  )


  val pattern3 = pattern1 nextTo pattern2
  val pattern4 = pattern2 nextTo pattern1
  val pattern5 = pattern1 above pattern2

  def doDrawing(): Unit = {
    val pattern = pattern5
    val geometry = new BoxGeometry(5.0, 5.0, 1.0, 1, 1, 1)

    for (x <- 0 until pattern.rowLength * 16)
      for (y <- 0 until pattern.nRows * 16) {

        val material = meshLambert(pattern.charAt(x, y).toString)
        val obj = new Mesh(geometry, material)

        obj.position.x = -1000 + 5 * x
        obj.position.y = -1000 + 5 * y
        obj.position.z = -500

        obj.rotation.x = 0
        obj.rotation.y = 0
        obj.rotation.z = 0

        scene add obj
      }

    new FontLoader().load("fonts/Old computer St_Regular.json", (font: Font) => {
      drawText(font, "Knitwear")
    })
  }

  def drawText(font: Font, textStr: String): Unit = {

    val fontSize = 5

    val textGeomParams = TextGeometryParameters(font)
    textGeomParams.size = fontSize
    textGeomParams.height = fontSize / 5

    val textGeometry = new TextGeometry(textStr, textGeomParams)

    val obj = new Mesh(textGeometry, materials(5))

    obj.position.x = 0
    obj.position.y = 0
    obj.position.z = -100

    obj.rotation.x = 0
    obj.rotation.y = 0
    obj.rotation.z = 0

    scene.add(obj)
  }

  def meshLambert(c: String): MeshLambertMaterial = c match {
    case _ if c.equals("*") => materials(0)
    case _ if c.equals("-") => materials(1)
    case _ if c.equals("@") => materials(2)
    case _ if c.equals("#") => materials(3)
    case _ => materials(4)
  }

  var theta = -90.0

  def moveCamera: Unit = {
    theta = theta + 1
    val vector = new Vector3(Math.sin(Math.PI * theta/180), 0 , Math.cos(Math.PI * theta/180))
    //val vector = new Vector3(0, 0 , -1)
    camera.lookAt(vector)
    renderer.render(scene, camera)
  }

  def animate(dummy: Double): Unit = {
    //println("animation frame")
    dom.window.requestAnimationFrame(animate)
    moveCamera
  }

  doDrawing()
  animate(0.0)
}
