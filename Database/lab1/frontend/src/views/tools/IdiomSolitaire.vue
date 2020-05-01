<template>
  <div id="IdiomSolitaire">
    <div class="header">
      <el-page-header @back="goBack" content="Idiom"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'IdiomSolitaire',
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
