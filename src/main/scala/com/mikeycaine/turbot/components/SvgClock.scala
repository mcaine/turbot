package com.mikeycaine.turbot.components

import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.svg.{svg, height, width, className, g, id, key, transform, line, x, y, x1, x2, y1, y2, text, textAnchor, r, circle}
import slinky.web.html.div

@react class SvgClock extends Component {

  val clockRadius = 200;
  val hourLabelRadius = clockRadius - 40;
  val hourLabelYOffset = 12;

  case class State(hours: Int, minutes: Int, seconds: Int)

  case class Props(hours: Int, minutes: Int, seconds: Int)

  override def initialState = State(props.hours, props.minutes, props.seconds)

  override def render(): ReactElement = {

    val secs: Double = 3600.0 * (this.state.hours % 12) + 60.0 * (this.state.minutes) + (this.state.seconds);
    val hourAngle: Double = 360.0 * secs / (3600 * 12);
    val minuteAngle: Double = 360.0 * (secs % 3600) / 3600;

    def hourLabels = {
      for (i <- 1 to 12) yield {
        val xv = hourLabelRadius * Math.sin(Math.PI * i / 6)
        val yv = -hourLabelRadius * Math.cos(Math.PI * i/ 6) + hourLabelYOffset
        text(key := String.valueOf(200 + i), className := "hour-label", textAnchor := "middle", x := xv, y := yv)("" + i)
      }
    }

    def centreOverlay: ReactElement = g(id := "face-overlay")(
        circle(className := "hands-cover", x := 0, y := 0, r := 10)
      )

    def hands(hourAngle: Double, minuteAngle: Double) = g(id := "clock-hands")(
        line(className := "hour-hand", x1 := 0, y1 := 0, x2 := 0, y2 := -130, transform := "rotate(" + hourAngle + ")"),
        line(className := "minute-hand", x1 := 0, y1 := 0, x2 := 0, y2 := -200, transform := "rotate(" + minuteAngle + ")")
      )

    def hourTicks = {
      for (i <- 1 to 12) yield line(key := String.valueOf(100 + i), className := "hour-tick", x1 := 0, x2 := 0, y1 := 200, y2 := 182, transform := "rotate(" + 30 * i + ")")
    }

    def minuteTicks = {
      for (i <- 1 to 60) yield line(key := String.valueOf(i), className := "minute-tick", x1 := 0, x2 := 0, y1 := 200, y2 := 190, transform := "rotate(" + 6 * i + ")")
    }

    div()(
      svg(className := "svgClock", height := "500", width := "500")(
        g(id := "clock-face", transform := "translate(250,250)")(
          minuteTicks,
          hourTicks,
          hourLabels,
          hands(hourAngle, minuteAngle),
          centreOverlay
        )
      )
    )
  }

}
