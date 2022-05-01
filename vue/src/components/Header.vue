<!--
 * @Author: fauchard
 * @Date: 2021-11-02 09:58:00
 * @LastEditTime: 2021-11-29 16:20:03
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \vue\src\components\Header.vue
-->
<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 216px; padding-left: 30px; font-weight: bold; color: dodgerblue">汕职院新闻后台管理系统</div>
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-dropdown>
        <span class="el-dropdown-link">
          <!-- <el-avatar :size="30" :src="user.avatar" style="position: relative; top: 10px"></el-avatar> -->
           {{ user.nickName }}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: ['user'],
  data() {
    return {

    }
  },
  created() {
  }
}
</script>

<style scoped>

</style>
