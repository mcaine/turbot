package com.mikeycaine.turbot.components

import typings.three.perspectiveCameraMod.PerspectiveCamera

object CameraUtils {
  def newCamera(width: Int, height: Int): PerspectiveCamera = {
    val camera = new PerspectiveCamera(100, width / height, 0.1, 100)
    camera.position.set(0,0,0)
    camera
  }
}
