package com.mikeycaine.turbot.components

import japgolly.scalajs.react.{Callback, ScalaComponent}
import org.scalajs.dom.{Element, document}
import typings.reactP5.mod.{P5, SketchProps}
import typings.reactP5.components.ReactP5

import scala.util.control.Breaks.break

object P5Demo {
  case class Props(elemId: String)

  val mySetup = (p: P5, canvasParentRef: Element) => Callback {
    p.createCanvas(1000, 1000).parent(canvasParentRef);
    p.pixelDensity(1)
    p.noLoop()
  };

  val myDraw = (p:P5) => Callback {
    p.background(0)

    // Establish a range of values on the complex plane
    // A different range will allow us to "zoom" in or out on the fractal

    // It all starts with the width, try higher or lower values
    val w = 0.8;
    val height = 1000
    val width = 1000
    val h = (w * height) / width;

    // Start at negative half the width and height
    val xmin = 0.1;
    val ymin = 0;

    // Make sure we can write to the pixels[] array.
    // Only need to do this once since we don't do any other drawing.
    p.loadPixels();

    // Maximum number of iterations for each point on the complex plane
    val maxiterations = 200;

    // x goes from xmin to xmax
    val xmax: Double = xmin + w;
    // y goes from ymin to ymax
    val ymax: Double = ymin + h;

    println(s"x: $xmin to $xmax")
    println(s"y: $ymin to $ymax")


    // Calculate amount we increment x,y for each pixel
    val dx: Double = (xmax - xmin).toDouble / (width);
    val dy: Double = (ymax - ymin).toDouble / (height);

    println(s"dx is $dx, dy is $dy")

    // Start y
    var y:Double = ymin;
    for (j <- 0 until height) {
      //println(s"j is $j")
      // Start x
      var x:Double = xmin;
      for (i <- 0 until width) {

        // Now we test, as we iterate z = z^2 + cm does z tend towards infinity?
        var a: Double = x
        var b: Double = y
        var n = 0
        var aa = a * a
        var bb = b * b
        while ((n < maxiterations) && (p.dist(aa, bb, 0, 0) < 16)) {
          aa = a * a
          bb = b * b
          val twoab: Double = 2.0 * a * b
          a = aa - bb + x
          b = twoab + y
          // Infinty in our finite world is simple, let's just consider it 16
          n += 1
        }

        //println (s"n is $n")

        // We color each pixel based on how long it takes to get to infinity
        // If we never got there, let's pick the color black
        val pix = (i+j*width)*4;
        val norm: Double = n.toDouble / maxiterations
        //println(s"norm is $norm")
        var bright: Double = Math.sqrt(norm) * 255

        var red = 255 * Math.sqrt(Math.cos(2 * norm))
        var green = 255 * Math.cos(3 * Math.sqrt(norm))
        var blue = 255 * Math.cos(10 * norm * norm)


        if (n == maxiterations) {
          bright = 0
        } else {
          //println(s"bright is $bright")
          // Gosh, we could make fancy colors here if we wanted
          p.pixels(pix + 0) = red
          p.pixels(pix + 1) = green
          p.pixels(pix + 2) = blue
          p.pixels(pix + 3) = 255
        }
        x += dx
      }
      y += dy
    }
    p.updatePixels()
  }

  val Component = ScalaComponent.builder[Props]("P5Demo")
    .renderStatic(
      ReactP5(mySetup)
        .draw(myDraw)
        .build
    ).build

  def apply(elemId: String) = Component(Props(elemId))
}
