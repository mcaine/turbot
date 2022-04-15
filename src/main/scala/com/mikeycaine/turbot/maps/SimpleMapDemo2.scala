package com.mikeycaine.turbot.maps


import japgolly.scalajs.react.vdom.HtmlTagOf
import japgolly.scalajs.react.vdom.html_<^.<._
import japgolly.scalajs.react.{Callback, CallbackOption, CallbackTo, Ref, ScalaComponent}
import org.scalablytyped.runtime.{StObject, StringDictionary}
import org.scalajs.dom
import org.scalajs.dom.html.Body
import org.scalajs.dom.svg.Path
import org.scalajs.dom.{Element, HTMLElement, fetch}
import typings.d3.global.d3

import javax.security.auth.callback
import typings.d3.{mod => d3Mod}
import typings.d3Geo.mod.{GeoPath_, GeoPermissibleObjects, GeoProjection_}
import typings.d3Geo.{mod => d3Geo}
import typings.d3Selection.mod
import typings.d3Selection.mod.{BaseType, ValueFn, select}
import typings.geojson.mod
import typings.geojson.mod.{Feature, FeatureCollection, GeoJsonObject, GeometryCollection, GeometryObject, MultiPolygon}
import typings.topojson.global.topojson
import typings.topojson.mod.feature
import typings.topojsonSpecification.mod.{Objects, Polygon, Properties, TopoJSON, Topology}

import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js
import scala.scalajs.js.Object.keys
import scala.scalajs.js.{JSON, Promise, UndefOr, |}
import scala.util.{Failure, Success}




object SimpleMapDemo2 {

  class Backend {

    implicit val ec: ExecutionContext = scala.scalajs.concurrent.JSExecutionContext.queue
    import scala.scalajs.js.Thenable.Implicits._
    //import scala.concurrent.ExecutionContext.Implicits.global

    // Create a mutable reference
    private val ref = Ref.toScalaComponent(Map.Component)

    // Wire it up from VDOM
    def render = div(Map.Component.withRef(ref)())
    // Alternatively: <.div(ref.component(123))

