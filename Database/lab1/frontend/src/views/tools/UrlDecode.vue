<template>
  <div id="UrlDecode">
    <div class="header">
      <el-page-header @back="goBack" content="URL编解码"/>
    </div>
    <div class="content">
      <el-input type="textarea" :rows="4" v-model="input"/>
      <el-button-group>
        <el-button type="primary" @click="encode">URL编码</el-button>
        <el-button type="primary" @click="decode">URL解码</el-button>
      </el-button-group>
      <el-input type="textarea" :rows="4" v-model="output"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UrlDecode',
  data () {
    return {
      input: '',
      output: ''
    }
  },
  methods: {
    encode: function () {
      this.output = encodeURIComponent(this.input)
    },
    decode: function () {
      this.output = decodeURIComponent(this.input)
    },
    goBack: function () {
      window.location.href = 'http://lsy99.cn'
    }
  }
}
</script>

<style scoped>
#UrlDecode {
  display: flex;
  height: 100%;
  width: 100%;
  flex-direction: column;
}

.header {
  margin: 20px;
}

.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-right: 20px;
  margin-left: 20px;
}

.content > * {
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
