<template>
  <div id="BingPicture">
    <div class="header">
      <el-page-header @back="goBack" content="BingPic"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BingPicture',
  methods: {
    goBack: function () {
      window.location.href = 'http://lsy99.cn'
    }
  }
}
</script>

<style scoped>
.header {
  margin: 20px;
}
</style>
