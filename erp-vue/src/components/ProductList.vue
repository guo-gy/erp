<template>
  <el-container>
    <el-header>
      <h2>产品列表</h2>
    </el-header>
    <el-main>
      <el-table :data="products" style="width: 100%">
        <el-table-column prop="name" label="产品名称" />
        <el-table-column prop="price" label="价格" />
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      products: []
    };
  },
  async created() {
    const response = await axios.get('http://localhost:8080/api/products');
    this.products = response.data;
  }
};
</script>

<style scoped>
/* 添加样式 */
</style>
