package com.mikeycaine.turbot.components

import japgolly.scalajs.react.{Callback, ScalaComponent}
import org.scalajs.dom.{Element, document}
import typings.reactP5.mod.{P5, SketchProps}
import typings.reactP5.components.ReactP5

object P5Demo {
  case class Props(elemId: String)

  val mySetup = (p: P5, canvasParentRef: Element) => Callback {
    p.createCanvas(500, 500).parent(canvasParentRef);
  };

  val myDraw = (p:P5) => Callback {
    p.background(0)
    for (i <- 0 to 10){
      p.ellipse(i * 50 , i * 50, i * 30 + 80, i * 30 + 80)
    }
  }

  val Component = ScalaComponent.builder[Props]("P5Demo")
    .renderStatic(
      ReactP5(mySetup)
        .draw(myDraw)
        .build
    ).build

  def apply(elemId: String) = Component(Props(elemId))
}
