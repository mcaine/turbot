package com.mikeycaine.turbot

import com.mikeycaine.turbot.components.{SvgClock, WebGLContainer}
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._

object App {
  val Component =
    ScalaComponent.builder[Unit]
      .renderStatic(
        <.div(
          SvgClock(5, 45, 10, true),
          WebGLContainer("xyz")
        )
      )
      .build

  def apply() = Component()
}

