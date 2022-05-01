<!--
 * @Author: fauchard
 * @Date: 2021-11-02 09:58:00
 * @LastEditTime: 2021-12-14 21:31:46
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \vue\src\layout\Layout.vue
-->
<template>
  <div>
    <!--    头部-->
    <Header :user="user" />

    <!--    主体-->
    <div style="display: flex">
      <!--      侧边栏-->
      <Aside />
      <!--      内容区域-->
      <router-view
        style="flex: 1"
        @userInfo="refreshUser"
      />
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
import request from "@/utils/request";

export default {
  name: "Layout",
  components: {
    Header,
    Aside,
  },
  data() {
    return {
      user: {},
    };
  },
  created() {
    this.refreshUser();
  },
  methods: {
    refreshUser() {
      let userJson = sessionStorage.getItem("user");
      if (!userJson) {
        return;
      }
      let userId = JSON.parse(userJson).id;
      request.get("/user/" + userId).then((res) => {
        this.user = res.data;
      });
    },
  },
};
</script>

<style scoped>

</style>
