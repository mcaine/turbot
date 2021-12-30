package com.mikeycaine.turbot

import com.mikeycaine.turbot.components.{WebGLContainer}
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._

object App {
  val Component =
    ScalaComponent.builder[Unit]
      .renderStatic(
        <.div(
          SvgClock(4, 20, 35, true),
          WebGLContainer("xyz")
        )
      )
      .build

  def apply() = Component()
}

