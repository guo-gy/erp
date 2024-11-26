<template>
  <el-container class="login-container">
    <el-header>
      <h2 class="login-title">ERP系统-欢迎登录</h2>
    </el-header>
    <el-main>
      <el-card class="login-card">
        <el-form class="login-form">
          <el-form-item label="用户名" class="form-item">
            <el-input v-model="username" required placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item label="密码" class="form-item">
            <el-input type="password" v-model="password" required placeholder="请输入密码" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login" class="login-button">登录</el-button>
            <el-button type="success" @click="registerDialogVisible = true" class="register-button">注册</el-button>
          </el-form-item>
        </el-form>
      </el-card>
      <el-dialog title="注册" v-model="registerDialogVisible" @close="resetRegisterForm">
        <el-form class="register-form">
          <el-form-item label="公司名" class="form-item">
            <el-input v-model="registerCompanytname" required placeholder="请输入公司名" />
          </el-form-item>
          <el-form-item label="用户名" class="form-item">
            <el-input v-model="registerUsername" required placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item label="密码" class="form-item">
            <el-input type="password" v-model="registerPassword" required placeholder="请输入密码" />
          </el-form-item>
          <el-form-item label="确认密码" class="form-item">
            <el-input type="password" v-model="registerrePassword" required placeholder="请输入密码" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="register">注册</el-button>
            <el-button @click="registerDialogVisible = false">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { mapState, mapActions } from 'vuex';

export default {
  data() {
    return {
      username: '',
      password: '',
      message: '',
      registerDialogVisible: false,
      registerCompanytname: '',
      registerUsername: '',
      registerPassword: '',
      registerrePassword: ''
    };
  },
  computed: {
    ...mapState(['user'])
  },
  created() {
    if (this.user.userId!=null) {
      this.$router.push('/home');
    }
  },
  methods: {
    ...mapActions(['updateUser']),
    async login() {
      try {
        const response = await axios.post('http://10.27.233.81:8080/api/auth/login', {
          username: this.username,
          password: this.password
        });
        if (response.data.success == true) {
          ElMessage.success(response.data.message);
          this.updateUser(response.data.user);
          this.$router.push('/home');
        } else {
          ElMessage.error(response.data.message);
        }
      } catch (error) {
        ElMessage.error("系统异常");
      }
    },
    resetRegisterForm() {
      this.registerUsername = '';
      this.registerPassword = '';
    },
    async register() {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/register', {
          Companytname: this.registerCompanytname,
          username: this.registerUsername,
          password: this.registerPassword,
          repassword: this.registerrePassword
        });
        if (response.data.success == true) {
          ElMessage.success(response.data.message);
          this.registerDialogVisible = false;
        } else {
          ElMessage.error(response.data.message);
        }
      } catch (error) {
        ElMessage.error('系统异常');
      }
    }
  }
};
</script>

<style scoped>
@import '../css/Login.css';
</style>
