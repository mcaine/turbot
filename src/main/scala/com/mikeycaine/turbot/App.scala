package com.mikeycaine.turbot

import com.mikeycaine.turbot.components.{KnitwearContainer, P5Demo, SvgClock, WebGLContainer}
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._

object App {
  val Component =
    ScalaComponent.builder[Unit]
      .renderStatic(
        <.div(
          //KnitwearContainer("knitwear"),
          //WebGLContainer("xyz")
          P5Demo("p5elem")
          //SvgClock(5, 45, 10, true)
        )
      )
      .build

  def apply() = Component()
}

