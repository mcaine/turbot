package com.mikeycaine.turbot.components

import typings.three.colorMod.Color
import typings.three.directionalLightMod.DirectionalLight
import typings.three.sceneMod.Scene

object SceneWithLights {

  def sceneWithLights() = {
    val scene = new Scene()

    val lightColour = new Color(0xaaffff)

    val lights =
      for (i <- 1 to 1) yield {
        val light = new DirectionalLight()
        light.color = lightColour
        light.position.set(1500, 1500, 1500)
        scene.add(light)
        light
      }

    scene
  }

}
