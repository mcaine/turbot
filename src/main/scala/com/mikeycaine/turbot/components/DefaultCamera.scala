package com.mikeycaine.turbot.components

import typings.three.perspectiveCameraMod.PerspectiveCamera

object DefaultCamera {
  def createCamera(width: Long, height: Long): PerspectiveCamera =
    new PerspectiveCamera(60, width / height, 0.1, 10000)

}
