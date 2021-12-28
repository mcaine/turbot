package com.mikeycaine.turbot.components

import com.mikeycaine.turbot.patterns.Pattern
import org.scalajs.dom
import typings.three.boxGeometryMod.BoxGeometry
import typings.three.cameraMod.Camera
import typings.three.coneGeometryMod.ConeGeometry
import typings.three.dodecahedronGeometryMod.DodecahedronGeometry
import typings.three.fontLoaderMod.FontLoader
import typings.three.fontMod.Font
import typings.three.geometriesMod.TorusKnotGeometry
import typings.three.global.THREE
import typings.three.meshLambertMaterialMod.{MeshLambertMaterial, MeshLambertMaterialParameters}
import typings.three.meshMod.Mesh
import typings.three.sceneMod.Scene
import typings.three.textGeometryMod.{TextBufferGeometry, TextGeometryParameters}
import typings.three.torusGeometryMod.TorusGeometry
import typings.three.webGLRendererMod.WebGLRenderer

case class TorusKnotDemo(renderer: WebGLRenderer, scene: Scene, camera: Camera) {

  val colours = ColourScheme.colours


  val materials = colours map (col => {
    val meshLambertMaterialParameters = MeshLambertMaterialParameters()
    meshLambertMaterialParameters.color = col
    new MeshLambertMaterial(meshLambertMaterialParameters)
  })


  def doDrawing(): Unit = {

    val geometry2 = new DodecahedronGeometry(30.0)
    scene.add( new Mesh(geometry2, materials(1)) )
    scene.add(new Mesh(new TorusGeometry(60, 20, 100, 100), materials(3)) )
    scene.add(new Mesh(new TorusGeometry(80, 20, 100, 100), materials(4)) )
    scene.add(new Mesh(new TorusGeometry(100, 20, 100, 100), materials(3)) )
    scene.add(new Mesh(new TorusGeometry(120, 20, 100, 100), materials(4)) )

    val fontPath = "fonts/Pacifico_Regular.json"
    val fontPath2 = "fonts/Old computer St_Regular.json"
    new FontLoader().load(fontPath, (font: Font) => {
      drawText(font, "TORUS NOT, more like")
    })
  }

  def drawText(font: Font, textStr: String): Unit = {

    val fontSize = 6

    val textGeomParams = TextGeometryParameters(font)
    textGeomParams.size = fontSize
    textGeomParams.height = fontSize / 5

    val textGeometry = new TextBufferGeometry(textStr, textGeomParams)

    val obj = new Mesh(textGeometry, materials(5))

    obj.position.x = -60
    obj.position.y = 20
    obj.position.z = 20

    obj.rotation.x = 0
    obj.rotation.y = 0
    obj.rotation.z = 0

    scene.add(obj)
  }

  var theta = -90.0


  def moveCamera(): Unit = {
    theta = theta + 1.0
//    if (theta > 90) {
//      theta = -90.0
//    }

    val radius = 100

    camera.position.x = radius * Math.sin(Math.PI * theta / 180)
    camera.position.y = 0.2 * radius * Math.sin(Math.PI * theta / 180)
    camera.position.z = radius * Math.cos(Math.PI * theta / 180)

    camera.lookAt(scene.position)
    camera.updateMatrixWorld()
    renderer.render(scene, camera)
  }

  def animate(dummy: Double): Unit = {
    //println("animation frame")
    dom.window.requestAnimationFrame(animate)
    moveCamera()
  }

  animate(0.0)
}
