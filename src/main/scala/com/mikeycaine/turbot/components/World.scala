package com.mikeycaine.turbot.components

import org.scalablytyped.runtime.StringDictionary
import org.scalajs.dom
import typings.three.anon.Uniforms
import typings.three.fontLoaderMod.{Font, FontLoader}
import typings.three.meshBasicMaterialMod.{MeshBasicMaterial, MeshBasicMaterialParameters}
import typings.three.meshLambertMaterialMod.{MeshLambertMaterial, MeshLambertMaterialParameters}
import typings.three.meshMod.Mesh
import typings.three.perspectiveCameraMod.PerspectiveCamera
import typings.three.sceneMod.Scene
import typings.three.shaderMaterialMod.ShaderMaterialParameters.ShaderMaterialParametersMutableBuilder
import typings.three.shaderMaterialMod.{ShaderMaterial, ShaderMaterialParameters}
import typings.three.sphereGeometryMod.SphereGeometry
import typings.three.textGeometryMod.{TextGeometry, TextGeometryParameters}
import typings.three.textureLoaderMod.TextureLoader
import typings.three.textureMod.Texture
import typings.three.uniformsLibMod.IUniform
import typings.three.vector3Mod.Vector3
import typings.three.webGLRendererMod.WebGLRenderer

import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichMap

case class World(elemId: String, width: Int, height: Int) {

  val colours = ColourScheme.colours

  val meshLambertMaterials = colours map (col => {
    val meshLambertMaterialParameters = MeshLambertMaterialParameters()
    meshLambertMaterialParameters.color = col
    new MeshLambertMaterial(meshLambertMaterialParameters)
  })

  val scene: Scene = SceneUtils.sceneWithDirectionalLight
  val camera: PerspectiveCamera = CameraUtils.newCamera(width, height)
  val renderer: WebGLRenderer = WebGL.webGLRenderer(elemId, width, height)

  val fontLoader: FontLoader = new FontLoader()
  fontLoader.load("fonts/Pacifico_Regular.json", (font: Font) => {
    drawText(font, "Fran")
  })

  // sky
  val textureLoader = new TextureLoader()
  val texture: Texture = textureLoader.load("./img/shepherds_crag.jpg")
  val meshBasicMaterialParameters = MeshBasicMaterialParameters()
  meshBasicMaterialParameters.map = texture
  val geometry = new SphereGeometry(10, 60, 40, 0, Math.PI * 2, 0, Math.PI)
  geometry.scale(-1, 1, 1)
  val material = new MeshBasicMaterial(meshBasicMaterialParameters)
  val sphere = new Mesh(geometry, material)
  scene.add(sphere)

  val vertexShader =
    """
      |varying vec3 v_Normal;
      |void main() {
      |  gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
      |  v_Normal = normal;
      |}
      |""".stripMargin

  val fragmentShader =
    """
      |uniform vec3 sphereColour;
      |varying vec3 v_Normal;
      |void main() {
      |  //gl_FragColor = vec4(v_Normal, 1.0);
      |  gl_FragColor = vec4(sphereColour, 1.0);
      |}
      |""".stripMargin

  val geometry2 = new SphereGeometry(1,60,40, 0, Math.PI * 2, 0, Math.PI)

  val uniforms: StringDictionary[IUniform[Any]] =
    StringDictionary("sphereColour" -> IUniform(new Vector3(0,1,1)))

  val shaderMaterialParams: ShaderMaterialParameters =
    ShaderMaterialParameters()
      .setUniforms(uniforms)
      .setFragmentShader(fragmentShader)
      .setVertexShader(vertexShader)
  val shaderMaterial = new ShaderMaterial(shaderMaterialParams)
  val sphere2 = new Mesh(geometry2, shaderMaterial)
  sphere2.position.set(0, 1,  2);
  scene.add(sphere2)

  def drawText(font: Font, textStr: String): Unit = {

    val fontSize = 1

    val textGeomParams = TextGeometryParameters(font)
    textGeomParams.size = fontSize
    textGeomParams.height = fontSize / 5

    val textGeometry = new TextGeometry(textStr, textGeomParams)

    val textureLoader = new TextureLoader()
    val texture: Texture = textureLoader.load("./img/star-stitch.jpg")

    val meshBasicMaterialParameters = MeshBasicMaterialParameters()
    meshBasicMaterialParameters.map = texture
    val material = new MeshBasicMaterial(meshBasicMaterialParameters)

    val obj = new Mesh(textGeometry, material)

    obj.position.x = -1
    obj.position.y = 0
    obj.position.z = -2

    obj.rotation.x = 0
    obj.rotation.y = 0
    obj.rotation.z = 0

    scene.add(obj)
  }

  var theta = 0.0

  def moveCameras(): Unit = {
    theta = theta + 0.02
    val vector = new Vector3(Math.sin(Math.PI * theta/180), 0.2 , Math.cos(Math.PI * theta/180))
    camera.lookAt(vector)
    renderer.render(scene, camera)
  }

  var animating = true

  def animate(dummy: Double): Unit = {
    if (animating) {
      moveCameras()
      dom.window.requestAnimationFrame(animate)
    }
  }

  animate(0.0)

  def stop = {
    animating = false
  }
}

