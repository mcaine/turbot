package com.mikeycaine.turbot

import org.scalajs.dom

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.{LinkingInfo, js}

object Main {

  @JSImport("resources/index.css", JSImport.Default)
  @js.native
  object IndexCSS extends js.Object

  @JSImport("resources/App.css", JSImport.Default)
  @js.native
  object AppCSS extends js.Object

  @JSExportTopLevel("main")
  def main(): Unit = {
//    if (LinkingInfo.developmentMode) {
//      hot.initialize()
//    }

    IndexCSS
    AppCSS

    val rootElement = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    App().renderIntoDOM(rootElement)
  }
}