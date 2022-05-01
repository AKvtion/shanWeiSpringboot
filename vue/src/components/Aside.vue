<!--
 * @Author: your name
 * @Date: 2021-11-02 09:58:00
 * @LastEditTime: 2021-12-22 15:43:24
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \vue\src\components\Aside.vue
-->
<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        :default-active="path"
        router
    >
      <el-menu-item index="/home">主页</el-menu-item>

      <el-menu-item index="/news"><i class="el-icon-menu"></i>新闻管理</el-menu-item>

      <el-submenu index="1" v-if="user.role === 1">
        <template #title><i class="el-icon-user"></i>用户管理</template>
        <el-menu-item index="/user">用户列表</el-menu-item>
      </el-submenu>

      <el-submenu index="2">
        <template #title><i class="el-icon-message"></i>公告管理</template>
        <el-menu-item index="/plank">公告栏</el-menu-item>

      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Aside",
  data() {
    return {
      user: {},
      path: this.$route.path   // 设置默认高亮的菜单
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)

    // 请求服务端，确认当前登录用户的 合法信息
    request.get("/user/" + this.user.id).then(res => {
      if (res.code === '0') {
        this.user = res.data
      }
    })
  }
}
</script>

<style scoped>

</style>
