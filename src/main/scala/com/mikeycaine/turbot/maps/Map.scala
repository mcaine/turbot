package com.mikeycaine.turbot.maps

import japgolly.scalajs.react.{Callback, Ref, ScalaComponent, ScalaFnComponent}
import japgolly.scalajs.react.vdom.svg_<^._
import japgolly.scalajs.react.vdom.html_<^.<.p
import org.scalajs.dom.{Body, html}
import typings.d3.global.d3.select
import typings.d3Geo.{mod => d3Geo}
import typings.geojson.mod.FeatureCollection

import scala.scalajs.js

object Map {

  //case class Props(data: Any)

  //val ref = Ref.toVdom[html.Canvas]

  val Component = ScalaComponent.builder[Unit]("Map")
    .render_P(props => p(^.id := "theMap")(s"Some text goes here"))
    .build

//  val Component = ScalaFnComponent.withHooks[Props]
//    .useEffectOnMountBy((props: Props) => Callback {
//      val width = 1000
//      val height = 800
//      val proj = d3Geo.geoMercator()
//        .scale(150)
//        .translate(js.Tuple2(width / 2, height / 2))
//
//      val data = props.data
//      val node = ref.unsafeGet()
//
//      select(node)
//        .append("g")
//        .classed("countries", true)
////
////      val countries = select("g")
////        .selectAll("path")
////        .data[FeatureCollection](data)
//
//
//    })
//    .render($ => <.svg(^.height := 800,^.width := 1000, ^.untypedRef := ref))

  def apply() = Component()



}
