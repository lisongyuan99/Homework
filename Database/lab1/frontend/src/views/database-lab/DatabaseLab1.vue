<template>
  <div class="main">
    <div class="header">
      <el-page-header @back="goBack" content="数据库作业1"/>
    </div>
    <div class="input-panel">
      <div class="input-row">
        <div class="line">
          <span class="tag">学号</span>
          <span class="switch"><el-switch v-model="idApplicable"/></span>
          <span class="input"><el-input v-model="id" :disabled="!idApplicable"/></span>
        </div>
        <div class="line">
          <span class="tag">姓名</span>
          <span class="switch"><el-switch v-model="nameApplicable"/></span>
          <span class="input"><el-input v-model="name" :disabled="!nameApplicable"/></span>
        </div>
        <div class="line">
          <span class="tag">年龄</span>
          <span class="switch"><el-switch v-model="ageApplicable"/></span>
          <span class="age-bar"><el-slider v-model="age" :disabled="!ageApplicable" range/></span>
        </div>
        <div class="line">
          <span class="tag">性别</span>
          <span class="switch"><el-switch v-model="genderApplicable"/></span>
          <span class="input"><el-input v-model="gender" :disabled="!genderApplicable"/></span>
        </div>
      </div>
      <div class="input-row">
        <div class="line">
          <span class="tag">班级</span>
          <span class="switch"><el-switch v-model="classApplicable"/></span>
          <span class="input"><el-input v-model="className" :disabled="!classApplicable"/></span>
        </div>
        <div class="line">
          <span class="tag">系别</span>
          <span class="switch"><el-switch v-model="deptApplicable"/></span>
          <span class="input"><el-input v-model="dept" :disabled="!deptApplicable"/></span>
        </div>
        <div class="line">
          <span class="tag">住址</span>
          <span class="switch"><el-switch v-model="addrApplicable"/></span>
          <span class="input"><el-input v-model="addr" :disabled="!addrApplicable"/></span>
        </div>
        <div class="line">
          <el-button type="primary" @click="search">搜索</el-button>
        </div>
      </div>
    </div>


    <el-divider/>
    <div class="sql-panel">sql语句:{{sql}}</div>
    <el-divider/>
    <div class="table-panel">
      <el-table :data="students" style="width: 100%" height="300">
        <el-table-column prop="id" label="学号"/>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="age" label="年龄"/>
        <el-table-column prop="gender" label="性别"/>
        <el-table-column prop="className" label="班级"/>
        <el-table-column prop="dept" label="系别"/>
        <el-table-column prop="addr" label="住址"/>
      </el-table>
      <!--      <ul id="example-2">-->
      <!--        {{students}}-->
      <!--        <table v-for="student in students" :key="student.id" border="1">-->
      <!--          <tr>-->
      <!--            <th>{{student.name}}</th>-->
      <!--            <th>{{student.id}}</th>-->
      <!--          </tr>-->
      <!--        </table>-->
      <!--      </ul>-->
    </div>
  </div>
</template>

<script>
export default {
  name: 'DatabaseLab1',
  data() {
    return {
      idApplicable: false,
      id: '',
      nameApplicable: false,
      name: '',
      ageApplicable: false,
      age: [0, 100],
      genderApplicable: false,
      gender: '',
      classApplicable: false,
      className: '',
      deptApplicable: false,
      dept: '',
      addrApplicable: false,
      addr: '',
      sql: '',
      students: []
    }
  },
  methods: {
    goBack: function () {
      window.location.href = 'http://lsy99.cn'
    },
    search: function () {
      const data = {}
      if (this.idApplicable) {
        data.id = this.id
      }
      if (this.nameApplicable) {
        data.name = this.name
      }
      if (this.ageApplicable) {
        data.age = this.age
      }
      if (this.genderApplicable) {
        data.gender = this.gender
      }
      if (this.classApplicable) {
        data.className = this.className
      }
      if (this.deptApplicable) {
        data.dept = this.dept
      }
      if (this.addrApplicable) {
        data.addr = this.addr
      }
      this.$axios.post('/api/database/lab1/search', data)
        .then((response) => {
          this.students = response.data.students
          this.sql = response.data.sql
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>
.main {
  width: 100%;
}

.header {
  margin: 20px;
}
.input-panel {
  display: flex;
}
.input-row {
  width: 35%;
  margin: 20px;
}



.table-panel, .sql-panel {
  margin: 20px;
}

.line {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.tag {
  margin-right: 20px;
}

.switch {
  margin-right: 20px;
}

.age-bar {
  width: 200px;
}
</style>
