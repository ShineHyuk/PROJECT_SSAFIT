<template>
  <div class="container">
    <br />
    <b-card>
      <div class="d-flex justify-content-center">
        <h3>게시글 수정</h3>
      </div>
      <b-form @submit="updateNotice">
        <b-form-group id="title" label="제목:" label-for="title">
          <b-form-input
            id="title"
            v-model="notice.title"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="writer" label="작성자:" label-for="writer">
          <b-form-input
            readonly
            id="writer"
            v-model="notice.writer"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notice.content"
            rows="6"
            max-rows="20"
            required
          ></b-form-textarea>
        </b-form-group>
        <div class="d-flex justify-content-end">
          <b-button
            type="submit"
            variant="outline-primary"
            class="align-self-center"
            style="width: 60px"
            >수정</b-button
          >
        </div>
      </b-form>
    </b-card>
  </div>
</template>

<script>
// 게시글 수정에는 id 값이 없으므로 게시글을 가져올 수 없다
// 하지만 이미 vuex의 state에 notice를 저장해 놨으므로 불러올 수 있다.
import { mapState } from "vuex";

export default {
  name: "NoticeUpdate",

  computed: {
    ...mapState(["notice"]),
  },
  methods: {
    updateNotice() {
      event.preventDefault(); // 폼의 기본 동작인 페이지 새로고침을 막음
      // if (this.notice.title === "" || this.notice.content === "") {
      //   alert("제목이나 내용이 비어있습니다.");
      //   return;
      // }
      let updateNotice = {
        noticeId: this.notice.noticeId,
        title: this.notice.title,
        content: this.notice.content,
      };
      this.$store.dispatch("updateNotice", updateNotice);
    },
  },
};
</script>

<style></style>
