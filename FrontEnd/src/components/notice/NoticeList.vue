<template>
  <div class="container">
    <br />
    <div class="text-center">
      <h1>공지사항</h1>
    </div>
    <div class="d-flex justify-content-between">
      <div class="mt-3 pt-3">{{ noticeCnt }}개의 공지사항이 등록되어 있습니다.</div>
      <b-button
        v-if="this.loginUser != null ? this.loginUser.rank === 0 : false"
        @click="writeNotice"
        variant="outline-primary"
        block
        style="max-width: 13%"
        >공지사항 작성</b-button
      >
    </div>
    <hr />
    <div class="overflow-auto">
      <b-table-simple hover :items="notices" class="text-center">
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
          <b-tr v-for="(notice, index) in pageNoticeList" :key="index">
            <b-td>{{ (currentPage - 1) * perPage + (index + 1) }}</b-td>
            <b-td>
              <b-link :to="`/notice/${notice.noticeId}`">{{ notice.title }}</b-link>
            </b-td>
            <b-td>{{ notice.writer }}</b-td>
            <b-td>{{ notice.viewCnt }}</b-td>
            <b-td>{{ notice.regDate }}</b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
    </div>
    <b-pagination v-model="currentPage" :total-rows="noticeCnt" :per-page="perPage" align="center"></b-pagination>
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
  name: 'NoticeList',
  data() {
    return {
      currentPage: 1,
      perPage: 10,
      mode: 1,
      keyword: '',
    };
  },
  computed: {
    ...mapState(['notices', 'loginUser']),
    ...mapGetters(['noticeCnt']),
    // 페이지를 나눠주는 방법
    pageNoticeList() {
      return this.notices.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
    },
  },

  created() {
    this.$store.dispatch('getNotices');
  },

  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch('getNotices', payload);
    },

    writeNotice() {
      return this.$router.push('/notice/write');
    },
  },
};
</script>

<style></style>
