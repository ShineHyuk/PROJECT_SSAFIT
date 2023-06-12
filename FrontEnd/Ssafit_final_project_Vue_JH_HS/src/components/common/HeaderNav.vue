<template>
  <header>
    <nav class="header-nav">
      <div class="route">
        <router-link to="/">
          <img src="../../assets/Logo.png" alt="logo" class="logoImg" />
        </router-link>
        <router-link to="/notice">공지사항</router-link>
        <router-link to="/board">자유게시판</router-link>
        <router-link to="/searchMap">헬스장찾기</router-link>
      </div>
      <div class="logo-container">
        <router-link to="/" class="logo">SSAFIT</router-link>
      </div>
      <div class="buttons-container">
        <span class="name" v-if="this.loginUser" style="color: white"
          >{{ this.loginUser.name }}님 안녕하세요!</span
        >
        <a href="#" v-if="getUser" @click="logout">로그아웃</a>
        <router-link v-else to="/login">로그인</router-link>
        <router-link v-if="getUser" :to="`/user/${getUser.id}`"
          >마이페이지</router-link
        >
        <router-link v-else :to="{ name: 'Regist' }">회원가입</router-link>
        <router-link to="/user/search">사용자검색</router-link>
      </div>
    </nav>
  </header>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "HeaderNav",
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    getUser() {
      return this.loginUser;
    },
  },
};
</script>
<style>
header {
  height: 70px;
  background: linear-gradient(to right, white, #3bb7ff);
  line-height: 70px;
  padding: 0px 10px;
}

header a {
  margin-left: 10px;
}

.name {
  margin-right: 2em;
}

.logo-container {
  position: absolute;
  left: 50%;
  top: 2.5em;
  transform: translate(-50%, -50%);
}

.route a {
  color: #4270c4;
}

.buttons-container a {
  color: white;
}

.header-nav {
  display: flex;
  justify-content: space-between;
}

.logoImg {
  height: 70px;
}

.logo {
  font-size: 2rem;
  font-weight: bold;
  color: white;
}
</style>
