package com.mikeycaine.turbot.components

import org.scalajs.dom
import typings.three.ambientLightMod.AmbientLight
import typings.three.fontLoaderMod.FontLoader
import typings.three.fontMod.Font
import typings.three.meshBasicMaterialMod.{MeshBasicMaterial, MeshBasicMaterialParameters}
import typings.three.meshLambertMaterialMod.{MeshLambertMaterial, MeshLambertMaterialParameters}
import typings.three.meshMod.Mesh
import typings.three.sceneMod.Scene
import typings.three.sphereGeometryMod.SphereGeometry
import typings.three.textGeometryMod.{TextBufferGeometry, TextGeometryParameters}
import typings.three.textureLoaderMod.TextureLoader
import typings.three.textureMod
import typings.three.vector3Mod.Vector3
import typings.three.webGLRendererMod.WebGLRenderer

import scala.scalajs.js

case class World(elemId: String, width: Int, height: Int) {

  val colours = ColourScheme.colours

  val meshLambertMaterials = colours map (col => {
    val meshLambertMaterialParameters = MeshLambertMaterialParameters()
    meshLambertMaterialParameters.color = col
    new MeshLambertMaterial(meshLambertMaterialParameters)
  })

  val scene = SceneUtils.newScene()
  val ambientLightColour = 0xffaaaa
  val ambientLight = new AmbientLight(ambientLightColour)
  scene.add(ambientLight)

  val camera = CameraUtils.newCamera(width, height)

  val renderer: WebGLRenderer = WebGL.webGLRenderer(elemId, width, height)

  new FontLoader().load("fonts/Old computer St_Regular.json", (font: Font) => {
    drawText(scene, font, "Fran")
  })

  val textureLoader = new TextureLoader()
  val texture: textureMod.Texture = textureLoader.load("./img/skye.jpg")

  val meshBasicMaterialParameters = js.Dynamic.literal("map" -> texture).asInstanceOf[MeshBasicMaterialParameters]

  val geometry = new SphereGeometry(5, 60, 40, 0, Math.PI * 2, 0, Math.PI)
  geometry.scale(-1, 1, 1)

  val material = new MeshBasicMaterial(meshBasicMaterialParameters)
  val sphere = new Mesh(geometry, material)

  scene.add(sphere)



  def drawText(theScene: Scene, font: Font, textStr: String): Unit = {

    val fontSize = 0.5

    val textGeomParams = TextGeometryParameters(font)
    textGeomParams.size = fontSize
    textGeomParams.height = fontSize / 5

    val textGeometry = new TextBufferGeometry(textStr, textGeomParams)

    val obj = new Mesh(textGeometry, meshLambertMaterials(5))

    obj.position.x = -2
    obj.position.y = -1
    obj.position.z = -2

    obj.rotation.x = 0
    obj.rotation.y = 0
    obj.rotation.z = 0

    theScene.add(obj)
  }

  var theta = 180.0

  def moveCameras(): Unit = {
    theta = theta + 0.2

    val vector = new Vector3(Math.sin(Math.PI * theta/180), 0.2 , Math.cos(Math.PI * theta/180))
    camera.lookAt(vector)

    renderer.render(scene, camera)

  }

  def animate(dummy: Double): Unit = {
    moveCameras()
    dom.window.requestAnimationFrame(animate)
  }

  animate(0.0)
}

