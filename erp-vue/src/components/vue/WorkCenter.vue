<template>
    <div>
        <h1>任务中心</h1>
        <el-row :gutter="20" style="margin-bottom: 20px;">
            <el-col :span="8">
                <el-input v-model="targetId" placeholder="输入指派员工名称"></el-input>
            </el-col>
            <el-col :span="8">
                <el-input v-model="name" placeholder="输入任务信息"></el-input>
            </el-col>
            <el-col :span="8">
                <el-button @click="addWork">任务指派</el-button>
            </el-col>
        </el-row>
        <el-table :data="works" style="width: 100%">
            <el-table-column prop="id" label="任务ID" />
            <el-table-column prop="name" label="商品名称" />
            <el-table-column prop="originName" label="指派人" />
            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button v-if="scope.row.status === false" @click="updWork(scope.row.id)"
                        type="success">完成</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
    name: 'WorkManagement',
    data() {
        return {
            userId: localStorage.getItem('userId'),
            works: [],
            targetId: '',
            name: '',
        };
    },
    mounted() {
        this.fetchWorks();
    },
    methods: {
        async fetchWorks() {
            const response = await axios.get(`http://localhost:8080/api/order/${this.userId}/target`);
            this.works = response.data.data;
            for (const work of this.works) {
                try {
                    const response = await axios.get(`http://localhost:8080/api/company/${order.originId}/name`);
                    order.originName = response.data.data;
                } catch (error) {
                    order.productName = '获取派出人员名称失败';
                }
            }
        },
        async addWork() {
            const request = {
                targetId: this.targetId,
                originId: this.userId,
                name: this.name,
            };
            const response = await axios.post('http://localhost:8080/api/work/addwork', request);
            console.log(response);
            if (response.data.success) {
                this.fetchInventories();
                ElMessage.success(response.data.message);
            } else {
                ElMessage.error(response.data.message);
            }
        },
        async updWork(id) {
            const request = {
                id
            };
            const response = await axios.post('http://localhost:8080/api/work/updwork', request);
            if (response.success) {
                this.fetchWorks();
                ElMessage.success(response.message);
            } else {
                ElMessage.error(response.message);
            }
        }
    },
}
</script>

<style scoped>
/* 商品管理样式 */
</style>