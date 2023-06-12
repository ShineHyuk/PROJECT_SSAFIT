<template>
  <div id="app">
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
    />
    <header-nav />
    <router-view />
    <footer-nav />
  </div>
</template>
<script>
import HeaderNav from "@/components/common/HeaderNav.vue";
import FooterNav from "./components/common/FooterNav.vue";
export default {
  name: "App",
  components: {
    HeaderNav,
    FooterNav,
  },
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
  font-family: "Maplestory_Light";
}
@font-face {
  font-family: "JUA";
  src: url("../public/fonts/BMJUA_ttf.ttf");
}
@font-face {
  font-family: "HANNA_11yrs";
  src: url("../public/fonts/BMHANNA_11yrs_ttf.ttf");
}
@font-face {
  font-family: "Maplestory_Light";
  src: url("../public/fonts/Maplestory\ Light.ttf");
}
</style>
