// src/store/index.js
import { createStore } from 'vuex';
import User from '../models/User'; // 确保路径正确

export default createStore({
  state: {
    user: new User()
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    }
  },
  actions: {
    updateUser({ commit }, userData) {
      const user = userData ? new User(
        userData.username,
        userData.userId,
        userData.userPermissions,
        userData.companyName
      ) : new User();
      commit('setUser', user);
    },
    clearUser({ commit }) {
      commit('setUser', new User());
    }
  },
  getters: {
    getUser: state => state.user,
    getUsername: state => state.user.username,
    getUserId: state => state.user.userId,
    getUserPermissions: state => state.user.userPermissions,
    getCompanyName: state => state.user.companyName
  }
});