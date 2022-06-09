package com.mikeycaine.turbot.components

import japgolly.scalajs.react.{Callback, ScalaComponent}
import org.scalajs.dom.Element
import typings.p5.mod.Image
import typings.reactP5.components.ReactP5
import typings.reactP5.mod.P5

object MikePence {
  case class Props(elemId: String)

  case class Point(x: Int, y:Int)

  case class Quad(tl: Point, tr: Point, bl: Point, br: Point) {
    def draw(p: P5): Unit = {
      p.stroke(255, 0, 0)
      //p.line(q.tl.x, q.tl.y, q.tr.x, q.tr.y)
      p.line(tl.x, tl.y, tr.x, tr.y)

      p.stroke(0, 255, 0)

      p.line(tr.x, tr.y, br.x, br.y)

      p.stroke(0, 0, 255)
      p.line(br.x, br.y, bl.x, bl.y)

      p.stroke(255, 255, 255)
      p.line(bl.x, bl.y, tl.x, tl.y)
    }
  }

  val quads = List(
    Quad(Point(1483, 315), Point(1708, 390), Point(1392, 590), Point(1570, 735))
  )

  val w = 2048
  val h = 1334



  val mySetup = (p: P5, canvasParentRef: Element) => Callback {
    p.createCanvas(w, h).parent(canvasParentRef);
    p.pixelDensity(1)
    p.noLoop()

  };

  val myDraw = (p:P5) => Callback {
    //p.background(pence)


//    p.background(0)
//
//    val height = h
//    val width = w
//
//    p.loadPixels();
//
//    for (j <- 0 until height) {
//
//      for (i <- 0 until width) {
//
//        val pix = (i + j * width) * 4;
//        val norm: Double =  Math.abs(i-j)
//
//        var bright: Double = Math.sqrt(norm) * 255
//
//        var red = 255 * Math.sqrt(Math.cos(2 * norm))
//        var green = 255 * Math.cos(3 * Math.sqrt(norm))
//        var blue = 255 * Math.cos(10 * norm * norm)
//
//        p.pixels(pix + 0) = red
//        p.pixels(pix + 1) = green
//        p.pixels(pix + 2) = blue
//        p.pixels(pix + 3) = 255
//      }
//    }
//    p.updatePixels()



    p.loadImage("img/pencedesk.png", (img: Image) => {
      p.image(img, 0, 0)

      quads foreach (_.draw(p))
//        p.stroke(255, 0, 0)
//        //p.line(q.tl.x, q.tl.y, q.tr.x, q.tr.y)
//        p.line(q.tl.x, q.tl.y, q.tr.x, q.tr.y)
//
//        p.stroke(0, 255, 0)
//
//        p.line(q.tr.x, q.tr.y, q.br.x, q.br.y)
//
//        p.stroke(0, 0, 255)
//        p.line(q.br.x, q.br.y, q.bl.x, q.bl.y)

     // }

      //p.updatePixels()

//      p.stroke(255, 0, 0)
//      p.line(0, 1000, w, 1000)

    });
  }

  val Component = ScalaComponent.builder[Props]("MikePence")
    .renderStatic(
      ReactP5(mySetup)
        .draw(myDraw)
        .build
    ).build

  def apply(elemId: String) = Component(Props(elemId))
}
