package com.mikeycaine.turbot.components

import org.scalajs.dom
import typings.three.boxGeometryMod.BoxGeometry
import typings.three.cameraMod.Camera
import typings.three.fontLoaderMod.FontLoader
import typings.three.fontMod.Font
import typings.three.meshLambertMaterialMod.{MeshLambertMaterial, MeshLambertMaterialParameters}
import typings.three.meshMod.Mesh
import typings.three.sceneMod.Scene
import typings.three.textGeometryMod.{TextBufferGeometry, TextGeometryParameters}
import typings.three.webGLRendererMod.WebGLRenderer

import scala.util.Random

case class FirstSecond(elemId: String, width: Int, height: Int) {

  val colours = ColourScheme.colours

  val meshLambertMaterials = colours map (col => {
    val meshLambertMaterialParameters = MeshLambertMaterialParameters()
    meshLambertMaterialParameters.color = col
    new MeshLambertMaterial(meshLambertMaterialParameters)
  })

  val scene1 = SceneWithLights.sceneWithLights()
  val camera1 = DefaultCamera.createCamera(width, height)
  val renderer1: WebGLRenderer = WebGL.webGLRenderer(elemId, width, height)

  val scene2 = SceneWithLights.anotherScene()
  val camera2 = DefaultCamera.createCamera(width, height)
  val renderer2: WebGLRenderer = WebGL.webGLRenderer(elemId, width, height)

  new FontLoader().load("fonts/Old computer St_Regular.json", (font: Font) => {
    drawText(scene1, font, "FIRST")
    drawText(scene2, font, "SECOND")
  })


  def drawText(theScene: Scene, font: Font, textStr: String): Unit = {

    val fontSize = 500

    val textGeomParams = TextGeometryParameters(font)
    textGeomParams.size = fontSize
    textGeomParams.height = fontSize / 5

    val textGeometry = new TextBufferGeometry(textStr, textGeomParams)

    val obj = new Mesh(textGeometry, meshLambertMaterials(5))

    obj.position.x = -2300
    obj.position.y = -700
    obj.position.z = 0

    obj.rotation.x = 0
    obj.rotation.y = 0
    obj.rotation.z = 0

    theScene.add(obj)
  }


  var theta = -90.0

  def moveCameras(): Unit = {
    theta = theta + 0.5
    if (theta > 90) {
      theta = -90.0
    }

    val radius = 3000

    camera1.position.x = radius * Math.sin(Math.PI * theta / 180)
    camera1.position.y = 0.2 * radius * Math.sin(Math.PI * theta / 180)
    camera1.position.z = radius * Math.cos(Math.PI * theta / 180)
    camera1.lookAt(scene1.position)
    camera1.updateMatrixWorld()

    camera2.position.x = radius * Math.sin(Math.PI * theta / 180)
    camera2.position.y = 0.2 * radius * Math.sin(Math.PI * theta / 180)
    camera2.position.z = radius * Math.cos(Math.PI * theta / 180)
    camera2.lookAt(scene2.position)
    camera2.updateMatrixWorld()



    renderer1.render(scene1, camera1)
    renderer2.render(scene2, camera2)
  }

  def animate(dummy: Double): Unit = {
    moveCameras()
    dom.window.requestAnimationFrame(animate)
  }

  animate(0.0)
}

