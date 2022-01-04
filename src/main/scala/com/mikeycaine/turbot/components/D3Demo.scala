package com.mikeycaine.turbot.components

import com.mikeycaine.turbot.components.WebGLContainer.{Backend, Props}
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.BackendScope
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, ScalaComponent}
import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.{Element, HTMLElement, document, window}
import typings.d3.global.d3
import typings.d3Geo.mod.{GeoContext, GeoProjection_}
import typings.d3Selection.mod
import typings.d3Selection.mod.ValueFn
import typings.geojson.mod.{LineString, Position}
import typings.std.FrameRequestCallback
import typings.d3.{mod => d3Mod}
import typings.three.webGLRendererMod.{WebGLRenderer, WebGLRendererParameters}

import scala.scalajs.js
//import typings.reactP5.components.ReactP5
//import typings.reactP5.mod.P5

object D3Demo {
  case class Props(elemId: String)

  class Backend($: BackendScope[Props, Unit]) {

    def start = $.props.map({ props: Props =>
      //println(s"Im elemenent ${props.elemId}")
      val elem = document.getElementById(props.elemId)
      //val webGLRendererParameters = WebGLRendererParameters()
      val canvas = dom.document.createElement("canvas").asInstanceOf[Canvas]
      //canvas.id = "content"

      val context = canvas.getContext("2d")
      val geocontext = context.asInstanceOf[GeoContext]
      elem.appendChild(canvas)

      val width  = window.innerWidth
      println(s"Width is $width")
      val height = window.innerHeight
      println(s"Height is $height")
      val size   = width min height

      canvas.width = width.toInt
      canvas.height = height.toInt


//      d3Mod
//        .select("content")
//        .attr("width", s"${width}px")
//        .attr("height", s"${height}px")

      val projection: GeoProjection_ =
        typings.d3Geo.mod
          .geoOrthographic()
          .scale(0.45 * size)
          .translate(js.Tuple2(0.5 * width, 0.5 * height))

      val geoGenerator =
        typings.d3Geo.mod.geoPath(projection, geocontext)

      val geometry = LineString(coordinates = js.Array[Position]())

      def rndLon = -180 + Math.random() * 360
      def rndLat = -90 + Math.random() * 180

      def addPoint(): Unit =
        geometry.coordinates.push(js.Array(rndLon, rndLat))

      def update: FrameRequestCallback =
        (time: Double) => {
          if (geometry.coordinates.length < 6000) addPoint()

          projection.rotate(js.Tuple2(time / 100, 1.0))

          context.clearRect(0, 0, width, height)
          context.beginPath()

          geoGenerator(geometry, null.asInstanceOf[js.Any])
          context.stroke()

          window.requestAnimationFrame(update)
        }

      window.requestAnimationFrame(update)




      ()

//      val renderer = new WebGLRenderer(webGLRendererParameters)
//      renderer.setSize(width, height)
//      document.getElementById(elemId).appendChild(renderer.domElement)
//      renderer


      //val sel = d3.selectAll("div").data(js.Array(5,2,4,6))
      //val fn = (d:Int) => d*2.0
      //sel.style("width", fn)

    })

    def stop = Callback {

    }
  }

  val Component
  = ScalaComponent.builder[Props]
    .backend(new Backend(_))
    .render_P(props => <.div(^.id := props.elemId))
    .componentDidMount(_.backend.start)
    .componentWillUnmount(_.backend.stop)
    .build

  def apply(elemId: String) = Component(Props(elemId))
}
