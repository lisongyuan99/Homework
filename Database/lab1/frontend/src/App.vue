<template>
  <div id="app">
<!--    <el-table-->
<!--        :data="tableData"-->
<!--        style="width: 100%">-->
<!--      <el-table-column-->
<!--          prop="date"-->
<!--          label="日期"-->
<!--          width="180">-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--          prop="name"-->
<!--          label="姓名"-->
<!--          width="180">-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--          prop="address"-->
<!--          label="地址">-->
<!--      </el-table-column>-->
<!--    </el-table>-->
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App'
}
</script>

<style>
* {
  padding: 0;
  margin: 0;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
html, body, #app {
  height: 100%;
}
</style>
