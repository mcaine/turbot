package com.mikeycaine.turbot.components

import japgolly.scalajs.react.{Callback, ScalaComponent}
import org.scalajs.dom.Element
import typings.reactP5.components.ReactP5
import typings.reactP5.mod.P5

object MikePence {
  case class Props(elemId: String)

  val mySetup = (p: P5, canvasParentRef: Element) => Callback {
    p.createCanvas(1000, 1000).parent(canvasParentRef);
    p.pixelDensity(1)
    p.noLoop()
  };

  val myDraw = (p:P5) => Callback {
    p.background(0)

    val height = 1000
    val width = 1000

    p.loadPixels();


    println ("Mike Pence 1")

    for (j <- 0 until height) {

      for (i <- 0 until width) {

        val pix = (i + j * width) * 4;
        val norm: Double =  Math.abs(i-j)

        var bright: Double = Math.sqrt(norm) * 255

        var red = 255 * Math.sqrt(Math.cos(2 * norm))
        var green = 255 * Math.cos(3 * Math.sqrt(norm))
        var blue = 255 * Math.cos(10 * norm * norm)

        p.pixels(pix + 0) = red
        p.pixels(pix + 1) = green
        p.pixels(pix + 2) = blue
        p.pixels(pix + 3) = 255
      }
    }
    p.updatePixels()
  }

  val Component = ScalaComponent.builder[Props]("MikePence")
    .renderStatic(
      ReactP5(mySetup)
        .draw(myDraw)
        .build
    ).build

  def apply(elemId: String) = Component(Props(elemId))
}
