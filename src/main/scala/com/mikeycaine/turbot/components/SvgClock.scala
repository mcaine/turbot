package com.mikeycaine.turbot.components

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.vdom.svg_<^.<.{g, line, svg, text, circle}
import japgolly.scalajs.react.vdom.svg_<^.^.{transform, x1, x2, y1, y2, textAnchor, x, y, visibility, r}

object SvgClock {

  case class ClockProps(hours: Int, minutes: Int, seconds: Int, showSeconds: Boolean)

  val clockRadius: Int = 200
  val hourLabelRadius: Int = clockRadius - 45
  val hourLabelYOffset: Int = 15

  def createMinuteTick(minute: Int) = line(
    ^.key := String.valueOf(100 + minute),
    ^.`class` := "minute-tick",
    x1 := 0, x2 := 0, y1 := 200, y2 := 190,
    transform := "rotate(" + 6 * minute + ")"
  )

  def createHourTick(hour: Int) = line(
    ^.key := String.valueOf(200 + hour),
    ^.`class` := "hour-tick",
    x1 := 0, x2 := 0, y1 := 200, y2 := 182,
    transform := "rotate(" + 30 * hour + ")"
  )

  def createHourLabel(hour: Int) = {
    val xv = hourLabelRadius * Math.sin(Math.PI * hour / 6)
    val yv = -hourLabelRadius * Math.cos(Math.PI * hour / 6) + hourLabelYOffset
    text(
      ^.key := String.valueOf(300 + hour),
      ^.`class` := "hour-label",
      textAnchor := "middle", x := xv, y := yv)("" + hour)
  }

  val minutes: Seq[Int] = for (minute <- 1 to 60) yield minute
  val hours: Seq[Int] = for (hour <- 1 to 12) yield hour

  val MinuteTicks = ScalaFnComponent[Unit] { _ =>
    g(minutes map createMinuteTick: _*)
  }

  val HourTicks = ScalaFnComponent[Unit] { _ =>
    g(hours map createHourTick: _*)
  }

  val HourLabels = ScalaFnComponent[Unit] { _ =>
    g(hours map createHourLabel: _*)
  }

  val Hands = ScalaFnComponent[ClockProps] { props =>
    val secs: Double = 3600.0 * (props.hours % 12) + 60.0 * props.minutes + props.seconds
    val hourAngle: Double = 360.0 * secs / (3600 * 12)
    val minuteAngle: Double = 360.0 * (secs % 3600) / 3600
    val secondAngle: Double = 360.0 * (secs % 60) / 60

    g(^.id := "clock-hands")(
      line(^.`class` := "hour-hand", x1 := 0, y1 := 0, x2 := 0, y2 := -130, transform := "rotate(" + hourAngle + ")"),
      line(^.`class` := "minute-hand", x1 := 0, y1 := 0, x2 := 0, y2 := -200, transform := "rotate(" + minuteAngle + ")"),
      line(^.`class` := "second-hand", x1 := 0, y1 := 0, x2 := 0, y2 := -200, transform := "rotate(" + secondAngle + ")", visibility := (if (props.showSeconds) "visible" else "hidden"))
    )
  }

  val CentreOverlay = ScalaFnComponent[Unit] { _ =>
    g(^.id := "face-overlay")(
      circle(^.`class` := "hands-cover", x := 0, y := 0, r := 10)
    )
  }

  val Component =
    ScalaComponent.builder[ClockProps]
      .render_P(props => {
        svg(^.className := "svgClock", ^.height := "500", ^.width := "500")(
          g(^.id := "clock-face", transform := "translate(250,250)")(
            MinuteTicks(),
            HourTicks(),
            HourLabels(),
            Hands(props),
            CentreOverlay()
          )
        )
      }).build

  def apply(hours: Int, minutes: Int, seconds: Int, showSeconds: Boolean) = Component(ClockProps(hours, minutes, seconds, showSeconds))

}
