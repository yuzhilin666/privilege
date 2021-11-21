// components/post/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    res:Object
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   * 组件的开发者不应该决定
   * 点击之后做什么事情 不应该
   * 组件的使用者
   * 自定义事件
   */
  methods: {
    onTap(event){
      const pid = this.properties.res.postId
      this.triggerEvent('posttap',{
        pid
      })
    },
  }
})
