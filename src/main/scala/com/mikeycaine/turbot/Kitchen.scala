package com.mikeycaine.turbot

import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.div
// import typings.reactLeaflet.components.{TileLayer, Map => LeafMap, Marker, Popup}
import scala.scalajs.js.Array





@react class Kitchen extends Component {



  def elemId = {
    props.id
  }

  case class Props(name: String, id: String)

  case class State(count: Long, lat: Double, lng: Double, zoom: Int)

  override def initialState = State(747L, 51.505, -0.09, 15)

  override def render: ReactElement = div()

    //val tlp = TileLayerProps("http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png")
//    val tlp = TileLayerProps("http://{s}.tile.osm.org/{z}/{x}/{y}.png")
//
//    val latLng: LatLngExpression = Array(49.505, -2.09).asInstanceOf[LatLngExpression]
//    val latLng2: LatLngExpression = Array(53.505, 2.09).asInstanceOf[LatLngExpression]
//    val center: LatLngExpression =  Array(51.48, -0.025).asInstanceOf[LatLngExpression]
//
//    val bounds = Array(latLng, latLng2).asInstanceOf[LatLngBoundsExpression]


    //val pt1 = LatLng(49.505, -2.09)
    //val pt2 = LatLngLiteral(53.505, 2.09)
    //val bounds2 = LatLngBounds()



    //val bounds: LatLngBounds  = Array(pt1, pt2)
    //val bounds: LatLngBounds  = Array(49.505, -2.09, 53.505, 2.09)
    //val bounds = Array(Array(49.505, -2.09), Array(53.505, 2.09))

    //val bounds: LatLngBounds = LatLngLiteral(49.505, -2.09)


    //val mapProps = MapProps().setBounds(bounds3).setId("mapHolder").setClassName("mapHolder")
    //val mapProps = MapProps().setClassName("mapHolder").setId("mapHolder").setBounds(bounds).setZoom(13).setCenter(center)
    //val mapProps = MapProps().setCenter(center).setZoom(this.state.zoom)

    //val position = js.Tuple2(this.state.lat, this.state.lng)
    //val latlng = LatLngLiteral(this.state.lat, this.state.lng)
    //val zoom = this.state.zoom
    //val markerProps = MarkerProps(latlng)

    //val tlp = new TileLayerProps().set



//      LeafMap(mapProps) (
//        TileLayer(tlp)
//    )
//    div()
//  }

}


