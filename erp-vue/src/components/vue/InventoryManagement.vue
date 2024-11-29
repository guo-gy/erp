<template>
  <div v-if="this.permission < 0">
    <h1>无权限访问</h1>
    <p>您没有权限访问此页面。</p>
  </div>
  <div v-if="this.permission === 0">
    <h1>库存管理</h1>
    <p>您仅拥有读权限，无权调整库存。</p>
    <el-table :data="inventories" style="width: 100%">
      <el-table-column prop="productName" label="商品名称" />
      <el-table-column prop="quantity" label="数量" />
    </el-table>
  </div>
  <div v-if="this.permission > 0">
    <h1>库存管理</h1>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="8">
        <el-input v-model="name" placeholder="输入商品名称"></el-input>
      </el-col>
      <el-col :span="8">
        <el-input v-model.number="quantity" placeholder="输入商品数量" type="number"></el-input>
      </el-col>
      <el-col :span="8">
        <el-button @click="updInventory">调整库存</el-button>
      </el-col>
    </el-row>
    <el-table :data="inventories" style="width: 100%">
      <el-table-column prop="productName" label="商品名称" />
      <el-table-column prop="quantity" label="数量" />
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button @click="delInventory(scope.row.id)" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
  name: 'InventoryManagement',
  data() {
    return {
      permission: -1,
      companyId: localStorage.getItem('companyId'),
      inventories: [],
      name: '',
      quantity: 0,
    };

  },
  mounted() {
    this.getPermission();
    this.fetchInventories();
  },
  methods: {
    async getPermission() {
      const moduleId = 2;
      const userId = localStorage.getItem('userId');
      const response = await axios.get(`http://localhost:8080/api/permission/${userId}/${moduleId}`);
      this.permission = response.data.data;
    },
    async fetchInventories() {
      const response = await axios.get(`http://localhost:8080/api/inventory/${this.companyId}`);
      this.inventories = response.data.data;
      for (const inventory of this.inventories) {
        try {
          const response = await axios.get(`http://localhost:8080/api/product/${inventory.productId}`);
          inventory.productName = response.data.data;
        } catch (error) {
          inventory.productName = '获取商品名称失败';
        }
      }
    },
    async updInventory() {
      const request = {
        companyId: this.companyId,
        productName: this.name,
        quantity: this.quantity,
      };
      const response = await axios.post('http://localhost:8080/api/inventory/updinventory', request);
      console.log(response);
      if (response.data.success) {
        this.fetchInventories();
        ElMessage.success(response.data.message);
      } else {
        ElMessage.error(response.data.message);
      }
    },
    async delInventory(id) {
      const request = {
        id: id
      };
      const response = await axios.post('http://localhost:8080/api/inventory/delinventory', request)
      if (response.data.success) {
        this.fetchInventories();
        ElMessage.success(response.data.message);
      } else {
        ElMessage.error(response.data.message);
      }
    },

  },

}

</script>

<style scoped>
/* 商品管理样式 */
</style>
<!-- End of Selection -->
