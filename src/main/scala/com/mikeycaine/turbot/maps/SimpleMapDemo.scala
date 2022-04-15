package com.mikeycaine.turbot.maps
//  import com.mikeycaine.turbot.maps.Map.ref
//  import japgolly.scalajs.react.vdom.{SvgTopNode, TopNode}
//  import japgolly.scalajs.react.vdom.html_<^._
//  import japgolly.scalajs.react.vdom.svg_<^.{<, ^}
//  import japgolly.scalajs.react.{Callback, Ref, ScalaFnComponent, vdom}
//  import org.scalajs.dom
//  import org.scalajs.dom.ext.Ajax
//  import org.scalajs.dom.html.Canvas
//  import org.scalajs.dom.{Body, Response, document, fetch, html}
//  import typings.d3.mod.select
//  import typings.d3Geo.mod.GeoContext
//  import typings.d3Geo.{mod => d3Geo}
//  import typings.geojson.mod.{Feature, FeatureCollection, Geometry}
//  import typings.geojson.{mod => d3geojson}
//
//  import java.util.concurrent.TimeUnit
//  import scala.concurrent.duration.Duration
//  import scala.scalajs.js
//  import scala.scalajs.js.Promise
//  import scala.scalajs.js.Thenable.Implicits.thenable2future
//  import scala.util.Success

object SimpleMapDemo {
//  var ref: TopNode = null
//
//  val width = 1200
//  val height = 900
//
//  case class State(something: Int)
//
//  val Component = ScalaFnComponent
//    .withHooks[Unit]
//    .useState(null: Any)
//    //.useEffectOnMount(Callback{
//    .useEffectOnMountBy((props, mapData) => Callback {
//
////      Ajax.get("https://uploads.codesandbox.io/uploads/user/f647f610-9482-4bdf-9210-12916f8a0ce4/mmF1-world.json") map (r =>
////        println("Sended!")
////        ) onFailure {
////        case dom.ext.AjaxException(r) => println("Error:" + r.response)
////      }
//
//    //import scala.concurrent.ExecutionContext.Implicits.global
//
//      println("Fetching data...")
//    val dataFuture = fetch(
//      "https://uploads.codesandbox.io/uploads/user/f647f610-9482-4bdf-9210-12916f8a0ce4/mmF1-world.json"
//    ).toFuture.flatMap(resp => resp.json())(scala.scalajs.concurrent.JSExecutionContext.queue)
//
//      dataFuture.onComplete {
//        case Success(body) => {
//          println(s"Got ${body.toString}")
//
//          // topojson client
//          //val features = feature(worlddata, worlddata.objects.countries).features
//
//          val proj = d3Geo.geoMercator()
//            .scale(150)
//            .translate(js.Tuple2(width / 2, height / 2))
////
//          //val node = ref
////
//          select(ref)
////            .append("g")
////            .classed("countries", true)
//
//                val countries = select("g")
//                  .selectAll("path")
//                  //.data(body)
//
//         // mapData.setState(body)
////          val width = 1000
////          val height = 1000
////          val proj = d3Geo.geoMercator()
////            .scale(150)
////            .translate(js.Tuple2(width / 2, height / 2))
////
////          val canvas = dom.document.createElement("canvas").asInstanceOf[Canvas]
////          canvas.id = "theCanvas"
////          canvas.height = height
////          canvas.width = width
////
////          val context = canvas.getContext("2d")
////          val geocontext = context.asInstanceOf[GeoContext]
////
////          document.body.append(canvas)
//        }
//        case _ => throw(new RuntimeException("Cant parse it"))
//      }(scala.scalajs.concurrent.JSExecutionContext.queue)
//
//     // })
//
//      //.`then`((resp: Response) => resp.json())
//      //.`then`((jsondata: Body) => mapData.modState(_ => jsondata))
//
//
//
////    a.`then`(resp: Response => resp.json)
////      \\.then(data => mapData.setState(_ => data));
//  })
//
//    //.render((props, mapData) => Map(mapData))
//    .render($ => <.svg(^.height := height,^.width := width, ^.untypedRef(ref = _ )))
//
//    //render((mapData:Body) => <.div(^.id := "something))
//
//  def apply() = Component()
//
//    //.useState(MapData())
//
////
////    .useEffectBy((props, count) => Callback {
////      document.title = s"You clicked ${count.value} times"
////    })
////
////    .useState("banana")
////
////    .render((props, count, fruit) =>
////      <.div(
////        <.p(s"You clicked ${count.value} times"),
////        <.button(
////          ^.onClick --> count.modState(_ + 1),
////          "Click me"
////        ),
////        <.p(s"Your favourite fruit is a ${fruit.value}!")
////      )
////    )
}
