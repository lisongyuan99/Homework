<template>
  <div id="Colormap">
    <div class="header">
      <el-page-header @back="goBack" content="ColorMap"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ColorMap',
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  methods: {
    goBack: function () {
      window.location.href = 'http://lsy99.cn'
    },
  }
}
</script>

<style scoped>
.header {
  margin: 20px;
}
</style>
