<template>
  <div class="container">
    <br />
    <b-card :title="notice.title" style="max-width: 80rem">
      <div class="d-flex justify-content-end">글쓴이 : {{ notice.writer }}</div>
      <!-- <p v-html="formattedText"></p> -->
      <b-card-text> {{ notice.content }} <br /> </b-card-text>
      <b-list-group flush>
        <b-list-group-item class="d-flex justify-content-end">작성일 : {{ notice.regDate }}</b-list-group-item>
      </b-list-group>
      <div class="d-flex justify-content-between">
        <div>
          <b-button :to="{ name: 'NoticeList' }" variant="secondary" class="align-self-center" style="width: 100px"
            >목록으로</b-button
          >
        </div>
        <div class="d-flex justify-content-end">
          <b-button v-if="getWriter" @click="moveUpdate" variant="primary" class="align-self-center" style="width: 60px"
            >수정</b-button
          >
          <b-button
            v-if="getWriter"
            @click="deleteNotice"
            variant="danger"
            class="align-self-center"
            style="width: 60px"
            >삭제</b-button
          >
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
// state에 있는 notice 가져와야 하므로
import { mapState } from 'vuex';

export default {
  name: 'NoticeDetail',

  data() {
    return {
      content: '',
      formattedText: '',
    };
  },

  // state에 있는 데이터를 가져온다
  computed: {
    // notice를 가져온다.
    ...mapState(['notice', 'loginUser']),

    getWriter() {
      if (this.loginUser === null) {
        return false;
      } else {
        if (this.notice.writer === this.loginUser.id) {
          return true;
        } else {
          return false;
        }
      }
    },
  },

  // mounted() {
  //   const textFromDB = this.notice.content;
  //   this.formattedText = textFromDB.replace(/\n/g, '<br>');
  // },

  // 컴포넌트가 만들어질 때
  created() {
    console.log(this.notice.regDate);
    // 전체 URL 정보를 가져온다.
    const pathName = new URL(document.location);
    console.log(pathName);
    // 여기서 pathname이라는 값이 /notice/id값 이다.
    console.log(pathName.pathname);
    // 이때 '/' 로 쪼개면 배열로 나오게 된다
    console.log(pathName.pathname.split('/'));
    // 이때 배열의 마지막 부분이 id 값이다.
    const name = pathName.pathname.split('/');
    const id = name[name.length - 1];
    // 잘 가져왔는지 확인해본다.
    console.log(id);

    // actions 호출 , 게시글을 가져온다.
    this.$store.dispatch('getNotice', id);
  },
  methods: {
    moveUpdate() {
      // vue의 script에서 router는 꼭 $를 써준다.
      this.$router.push({ name: 'NoticeUpdate' });
    },
    // 삭제 메소드
    deleteNotice() {
      if (confirm('공지사항을 삭제하시겠습니까?')) {
        this.$store.dispatch('deleteNotice', this.notice.noticeId);
      }
    },
  },
};
</script>

<style></style>
