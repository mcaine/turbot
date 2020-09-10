package com.mikeycaine.turbot.components

import slinky.core.{Component, StatelessComponent, SyntheticEvent}
import slinky.core.annotations.react
import slinky.web.html.{button, div, form, h3, input, key, li, onChange, onSubmit, ul, value}
import org.scalajs.dom.raw.Event
import org.scalajs.dom.html
import slinky.core.facade.ReactElement
import typings.std.global.^.Date

case class TodoItem(text: String, id: Long)

@react class TodoApp extends Component {
  type Props = Unit
  case class State(items: Seq[TodoItem], text: String)

  override def initialState: State = State(Seq.empty, "")

  def handleChange(e: SyntheticEvent[html.Input, Event]): Unit = {
    val eventValue = e.target.value
    setState(_.copy(text = eventValue))
  }

  def handleSubmit(e: SyntheticEvent[html.Form, Event]): Unit = {
    e.preventDefault()

    if (state.text.nonEmpty) {
      val newItem = TodoItem(
        text = state.text,
        id = Date.now().toLong
      )

      setState(prevState => {
        State(
          items = prevState.items :+ newItem,
          text = ""
        )
      })
    }
  }

  override def render(): ReactElement = {
    div(
      h3("TODO"),
      TodoList(items = state.items),
      form(onSubmit := (handleSubmit(_)))(
        input(
          onChange := (handleChange(_)),
          value := state.text
        ),
        button(s"Add #${state.items.size + 1}")
      )
    )
  }
}

@react class TodoList extends StatelessComponent {
  case class Props(items: Seq[TodoItem])

  override def render(): ReactElement = {
    ul(
      props.items.map { item =>
        li(key := item.id.toString)(item.text)
      }
    )
  }
}
