package com.mikeycaine.turbot.components

import typings.three.ambientLightMod.AmbientLight
import typings.three.sceneMod.Scene

object SceneUtils {
  def newScene: Scene = new Scene()

  def sceneWithAmbientLight: Scene = {
    val scene: Scene = newScene
    val ambientLightColour = 0xaaaaaa
    val ambientLight = new AmbientLight(ambientLightColour)
    scene.add(ambientLight)
    scene
  }

}
