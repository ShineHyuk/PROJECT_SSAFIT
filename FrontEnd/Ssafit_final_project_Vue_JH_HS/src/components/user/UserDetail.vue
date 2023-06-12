<template>
  <div class="container">
    <br />
    <b-row cols="1">
      <b-card bg-variant="light">
        <div class="d-flex justify-content-center">
          <h1>회원정보</h1>
        </div>
        <b-form-group label="아이디:" label-for="id" content-cols-sm="6" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="person"></b-icon>
            </b-input-group-prepend>
            <b-form-input readonly type="text" id="id" v-model="user.id"></b-form-input>
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
            <b-form-input type="password" id="password" v-model="user.password"></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group label="이름:" label-for="name" content-cols-sm="6" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="person-badge"></b-icon>
            </b-input-group-prepend>
            <b-form-input type="text" id="name" v-model="user.name"></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group label="이메일:" label-for="email" content-cols-sm="6" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="envelope"></b-icon>
            </b-input-group-prepend>
            <b-form-input readonly type="email" id="email" v-model="user.email"></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group label="나이:" label-for="age" content-cols-sm="6" label-cols-sm="3" label-align-sm="right">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="calendar2-date"></b-icon>
            </b-input-group-prepend>
            <b-form-input type="number" id="age" v-model="user.age"></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group label="선호 부위 :" content-cols-sm="6" label-cols-sm="3" label-align-sm="right" class="mb-0">
          <b-input-group class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="emoji-heart-eyes"></b-icon>
            </b-input-group-prepend>
            <b-form-select name="exerciseCode" id="exerciseName" v-model="user.exerciseCode">
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
            <b-button variant="outline-primary" @click="updateUser" class="align-self-center" style="width: 60px"
              >수정</b-button
            >
            <b-button variant="outline-danger" @click="deleteUser" class="align-self-center" style="width: 60px"
              >삭제</b-button
            >
          </div>
        </div>
      </b-card>
    </b-row>
    <br />
    <hr/>
    <h2>찜 목록</h2>
    <b-row class="d-flex justify-content-between">
      <b-card
        v-for="(basket, index) in baskets"
        :key="index"
        :title="getBasketTitle(basket.videoId)"
        :img-src="getBasketImage(basket)"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 20rem; height: 450px; position: relative"
        class="mb-2"
      >
        <div class="d-flex justify-content-end" style="position: absolute; bottom: 0; right: 0">
          <b-button
            @click="showVideo(basket.videoId)"
            variant="outline-primary"
            class="align-self-center"
            style="width: 150px"
            >영상바로가기</b-button
          >
        </div>
      </b-card>
    </b-row>
    <br/>
    <hr/>
    <div v-if="this.loginUser != null ? this.loginUser.rank === 0 : false">
      <div>리뷰 관리</div>
      <br/>
      <AdminReviewList/>
    </div>
  </div>
</template>
<script>
import { mapState } from 'vuex';
import AdminReviewList from "../video/AdminReviewList.vue";
export default {
  name: 'UserDetail',
  components: {
    AdminReviewList,
  },
  methods: {
    updateUser() {
      this.$store.dispatch('updateUser', this.user);
    },
    deleteUser() {
      if (confirm('회원을 탈퇴하시겠습니까?')) {
        this.$store.dispatch('deleteUser', this.user.id);
      }
    },
    async getUser(id) {
      await this.$store.dispatch('getUser', id);
    },

    getBasketImage(basket) {
      return `https://img.youtube.com/vi/${basket.videoId}/0.jpg`;
    },

    getBasketTitle(videoId) {
      let index;
      for (let i = 0; i < this.videos.length; i++) {
        if (this.videos[i].videoId === videoId) {
          index = i;
        }
      }
      console.log(this.videos[index]);
      return this.videos[index].videoTitle;
    },

    showVideo(videoId) {
      return this.$router.push(`/video/${videoId}`);
    },
  },
  computed: {
    ...mapState(['user', 'baskets', 'videos', "loginUser"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split('/');
    const id = pathName[pathName.length - 1];
    this.getUser(id);
    this.$store.dispatch('getBaskets', id);
    this.$store.dispatch('getVideos');
  },
};
</script>
<style scoped></style>
