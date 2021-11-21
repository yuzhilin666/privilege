import camera from './camera'
import light from './light'
import background from '../objects/background'

var HEIGHT = window.innerHeight > window.innerWidth ? window.innerHeight : window.innerWidth;
var WIDTH = window.innerHeight < window.innerWidth ? window.innerHeight : window.innerWidth;
var system = wx.getSystemInfoSync() || {};
var isIPhone = system.platform == 'ios';
var model = system.model;

class Scene {
  constructor () {
    this.instance = null
    this.currentScore = null
  }

  reset () {
    this.camera.reset()
    this.light.reset()
  }

  init () {
    this.instance = new THREE.Scene()
    const renderer = this.renderer = new THREE.WebGLRenderer({
      canvas: canvas,
      antialias: true,
      precision:"highp",
      preserveDrawingBuffer: true
    })

    if (isIPhone && (model.indexOf('iPhone 4') >= 0 || model.indexOf('iPhone 5') >= 0 || system.system.indexOf('iOS 9') >= 0 || system.system.indexOf('iOS 8') >= 0 || model.indexOf('iPhone 6') >= 0 && model.indexOf('iPhone 6s') < 0)) {
      renderer.shadowMap.enabled = true;
      this.renderer.setPixelRatio(1.5);
    } else {
      if (typeof system.benchmarkLevel != 'undefined' && system.benchmarkLevel < 5 && system.benchmarkLevel != -1) {
        renderer.shadowMap.enabled = true;
        renderer.setPixelRatio(window.devicePixelRatio ? isIPhone ? Math.min(window.devicePixelRatio, 2) : window.devicePixelRatio : 1);
      } else {
        renderer.setPixelRatio(window.devicePixelRatio ? isIPhone ? Math.min(window.devicePixelRatio, 2) : window.devicePixelRatio : 1);
        renderer.shadowMap.enabled = true;
      }
    }
    this.renderer.setSize(WIDTH, HEIGHT);
    this.renderer.localClippingEnabled = true;

    renderer.shadowMap.enabled = true
    renderer.shadowMap.type = THREE.PCFShadowMap

    this.camera = camera
    this.light = light
    this.camera.init()
    this.light.init()
    this.axesHelper = new THREE.AxesHelper(100)

    // this.instance.add(this.axesHelper)
    this.instance.add(this.camera.instance)
    for (let lightType in this.light.instances) {
      this.instance.add(this.light.instances[lightType])
    }

    this.background = background
    this.background.init()
    this.background.instance.position.z = -84
    this.camera.instance.add(this.background.instance)
  }

  render () {
    this.renderer.render(this.instance, this.camera.instance)
  }

  updateCameraPosition (targetPosition) {
    this.camera.updatePosition(targetPosition)
    this.light.updatePosition(targetPosition)
  }

  addScore (scoreInstance) {
    this.currentScore = scoreInstance
    this.camera.instance.add(scoreInstance)
     scoreInstance.position.x = -20
     scoreInstance.position.y = 46
  }
  addTime(scoreInstance){
    this.currentScore = scoreInstance
    this.camera.instance.add(scoreInstance)

     scoreInstance.position.x = 20
     scoreInstance.position.y = 47
  }

  updateTime(scoreInstance) {
    this.camera.instance.remove(this.currentScore)
    this.currentScore = scoreInstance
    this.camera.instance.add(scoreInstance)
    scoreInstance.position.x = 20
    scoreInstance.position.y = 47
  }

}

export default new Scene()