<template>
  <div id="ToolsMain">
    <el-container>
      <el-header>
        <p @click="goBack" class="title">李西西的
          <del>代码</del>
          屎山
        </p>
      </el-header>
      <el-container>
        <el-aside width="20%">
          <el-menu :router="true">
            <el-submenu index="1">
              <template slot="title">
                <!--                <i class="el-icon-location"></i>-->
                <span>工具</span>
              </template>
              <el-menu-item index="UrlDecode"><i class="el-icon-link"/>URL编解码</el-menu-item>
              <el-menu-item index="BingPicture"><i class="el-icon-picture-outline"/>开发中2</el-menu-item>
              <el-menu-item index="IdiomSolitaire"><i class="el-icon-d-arrow-right"/>开发中3</el-menu-item>
              <el-menu-item index="ColorMap"><i class="el-icon-picture"/>开发中4</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <!--                <i class="el-icon-location"></i>-->
                <span>数据库作业</span>
              </template>
              <el-menu-item index="DatabaseLab1">作业1</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <router-view/>
      </el-container>
      <el-footer>
        <a href="http://beian.miit.gov.cn/" target="_blank">黑ICP备20000491号-1</a>
        <img src="@/assets/mps-license.png" alt="备案图标" class="msc-icon"/>
        <a class="license" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=23010302000977"
           target="_blank">黑公网安备 23010302000977号</a>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
    name: 'ToolsMain',
    methods: {
        goBack: function () {
            window.location.href = 'http://lsy99.cn'
        }
    }
}
</script>

<style scoped>
#ToolsMain, .el-container {
  display: flex;
  height: 100%
}

.el-header, .el-footer {
  display: flex;
  align-items: center;
  color: #fff;
  background-color: #409EFF;
}

.el-footer {
  justify-content: center;
}

.el-menu {
  height: 100%;
}

.title {
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}

a:link, a:visited {
  text-decoration: none;
  color: #cccccc;
}

a:active, a:hover {
  text-decoration: underline;
  color: #ffffff;
}

a {
  font-size: 12px;
}

.msc-icon{
  margin-left: 20px;
  margin-right: 5px;
}

</style>