    // Use it from a lifecycle callback
    def onMount: Callback = Callback {

      val width = 960
      val height = 1160

      var svg = typings.d3.mod.select("#theMap").append("svg")
        .attr("width", width)
        .attr("height", height);

      val size = width min height

      val a: Promise[UndefOr[Topology[Objects[Properties]]]] = typings.d3Fetch.mod.json[Topology[Objects[Properties]]]("https://cdn.jsdelivr.net/npm/world-atlas@2/countries-110m.json")
      a.foreach {
        case topology: UndefOr[_] => {
          println ("Got a topology")
          topology.foreach { top =>
            println ("Got a top")
            //println(top.objects.)
            val b: Feature[GeometryObject, Nothing] | FeatureCollection[GeometryObject, Nothing] = feature(top, "countries")
            b match {
              case f if f.asInstanceOf[js.Object].hasOwnProperty("geometry") => println("Feature")
              case fc if fc.asInstanceOf[js.Object].hasOwnProperty("features") => {
                println("Feature COllecton")
                val featureCollection = fc.asInstanceOf[FeatureCollection[GeometryObject, Properties]]
                //println(featureCollection.features.size)
                featureCollection.features.foreach(feat => {
                  val sd = feat.properties.asInstanceOf[StringDictionary[Any]]
                  println(feat.id + " " + feat.`type` + " " + sd.get("name"))
                  println(keys(feat))
                  val geometry: GeometryObject = feat.geometry
                  val geoJsonObj = geometry.asInstanceOf[GeoJsonObject]
                  println(keys(geometry))
                  println(geoJsonObj.`type`)
                  val path2: GeoPath_[Any, GeoPermissibleObjects] = typings.d3Geo.mod.geoPath().projection(typings.d3Geo.mod.geoMercator())
                  val path = typings.d3Geo.mod.geoPath().projection(typings.d3Geo.mod.geoMercator()).asInstanceOf[ValueFn[Path, GeoJsonObject, Double]]
                  //svg.append("path").datum(geoJsonObj).attr("d", path)

                  val pathSelection = svg.append[Path]("path")
                  //geometry match {
                    //case mp: MultiPolygon => println(mp.coordinates)
                    //case _ => println("no macth")
                  //}
                })

              }

            }
            //println("b is " + b.right)
            //svg.append("path").datum(b)

 //           val b = typings.topojsonClient.mod.feature(top, "countries")

            //val features = b.features
            //println("Got " + features.size + " features")
          }

          //val path: GeoPath_[Any, GeoPermissibleObjects] = typings.d3Geo.mod.geoPath().projection(typings.d3Geo.mod.geoMercator())
          //svg.append("path").datum(b).attr("d", path)
        }
      }

//      val projection: GeoProjection_ =
//        typings.d3Geo.mod
//          .geoOrthographic()
//          .scale(0.45 * size)
//          .translate(js.Tuple2(0.5 * width, 0.5 * height))

      //val geoGenerator =
      //  typings.d3Geo.mod.geoPath(projection, geocontext)

//      typings.d3.mod.select("#theMap")
//        .append("g")
//        .classed("countries", true)

//      val z: GeoPath_[Any, GeoPermissibleObjects] = typings.d3Geo.mod.geoPath().projection(typings.d3Geo.mod.geoMercator())

//      typings.d3Fetch.mod.json[Topology[Objects[Properties]]]("https://cdn.jsdelivr.net/npm/world-atlas@2/countries-110m.json")
//        .foreach(resp => resp.foreach(topology => {
//          val f: mod.Feature[GeometryObject, Nothing] | FeatureCollection[GeometryObject, Nothing] = typings.topojsonClient.mod.feature(topology, "countries")

//          var g = null
//          f match {
//            case a: mod.Feature[GeometryObject, Nothing] => ???
//            case b: FeatureCollection[GeometryObject, Nothing] => ???
//          }
//          //val g = f.features

          //svg.append("path").datum(f).attr("d", z)

//          println(s"Geometry Collection: ${f}")
//          g.foreach((h: GeometryObject) => {
//            println(s"Features: ${h.toString}")
//            svg.append("path").datum(h)  //.attr("d", )
//          })
          //println(s"Features: ${g}")
//        }))

      //
      // typings.d3Fetch.mod.json[Topology[Objects[Properties]]]("https://cdn.jsdelivr.net/npm/world-atlas@2/countries-110m.json")

//        .foreach { a =>
//          a foreach { b =>
//            val z = typings.d3Geo.mod.geoPath().projection(typings.d3Geo.mod.geoMercator())
//
//            val feature = typings.topojsonClient.mod.feature(b, "countries")
//            println(s"Feature is $feature")
//
//            svg.append("path").datum(feature).attr("d", z)
//                        //.attr("d", )
//          }
//        }



      //      def processTopology[T <: Topology[_]]  (t : T) = {
      //        svg.append("path").datum(t, "countries")
      //
      //      }

      //      val b = {
      //      for {
      //        json <- typings.d3Fetch.mod.json[Topology[Objects[Properties]]]("uk.json")
      //      } yield json
      //
      //        b.foreach((t: UndefOr[Topology[Objects[Properties]]]) => t.foreach((s: Topology[Objects[Properties]]) => {
      //          val z = typings.d3Geo.mod.geoPath().projection(typings.d3Geo.mod.geoMercator()).toString
      //
      //          svg.append("path")
      //            .datum(typings.topojson.mod.feature(s, "Polygon"))
      //            .attr("d", z)
      //
      //        }))
      //
      //          .attr("d", typings.d3Geo.mod.geoPath().projection(typings.d3Geo.mod.geoMercator()): GeoPath_)
      //        }))

      //        for {
      //          c <- b
      //          u
      //        } {
      //          svg.append("path")
      //            .datum(typings.topojson.mod.feature(uk, uk.
      //              objects.subunits))
      //            .attr("d", d3.geo.path().projection(d3.geo.mercator()));
      //        });
      //        }
      //
      //        val e = b map (uk => uk foreach {
      //
      //
      //        }
      //        1
      //        )
      //
      //
      //
      //
      //
      //      svg.append("path")
      //          .datum(topojson.feature(uk, uk.objects.subunits))
      //          .attr("d", d3.geo.path().projection(d3.geo.mercator()));
      //      });


      val url = "https://cdn.jsdelivr.net/npm/world-atlas@2/countries-110m.json"
      val responseText = for {
        response <- dom.fetch(url)
        text <- response.text()
      } yield {
        text
      }

      for (text <- responseText) {
        println(s"text: $text")
      }
 //   }
//
//      val t: Future[UndefOr[Nothing]] = for {
//        json <-typings.d3Fetch.mod.json(url)
//      } yield json
//
//    }



//    def onMount: Callback = Callback { fetch("https://uploads.codesandbox.io/uploads/user/f647f610-9482-4bdf-9210-12916f8a0ce4/mmF1-world.json")
//      .toFuture.map { _.json() } .onComplete {
//      case Success(x) => x.toFuture.onComplete {
//        case Success(value) => println(s"Value: ${value.getClass}")
//        case Failure(t) => ???
//      }
//      case Failure(t) => throw t
//    }}
//
//      Callback {
//
//      typings.d3.mod.json("https://uploads.codesandbox.io/uploads/user/f647f610-9482-4bdf-9210-12916f8a0ce4/mmF1-world.json",
//        data => { println(data)})
//
//      typings.d3.mod.select("#theMap")
//        .append("g")
//        .classed("countries", true)

//      val countries = typings.d3.mod.select("g")
//         .selectAll("path").data(body)

      //println(s"a is $a")

      //d3.select("body").append("g").classed("countries", true)
 //   }

//      ref.get.map {
//      case Some(x) => {
//        d3.select(x.getDOMNode.toElement.get).append("g").classed("countries", true)
//        println("Bye")
//      }
//      case None => {
//        println("None")
//      }
//    }

     // .get >>= CallbackTo { r =>
     // d3.select(r.)
     //   .append("g")
     //   .classed("countries", true)


      //println("Bye")
      //ref
       // .get.map(r => r map (_.getDOMNode))
  //  }

//      .flatMap { x => CallbackOption { x map(_.getDOMNode) flatMap (_.toElement) }
//
//      }
//      //.flat Callback ({
//      Callback.log("SOMETHING: ", _)
//    })

      //  )
      //.flatMapCB(Callback.log("DOM HTML: ", _))


        //.map(a => Callback{ println(a.flatMap(_.getDOMNode).flatMap(_.toElement))})
        //.map(_.getDOMNode.toElement.map ((el: Element) => el.outerHTML))
        //.map(_ map (_.getDOMNode.toElement map(_.outerHTML)))
//      .map {
//        case Some(x) => println(x.getDOMNode.toElement.map{_.outerHTML})
//        case None => ???
//      }

        //.flatMapCB(Callback.log("DOM HTML: ", _))
 //   }
  }
  }

  val Component = ScalaComponent.builder[Unit]("SimpleMapDemo2")
    .renderBackend[Backend]
    .componentDidMount(_.backend.onMount)
    .build

  def apply() = Component()

}
