<template>
  <div class="container">
    <br />
    <div class="text-center">
      <h1>자유게시판</h1>
    </div>
    <div class="d-flex justify-content-between">
      <div class="mt-3 pt-3">{{ boardCnt }}개의 게시글이 등록되어 있습니다.</div>
      <b-button @click="getUser" variant="outline-primary" block style="max-width: 13%">게시글 작성</b-button>
    </div>
    <hr />
    <div class="overflow-auto">
      <b-table-simple hover :items="boards" class="text-center">
        <colgroup>
          <col style="width: 10%" />
          <col style="width: 40%" />
          <col style="width: 15%" />
          <col style="width: 10%" />
          <col style="width: 25%" />
        </colgroup>
        <b-thead>
          <b-tr>
            <b-th>번호</b-th>
            <b-th>제목</b-th>
            <b-th>글쓴이</b-th>
            <b-th>조회수</b-th>
            <b-th>등록일</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr v-for="(board, index) in pageBoardList" :key="index">
            <b-td>{{ (currentPage - 1) * perPage + (index + 1) }}</b-td>
            <b-td>
              <b-link :to="`/board/${board.boardId}`">{{ board.title }} [{{ getReviews(board.boardId) }}]</b-link>
            </b-td>
            <b-td>{{ board.writer }}</b-td>
            <b-td>{{ board.viewCnt }}</b-td>
            <b-td>{{ board.regDate }}</b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
    </div>
    <b-pagination v-model="currentPage" :total-rows="boardCnt" :per-page="perPage" align="center"></b-pagination>
    <div>
      <b-form inline class="d-flex justify-content-center">
        <b-form-select v-model="mode">
          <b-form-select-option value="1">제목</b-form-select-option>
          <b-form-select-option value="2">내용</b-form-select-option>
          <b-form-select-option value="3">제목+내용</b-form-select-option>
        </b-form-select>
        <b-form-input type="text" v-model="keyword" />
        <b-button variant="outline-dark" @click="search">검색</b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex';

export default {
  name: 'BoardList',
  data() {
    return {
      currentPage: 1,
      perPage: 10,
      mode: 1,
      keyword: '',
    };
  },
  computed: {
    ...mapState(['boards', 'loginUser', 'comments']),
    ...mapGetters(['boardCnt']),
    // 페이지를 나눠주는 방법
    pageBoardList() {
      return this.boards.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
    },
  },

  created() {
    this.$store.dispatch('getBoards');
    this.$store.dispatch('getAllComments');
  },

  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch('getBoards', payload);
    },
    getUser() {
      if (this.loginUser != null) {
        return this.$router.push('/board/write');
      } else {
        alert('로그인이 필요합니다.');
        return this.$router.push('/login');
      }
    },
    getReviews(boardId) {
      const matchingComments = this.comments.filter((comment) => comment.boardId === boardId);
      return matchingComments.length;
    },
  },
};
</script>

<style></style>
