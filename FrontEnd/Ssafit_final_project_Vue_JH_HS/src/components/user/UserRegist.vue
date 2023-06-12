<template>
  <div class="container">
    <br />
    <b-row cols="1">
      <b-card bg-variant="light">
        <div class="d-flex justify-content-center">
          <h1>회원가입</h1>
        </div>

        <b-form-group label="아이디:" label-for="id" content-cols-sm="6" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="person"></b-icon>
            </b-input-group-prepend>
            <b-form-input type="text" id="id" v-model="id" placeholder="아이디를 입력하세요."></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group
          label="비밀번호:"
          label-for="password"
          content-cols-sm="6"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="key"></b-icon>
            </b-input-group-prepend>
            <b-form-input
              type="password"
              id="password"
              v-model="password"
              placeholder="비밀번호를 입력하세요."
            ></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group label="이름:" label-for="name" content-cols-sm="6" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="person-badge"></b-icon>
            </b-input-group-prepend>
            <b-form-input type="text" id="name" v-model="name" placeholder="이름을 입력하세요."></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group label="이메일:" label-for="email" content-cols-sm="6" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="envelope"></b-icon>
            </b-input-group-prepend>
            <b-form-input type="email" id="email" v-model="email" placeholder="이메일을 입력하세요."></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group label="나이:" label-for="age" content-cols-sm="6" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="calendar2-date"></b-icon>
            </b-input-group-prepend>
            <b-form-input type="number" id="age" v-model="age" placeholder="나이를 입력하세요."></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group label="선호 부위 :" content-cols-sm="6" label-cols-sm="3" label-align-sm="right" class="mb-0">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="emoji-heart-eyes"></b-icon>
            </b-input-group-prepend>
            <b-form-select name="exerciseCode" id="exerciseName" v-model="exerciseCode">
              <option value="0">없음</option>
              <option value="100">전신</option>
              <option value="200">상체</option>
              <option value="300">하체</option>
              <option value="400">복부</option>
            </b-form-select>
          </b-input-group>
        </b-form-group>
        <div style="max-width: 76%">
          <div class="d-flex justify-content-end">
            <b-button variant="outline-primary" @click="regist" class="align-self-center" style="width: 60px"
              >등록</b-button
            >
            <b-button variant="outline-danger" @click="getInfoFromAPI" class="align-self-center" style="width: 60px"
              >랜덤</b-button
            >
          </div>
        </div>
      </b-card>
    </b-row>
  </div>
</template>
<script>
import { mapState } from 'vuex';
export default {
  name: 'UserList',
  data() {
    return {
      id: '',
      password: '',
      name: '',
      email: '',
      age: '',
      exerciseCode: 0,
      img: '',
    };
  },

  created() {
    // 유저 가져오기
    this.$store.dispatch('getUsers');
  },

  methods: {
    async getInfoFromAPI() {
      await this.$store.dispatch('getRandomUser');
      this.id = this.randomUser.id;
      this.password = this.randomUser.password;
      this.name = this.randomUser.name;
      this.email = this.randomUser.email;
      this.age = this.randomUser.age;
      this.exerciseCode = this.randomUser.exerciseCode;
    },
    regist() {
      if (this.id === '' || this.password === '' || this.name === '' || this.email === '' || this.age <= 0) {
        alert('모든 내용을 입력해주세요');
        return;
      }

      for (let i = 0; i < this.users.length; i++) {
        if (this.users[i].id == this.id) {
          alert('중복되는 id가 있습니다.');
          return;
        }
      }

      for (let i = 0; i < this.users.length; i++) {
        if (this.users[i].email == this.email) {
          alert('중복되는 email이 있습니다.');
          return;
        }
      }

      let user = {
        id: this.id,
        password: this.password,
        name: this.name,
        email: this.email,
        age: this.age,
        exerciseCode: this.exerciseCode,
        img: '#',
      };

      console.log(user);

      this.$store.dispatch('createUser', user);
    },
  },
  computed: {
    ...mapState(['randomUser', 'users']),
  },
};
</script>
