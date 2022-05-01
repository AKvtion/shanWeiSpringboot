<!--
 * @Author: fauchrad
 * @Date: 2021-11-02 09:58:00
 * @LastEditTime: 2021-12-27 11:06:35
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \vue\src\views\Home.vue
-->
<template>
  <div style="padding: 10px">
    <el-card>
      <div id="myChart" :style="{width: '1100px', height: '800px'}"></div>
    </el-card>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Home",
  data() {
    return {}
  },
  mounted() {
    this.drawLine();
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$root.echarts.init(document.getElementById('myChart'))
      let option = {
        title: {
          text: '各部门比例统计图',
          subtext: '真实数据',
          left: 'left'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          trigger: 'item'
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        series: [
          {
            name: '用户比例',
            type: 'pie',
            radius: [50, 250],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: []
          }
        ]
      }
      request.get("/user/count").then(res => {
        res.data.forEach(item => {
          option.series[0].data.push({name: item.address, value: item.count})
        })
        // 绘制图表
        myChart.setOption(option);
      })

    }
  }
}
</script>

<style scoped>

</style>
