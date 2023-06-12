<template>
  <div class="container">
    <br />
    <b-card>
      <div class="d-flex justify-content-center">
        <h3>게시글 작성</h3>
      </div>
      <b-form @submit="writeNotice">
        <b-form-group id="title" label="제목:" label-for="title">
          <b-form-input id="title" v-model="title" type="text" placeholder="제목을 입력하세요." required></b-form-input>
        </b-form-group>

        <b-form-group id="writer" label="작성자:" label-for="writer">
          <b-form-input readonly id="writer" v-model="this.loginUser.id"></b-form-input>
        </b-form-group>

        <b-form-group id="content" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="content"
            rows="6"
            max-rows="20"
            placeholder="내용을 입력하세요."
            required
          ></b-form-textarea>
        </b-form-group>
        <div class="d-flex justify-content-end">
          <b-button type="submit" variant="outline-primary" class="align-self-center" style="width: 60px"
            >등록</b-button
          >
        </div>
      </b-form>
    </b-card>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'NoticeWrite',
  data() {
    return {
      title: '',
      writer: '',
      content: '',
    };
  },
  methods: {
    writeNotice() {
      event.preventDefault(); // 폼의 기본 동작인 페이지 새로고침을 막음
      let notice = {
        noticeId: null,
        title: this.title,
        writer: this.getUser.id,
        content: this.content,
      };
      console.log(notice);
      this.$store.dispatch('writeNotice', notice);
    },
  },
  computed: {
    ...mapState(['loginUser']),
    getUser() {
      return this.loginUser;
    },
  },
};
</script>

<style></style>
