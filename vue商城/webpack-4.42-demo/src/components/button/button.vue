<template>
    <button
        class="el-button"
        @click="handleClick"
        v-bind:class="[
            'el-button--'+type
        ]"
    >
        <slot></slot>    
    </button>
</template>
<script>
    export default {
        name:'ElButton',
        props:{
            type:{
                type:String,
                default:'default'
            }
        },
        methods:{
            handleClick(event){
                this.$emit('click',event);
            }
        }
    }
</script>  
<style lang="scss">
    .el-button{
        display: inline-block;
        border:1px solid #dcdfe6;
        text-align: center;
        box-sizing: border-box;
        padding:12px 20px;
        font-size: 12px;
        border-radius: 4px;
        cursor: pointer;
    }
    .el-button--primary {
        color: #fff;
        background-color: #409eff;
        border-color: #409eff;
    }
    .el-button--success {
        color: #fff;
        background-color: #67c23a;
        border-color: #67c23a;
    }
</style>