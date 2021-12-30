package com.mikeycaine.turbot.components

import typings.three.colorMod.Color
import typings.three.directionalLightMod.DirectionalLight
import typings.three.ambientLightMod.AmbientLight
import typings.three.sceneMod.Scene

object SceneWithLights {

  def sceneWithLights(): Scene = {
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

  def anotherScene(): Scene = {
    val scene = new Scene()

    val lightColour = new Color(0xff7777)

//    val lights =
//      for (i <- -2 to 5) yield {
//        val light = new DirectionalLight()
//        light.color = lightColour
//        light.position.set(0, i * 300, 100)
//        scene.add(light)
//        light
//      }

    val ambient = new AmbientLight(lightColour)
    scene.add(ambient)

    scene
  }

}
