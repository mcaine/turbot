package com.mikeycaine.turbot.components

import typings.std.global.document
import typings.three.webGLRendererMod.{WebGLRenderer, WebGLRendererParameters}

object WebGL {
  def webGLRenderer(elemId: String, innerWidth: Long, innerHeight: Long): WebGLRenderer = {
    val webGLRendererParameters = WebGLRendererParameters()
    val renderer = new WebGLRenderer(webGLRendererParameters)
    renderer.setSize(innerWidth, innerHeight)
    document.getElementById(elemId).appendChild(renderer.domElement)
    renderer
  }
}
