package com.mikeycaine

import com.mikeycaine.turbot.App
import slinky.web.ReactDOM
import org.scalajs.dom.document
import org.scalatest.FunSuite

class AppTest extends FunSuite {
  test("Renders without crashing") {
    val div = document.createElement("div")
    ReactDOM.render(App(), div)
    ReactDOM.unmountComponentAtNode(div)
  }

  test("another") {
    assert(false)
  }
}
