package com.mikeycaine.turbot.components


import com.mikeycaine.turbot.patterns.Pattern
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

import scala.scalajs.js
import scala.util.Random

case class TorusKnotDemo(renderer: WebGLRenderer, scene: Scene, camera: Camera) {

  val colours = for (i <- 1 to 10) yield {
    val r = new Random()
    //0x77777 + r.nextGaussian() * 0x111111
    0xdddddd + r.nextGaussian() * 100L
  }


  val materials = colours map (col => {
    val meshLambertMaterialParameters = MeshLambertMaterialParameters()
    meshLambertMaterialParameters.color = col
    new MeshLambertMaterial(meshLambertMaterialParameters)
  })


  def doDrawing(): Unit = {
    val geometry = new BoxGeometry(50.0, 50.0, 10.0, 1, 1, 1)

    new FontLoader().load("fonts/Pacifico_Regular.json", (font: Font) => {
      drawText(font, "Torus Knot")
    })
  }

  def drawText(font: Font, textStr: String): Unit = {

    val fontSize = 500

    val textGeomParams = TextGeometryParameters(font)
    textGeomParams.size = fontSize
    textGeomParams.height = fontSize / 5

    val textGeometry = new TextBufferGeometry(textStr, textGeomParams)

    val obj = new Mesh(textGeometry, materials(5))

    obj.position.x = -2300
    obj.position.y = -700
    obj.position.z = 0

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

  def moveCamera(): Unit = {
    theta = theta + 0.5
    if (theta > 90) {
      theta = -90.0
    }

    val radius = 3000

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
