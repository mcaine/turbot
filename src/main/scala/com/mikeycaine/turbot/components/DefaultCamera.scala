package com.mikeycaine.turbot.components

import typings.three.perspectiveCameraMod.PerspectiveCamera

object DefaultCamera {
  def createCamera(width: Long, height: Long): PerspectiveCamera =
    new PerspectiveCamera(90, width / height, 10, 10000)
}
