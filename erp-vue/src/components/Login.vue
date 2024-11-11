<template>
  <el-container>
    <el-header>
      <h2>登录</h2> <!-- 登录标题 -->
    </el-header>
    <el-main>
      <el-form @submit.prevent="login"> <!-- 防止表单默认提交 -->
        <el-form-item label="用户名">
          <el-input v-model="username" required /> <!-- 用户名输入框 -->
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="password" required /> <!-- 密码输入框 -->
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button> <!-- 登录按钮 -->
        </el-form-item>
      </el-form>
      <el-divider></el-divider> <!-- 分隔线 -->
      <el-form @submit.prevent="register"> <!-- 防止表单默认提交 -->
        <el-form-item label="注册用户名">
          <el-input v-model="registerUsername" required /> <!-- 注册用户名输入框 -->
        </el-form-item>
        <el-form-item label="注册密码">
          <el-input type="password" v-model="registerPassword" required /> <!-- 注册密码输入框 -->
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="register">注册</el-button> <!-- 注册按钮 -->
        </el-form-item>
      </el-form>
      <p v-if="message">{{ message }}</p> <!-- 显示登录消息 -->
    </el-main>
  </el-container>
</template>

<script>
import axios from 'axios'; // 导入axios用于HTTP请求
import { ElMessage } from 'element-plus'; // 导入Element Plus消息提示组件

export default {
  data() {
    return {
      username: '', // 用户名
      password: '', // 密码
      registerUsername: '', // 注册用户名
      registerPassword: '', // 注册密码
      message: '' // 登录消息
    };
  },
  methods: {
    async login() {
      try {
        // 发送登录请求
        const response = await axios.post('http://localhost:8080/api/auth/login', {
          username: this.username,
          password: this.password
        });
        this.message = response.data; // 设置返回的消息
        ElMessage.success('登录成功'); // 显示成功消息
      } catch (error) {
        this.message = '登录失败'; // 设置失败消息
        ElMessage.error('登录失败'); // 显示失败消息
      }
    },
    async register() {
      try {
        // 发送注册请求
        const response = await axios.post('http://localhost:8080/api/auth/register', {
          username: this.registerUsername,
          password: this.registerPassword
        });
        this.message = response.data; // 设置返回的消息
        ElMessage.success('注册成功'); // 显示成功消息
      } catch (error) {
        this.message = '注册失败'; // 设置失败消息
        ElMessage.error('注册失败'); // 显示失败消息
      }
    }
  }
};
</script>

<style scoped>
/* 添加样式 */
</style>
