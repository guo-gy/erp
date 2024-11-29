<template>
  <div>
    <h1>销售管理</h1>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="4">
        <el-input v-model="targetName" placeholder="输入买家名称"></el-input>
      </el-col>
      <el-col :span="4">
        <el-input v-model="productName" placeholder="输入商品名称"></el-input>
      </el-col>
      <el-col :span="4">
        <el-input v-model="price" placeholder="输入商品单价"></el-input>
      </el-col>
      <el-col :span="4">
        <el-input v-model.number="quantity" placeholder="输入商品数量" type="number"></el-input>
      </el-col>
      <el-col :span="4">
        <el-button @click="addOrder">新增订单</el-button>
      </el-col>
    </el-row>
    <el-table :data="orders" style="width: 100%">
      <el-table-column prop="id" label="订单ID" />
      <el-table-column prop="productName" label="商品名称" />
      <el-table-column prop="targetName" label="买家" />
      <el-table-column prop="quantity" label="数量" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="money" label="总价" />
    </el-table>
  </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
  name: 'OrderManagement',
  data() {
    return {
      companyId: localStorage.getItem('companyId'),
      orders: [],
      targetName: '',
      productName: '',
      quantity: null,
      price: null,
    };
  },
  mounted() {
    this.fetchOrders();
    this.getPermission();
  },
  methods: {
    async getPermission() {
      const moduleId = 2;
      const userId = localStorage.getItem('userId');
      const response = await axios.get(`http://localhost:8080/api/permission/${userId}/${moduleId}`);
      this.permission = response.data.data;
    },
    async fetchOrders() {
      const response = await axios.get(`http://localhost:8080/api/order/${this.companyId}/origin`);
      this.orders = response.data.data;
      for (const order of this.orders) {
        try {
          const response1 = await axios.get(`http://localhost:8080/api/product/${order.productId}`);
          order.productName = response1.data.data;
          const response2 = await axios.get(`http://localhost:8080/api/company/${order.targetId}/name`);
          order.targetName = response2.data.data;
          order.money = order.price * order.quantity;
        } catch (error) {
          order.productName = '获取商品名称失败';
        }
      }
    },
    async addOrder() {
      const request = {
        originName: localStorage.getItem('companyName'),
        targetName: this.targetName,
        productName: this.productName,
        price: this.price,
        quantity: this.quantity,
      };
      const response = await axios.post('http://localhost:8080/api/order/addorder', request);
      console.log(response);
      if (response.data.success) {
        this.fetchOrders();
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
