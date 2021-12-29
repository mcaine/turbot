package com.mikeycaine.turbot.components

import org.scalajs.dom
import typings.three.ambientLightMod.AmbientLight
import typings.three.fontLoaderMod.{Font, FontLoader}
import typings.three.textGeometryMod.{TextBufferGeometry, TextGeometry, TextGeometryParameters}
//import typings.three.fontLoaderMod.{Font, FontLoader}
import typings.three.meshBasicMaterialMod.{MeshBasicMaterial, MeshBasicMaterialParameters}
import typings.three.meshLambertMaterialMod.{MeshLambertMaterial, MeshLambertMaterialParameters}
import typings.three.meshMod.Mesh
import typings.three.sceneMod.Scene
import typings.three.shaderMaterialMod.{ShaderMaterial, ShaderMaterialParameters}
import typings.three.shaderMaterialMod.ShaderMaterialParameters.ShaderMaterialParametersMutableBuilder
import typings.three.sphereGeometryMod.SphereGeometry
import typings.three.textureLoaderMod.TextureLoader
import typings.three.textureMod
import typings.three.textureMod.Texture
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

  val scene = SceneUtils.sceneWithAmbientLight

  val camera = CameraUtils.newCamera(width, height)

  val renderer: WebGLRenderer = WebGL.webGLRenderer(elemId, width, height)

  val fontLoader = new FontLoader()
  fontLoader.load("fonts/Pacifico_Regular.json", (font: Font) => {
    drawText(scene, font, "Fran")
  })

  val textureLoader = new TextureLoader()
  val texture: Texture = textureLoader.load("./img/skye.jpg")

  val meshBasicMaterialParameters = js.Dynamic.literal("map" -> texture).asInstanceOf[MeshBasicMaterialParameters]

  val geometry = new SphereGeometry(5, 60, 40, 0, Math.PI * 2, 0, Math.PI)
  geometry.scale(-1, 1, 1)

  val material = new MeshBasicMaterial(meshBasicMaterialParameters)
  val sphere = new Mesh(geometry, material)

  scene.add(sphere)

  val vertexShader =
    """void main() {
      |  gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
      |}
      |""".stripMargin

  val fragmentShader =
    """
      |void main() {
      |  gl_FragColor = vec4(1.0, 1.0, 0.0, 1.0);
      |}
      |""".stripMargin

  val geometry2 = new SphereGeometry(1,60,40, 0, Math.PI * 2, 0, Math.PI)
  val shaderMaterialParams = ShaderMaterialParameters().setFragmentShader(fragmentShader).setVertexShader(vertexShader) //.setUniforms()
  val shaderMaterial = new ShaderMaterial(shaderMaterialParams)
  val sphere2 = new Mesh(geometry2, shaderMaterial)
  sphere2.position.set(0, 1,  2);
  scene.add(sphere2)


  def drawText(theScene: Scene, font: Font, textStr: String): Unit = {

    val fontSize = 0.5

    val textGeomParams = TextGeometryParameters(font)
    textGeomParams.size = fontSize
    textGeomParams.height = fontSize / 5

    val textGeometry = new TextGeometry(textStr, textGeomParams)

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

