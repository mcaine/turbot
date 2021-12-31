package com.mikeycaine.turbot.components

import typings.std.global.document
import typings.three.webGLRendererMod.{WebGLRenderer, WebGLRendererParameters}

object WebGL {
  def webGLRenderer(elemId: String, width: Long, height: Long): WebGLRenderer = {
    val webGLRendererParameters = WebGLRendererParameters()
    val renderer = new WebGLRenderer(webGLRendererParameters)
    renderer.setSize(width, height)
    document.getElementById(elemId).appendChild(renderer.domElement)
    renderer
  }
}
