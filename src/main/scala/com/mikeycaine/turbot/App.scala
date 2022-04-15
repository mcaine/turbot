package com.mikeycaine.turbot

import com.mikeycaine.turbot.components.{D3Demo, KnitwearContainer, MikePence, P5Demo, SvgClock, WebGLContainer}
import com.mikeycaine.turbot.maps.SimpleMapDemo2
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._

object App {
  val Component =
    ScalaComponent.builder[Unit]
      .renderStatic(
        <.div(
          //KnitwearContainer("knitwear"),
          //WebGLContainer("xyz"),
          //P5Demo("p5elem"),
          MikePence("mikepence-elem"),
          //D3Demo("d3divrighthere"),
          //SimpleMapDemo2()
          SvgClock(5, 45, 10, true)
        )
      )
      .build

  def apply() = Component()
}

