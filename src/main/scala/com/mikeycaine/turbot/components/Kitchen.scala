package com.mikeycaine.turbot.components

import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{className, div, id, p}
import typings.leaflet.mod.LatLngLiteral
import typings.reactLeaflet.components.{Marker, Popup, TileLayer, Map => LeafletMap}
import typings.reactLeaflet.mod.TileLayerProps

@react class Kitchen extends Component {

  def elemId = {
    props.id
  }

  case class Props(name: String, id: String)

  case class State(count: Long, lat: Double, lng: Double, zoom: Int)

  override def initialState = State(747L, 51.505, -0.09, 13)

  override def render(): ReactElement = {

    val tlp = TileLayerProps("http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png")

    //val position = js.Tuple2(this.state.lat, this.state.lng)
    val latlng = LatLngLiteral(this.state.lat, this.state.lng)
    val zoom = this.state.zoom
    //val markerProps = MarkerProps(latlng)

    //val tlp = new TileLayerProps().set

    div(className := "Kitchen", id := elemId)(
      p(className := "Kitchen")(
        "THE SMELLY Kitchen id is ", props.id, ", name is ", props.name
      ),

      LeafletMap(latlng, zoom)(
        TileLayer(tlp),
        Marker(latlng)(Popup("This be the popup"))
      )

    )
  }

}
