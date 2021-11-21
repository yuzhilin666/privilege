<template>
<div :class="$style.demo">Hello, I'm {{name}}</div>
<!-- <div class="demo">Hello, I'm {{name}}</div> -->
</template>

<script>
export default {
  name: 'Demo',
  data () {
    return {
      name: 'demo'
    }
  }
}
</script>

<style module>
.demo{
  color:red;
}
</style>
