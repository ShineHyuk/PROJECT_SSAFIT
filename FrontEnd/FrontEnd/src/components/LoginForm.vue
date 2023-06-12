<template>
  <div class="container">
    <br />
    <b-row cols="2" class="d-flex justify-content-center">
      <b-card bg-variant="light">
        <div class="d-flex justify-content-center">
          <h2>로그인</h2>
        </div>
        <b-form-group label="아이디:" label-for="id" content-cols-sm="7" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="person-fill"></b-icon>
            </b-input-group-prepend>
            <b-form-input type="text" id="id" placeholder="아이디를 입력하세요." v-model="id"></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group
          label="비밀번호:"
          label-for="password"
          content-cols-sm="7"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="key-fill"></b-icon>
            </b-input-group-prepend>
            <b-form-input type="password" id="password" placeholder="비밀번호를 입력하세요." v-model="password" @keyup.enter="login"></b-form-input>
          </b-input-group>
        </b-form-group>

        <div class="d-flex justify-content-center">
          <b-button variant="outline-secondary " @click="login" class="align-self-center" style="width: 150px"
            >로그인</b-button
          >
        </div>
      </b-card>
    </b-row>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  data() {
    return {
      id: '',
      password: '',
    };
  },
  methods: {
    login() {
      let user = {
        id: this.id,
        password: this.password,
      };

      this.$store.dispatch('getLoginUser', user);
    },
  },
};
</script>

<style></style>
