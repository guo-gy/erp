<template>
    <div>
        <h1>商品管理</h1>
        <el-table :data="products" style="width: 100%">
            <el-table-column prop="id" label="商品ID" />
            <el-table-column prop="name" label="商品名称" />
            <el-table-column prop="price" label="价格" />
            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button @click="deleteProduct(scope.row.id)" type="danger">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-input v-model="name" placeholder="输入新商品名称"></el-input>
        <el-input v-model.number="price" placeholder="输入新商品价格" type="number"></el-input>
        <el-button @click="addProduct">添加商品</el-button>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'ProductManagement',
    data() {
        return {
            products: [],
            name: '',
            price: 0,
        };
    },
    methods: {
        fetchProducts() {
            axios.get('http://localhost:8080/api/products/get')
                .then(response => {
                    this.products = response.data;
                })
                .catch(error => {
                    console.error('获取商品失败:', error);
                });
        },
        addProduct() {
            const newProduct = {
                name: this.name,
                price: this.price,
            };
            axios.post('http://localhost:8080/api/products/create', newProduct)
                .then(() => {
                    //this.fetchProducts();

                    this.name = '';
                    this.price = 0;
                })
                .catch(error => {
                    console.error('添加商品失败:', error);
                });
        },
        deleteProduct(productId) {
            axios.delete(`http://localhost:8080/api/products/delete/${productId}`)
                .then(() => {
                    this.fetchProducts();
                })
                .catch(error => {
                    console.error('删除商品失败:', error);
                });
        },
    },
    mounted() {
        this.fetchProducts();
    },
};
</script>

<style scoped>
/* 商品管理样式 */
</style>
<!-- End of Selection -->
