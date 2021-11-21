<template>
  <div class="index">
    <img src="./../assets/img/header.png" class="header" alt="">
    <div class="nickname" v-if="userInfo" v-text="userInfo.nickname"></div>
    <div class="btn-group">
      <button class="btn">分享</button>
      <button class="btn btn-primary" @click="recharge">充值</button>
      <button class="btn" @click="activity">活动详情</button>
    </div>
  </div>
</template>
<script>
import API from './../api/index'
export default {
  name: 'index',
  data(){
    return {
      userInfo:''
    }
  },
  mounted(){
    if(this.$cookie.get('openId')){
      this.getUserInfo();
    }
  },
  methods:{
    getUserInfo(){
      this.$http.get(API.getUserInfo).then((response)=>{
        let res = response.data;
        this.userInfo = res.data;
      });
    },
    recharge(){
      this.$router.push('/pay')
    },
    activity(){
      this.$router.push('/activity')
    },
  }
}
</script>
<style>
  .index{
    background-color:#FFC93A;
    height:100vh;
  }
  .btn-group{
    padding-top:.34rem;
    text-align:center;
  }
  .nickname{
    position:absolute;
    top:6rem;
    left: 50%;
    transform: 
    translateX(-50%);
    font-size:18px;
  }
</style>