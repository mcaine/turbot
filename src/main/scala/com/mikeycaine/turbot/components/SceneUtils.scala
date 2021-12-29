package com.mikeycaine.turbot.components

import typings.three.ambientLightMod.AmbientLight
import typings.three.colorMod.Color
import typings.three.directionalLightMod.DirectionalLight
import typings.three.sceneMod.Scene
import typings.three.vector3Mod.Vector3

object SceneUtils {
  def newScene: Scene = new Scene()

  def sceneWithAmbientLight: Scene = {
    val scene = newScene
    scene add ambientLight
    scene
  }

  def ambientLight: AmbientLight = {
    val ambientLight = new AmbientLight()
    ambientLight.color = new Color(0xffaaff)
    ambientLight
  }

  def directionalLight: DirectionalLight = {
    val light = new DirectionalLight()
    light.color = new Color(0xaaffff)
    light.position.set(10, 10, 10)
    light.lookAt(new Vector3(0,0,-10))
    light
  }

  def sceneWithDirectionalLight: Scene = {
    val scene = newScene
    scene add directionalLight
    scene
  }
}
