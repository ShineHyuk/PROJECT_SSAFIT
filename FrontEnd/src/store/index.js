import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import router from "../router";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

// 자주 쓰는 URL 설정
const API_BASE_URL = "http://localhost:9999/ssafit";
const USER_API_URL = `${API_BASE_URL}/user`;
const BOARD_API_URL = `${API_BASE_URL}/board`;
const COMMENT_API_URL = `${API_BASE_URL}/comment`;
const VIDEO_API_URL = `${API_BASE_URL}/video`;
const REVIEW_API_URL = `${API_BASE_URL}/review`;
const BASKET_API_URL = `${API_BASE_URL}/basket`;
const FOLLOW_API_URL = `${API_BASE_URL}/follow`;
const NOTICE_API_URL = `${API_BASE_URL}/notice`;

export default new Vuex.Store({
  plugins: [createPersistedState()],

  state: {
    users: [],
    searchUsers: [],
    user: {},
    loginUser: null,
    randomUser: null,

    // board
    boards: [],
    board: {},

    // comment
    comments: [],
    comment: {},

    // video
    video: {},
    videos: [],
    partVideos: [],
    dislikedReviews: [],
    reviews: [],
    review: {},
    like: [],

    // basket
    baskets: [],

    // follow
    follows: [],

    // board
    notices: [],
    notice: {},
  },
  getters: {
    userCnt: function (state) {
      return state.users.length;
    },
    searchUserCnt: function (state) {
      return state.searchUsers.length > 0 ? state.searchUsers.length : null;
    },

    // board
    boardCnt(state) {
      return state.boards.length;
    },

    // comment
    commentCnt(state) {
      return state.comments.length;
    },

    // video
    videoCnt: function (state) {
      return state.videos.length;
    },
    reviewCnt: function (state) {
      return state.reviews.length;
    },

    // notice
    noticeCnt(state) {
      return state.notices.length;
    },
  },
  mutations: {
    CREATE_USER: function (state, user) {
      state.users.push(user);
    },
    GET_USERS: function (state, users) {
      state.users = users;
    },
    GET_USER: function (state, user) {
      state.user = user;
    },
    UPDATE_USER(state, user) {
      state.user = user;
    },
    SEARCH_NAME: function (state, users) {
      state.searchUsers = users;
    },
    GET_LOGIN_USER: function (state, user) {
      state.loginUser = user;
    },
    LOGOUT: function (state) {
      state.loginUser = null;
      return router.push("/");
    },
    GET_RANDOM_USER: function (state, user) {
      state.randomUser = user;
    },

    // board
    GET_BOARDS(state, boards) {
      state.boards = boards;
    },
    GET_BOARD(state, board) {
      state.board = board;
    },
    WRITE_BOARD(state, board) {
      state.boards.push(board);
    },

    UPDATE_BOARD(state, board) {
      state.board = board;
    },

    // comment
    CREATE_COMMENT: function (state, comment) {
      state.comments.push(comment);
    },
    GET_COMMENTS: function (state, comments) {
      state.comments = comments;
    },
    GET_ALL_COMMENTS(state, comments) {
      state.comments = comments;
    },
    // video
    GET_VIDEOS(state, videos) {
      state.videos = videos;
    },
    GET_VIDEO(state, video) {
      state.video = video;
    },
    GET_PARTVIDEOS(state, partVideos) {
      state.partVideos = partVideos;
    },

    // review
    CREATE_REVIEW(state, review) {
      state.reviews.push(review);
    },
    GET_REVIEWS(state, reviews) {
      state.reviews = reviews;
    },
    UPDATE_REVIEW(state, { videoId, reviewId }) {
      const review = state.reviews.find(
        (r) => r.videoId === videoId && r.reviewId === reviewId
      );
      if (review) {
        state.review = review;
      }
    },
    UPDATE_REVIEW_LIKE(state, { videoId, reviewId }) {
      const review = state.reviews.find(
        (r) => r.videoId === videoId && r.reviewId === reviewId
      );
      if (review) {
        state.review.reviewLike = review.reviewlike;
      }
    },
    UPDATE_REVIEW_DISLIKE(state, { videoId, reviewId }) {
      const review = state.reviews.find(
        (r) => r.videoId === videoId && r.reviewId === reviewId
      );
      if (review) {
        state.review.reviewDisLike = review.reviewDislike;
      }
    },
    GET_LIKE(state, like) {
      state.like = like;
    },
    GET_DISLIKED_REVIEWS(state, dislikedReviews){
      state.dislikedReviews = dislikedReviews;
    },

    // basket
    ADD_BASKET: function (state, basket) {
      state.baskets.push(basket);
    },
    GET_BASKETS(state, baskets) {
      state.baskets = baskets;
    },

    // follow
    GET_FOLLOWS(state, follows) {
      state.follows = follows;
    },

    ADD_FOLLOW(state, follow) {
      state.follows.push(follow);
    },

    // notice
    GET_NOTICES(state, notices) {
      state.notices = notices;
    },
    GET_NOTICE(state, notice) {
      state.notice = notice;
    },
    WRITE_NOTICE(state, notice) {
      state.notices.push(notice);
    },

    UPDATE_NOTICE(state, notice) {
      state.notice = notice;
    },
  },
  actions: {
    createUser: function ({ commit }, user) {
      console.log(user);
      const API_URL = `${USER_API_URL}`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then((res) => {
          commit("CREATE_USER", res.data);
          console.log(user);
          console.log(res.data);
          alert("회원등록이 완료되었습니다.");
          router.push("/user");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getUsers: function ({ commit }) {
      const API_URL = `${USER_API_URL}`;
      return axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log("user setting...");
          console.log(res.data);
          commit("GET_USERS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateUser: function ({ commit }, user) {
      console.log(commit);
      console.log(user);
      const API_URL = `${USER_API_URL}`;
      axios({
        url: API_URL,
        method: "PUT",
        data: user,
      })
        .then((res) => {
          console.log(res);
          commit("UPDATE_USER", user);

          alert("수정 완료!");
          router.push("/user");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteUser: function ({ state }, id) {
      const API_URL = `${USER_API_URL}/${id}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          alert("회원탈퇴가 완료되었습니다.");
          let index;
          for (let i = 0; i < state.users.length; i++) {
            if (state.users[i].id === id) {
              index = i;
            }
          }
          // 저장된 유저를 지운다
          state.users.splice(index, 1);
          // 로그인 상태를 null로 바꾼다
          state.loginUser = null;
          return router.push("/login");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getUser: function ({ commit }, id) {
      const API_URL = `${USER_API_URL}/${id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_USER", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    searchName: function ({ commit }, searchCondition) {
      const API_URL = `${USER_API_URL}/search`;
      console.log(searchCondition);
      axios({
        url: API_URL,
        method: "GET",
        params: searchCondition,
      })
        .then((res) => {
          console.log("유저를 찾아서 res로 받아왔다");
          console.log(res.data);
          commit("SEARCH_NAME", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getLoginUser: function ({ commit }, user) {
      const API_URL = `${USER_API_URL}/${user.id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          let resUser = res.data;
          if (resUser.id === user.id && resUser.password === user.password) {
            alert("로그인 성공!");
            commit("GET_LOGIN_USER", res.data);
            // 뒤로가기버튼
            router.push("/");
          } else {
            alert("로그인 실패");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getRandomUser: async function ({ commit }) {
      const API_URL = `https://random-data-api.com/api/users/random_user`;

      try {
        const res = await axios.get(API_URL);
        let user = {
          id: res.data.username,
          password: res.data.password,
          name: res.data.first_name,
          email: res.data.email,
          age: Math.floor(Math.random() * (99 - 20)) + 20,
          exerciseCode: Math.floor(Math.random() * 5) * 100,
        };
        commit("GET_RANDOM_USER", user);
      } catch (err) {
        console.log(err);
      }
    },

    // board
    getBoards({ commit }, payload) {
      let params = null;
      if (payload) params = payload;

      const API_URL = `${BOARD_API_URL}`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
        .then((res) => {
          console.log(res);
          commit("GET_BOARDS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getBoard({ commit }, payload) {
      const id = payload.userId;
      const boardId = payload.boardId;
      const API_URL = `${BOARD_API_URL}/${boardId}/${id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        // 가져오기를 성공했으면 mutations로 보낸다
        .then((res) => {
          console.log(res);
          commit("GET_BOARD", res.data);
        })
        // 실패 시 에러 내보내기
        .catch((err) => {
          console.log(err);
        });
    },

    writeBoard({ commit }, board) {
      // 요청을 보내줄 URL
      const API_URL = `${BOARD_API_URL}`;
      axios({
        url: API_URL,
        method: "POST",
        params: board,
      })
        // commit => 여기까지만 해도 게시글이 DB에 등록은 된다!!!!
        .then((res) => {
          // 상세보기가 불가능한 이유: spring에서 등록 시 success만 오기 때문에
          // 나중에 상세보기를 원할 시 등록 후 id 값을 줘야한다.
          console.log(res);
          commit("WRITE_BOARD", res.data);
          alert("게시글이 작성되었습니다.");
          // axios 에서 router.push는 $나 this가 필요없다.
          router.push(`/board/${res.data.boardId}`);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    updateBoard({ commit }, board) {
      const API_URL = `${BOARD_API_URL}`;
      axios({
        url: API_URL,
        method: "PUT",
        params: board,
      })
        // mutations으로 보내고
        // detail 상세 페이지 출력
        .then((res) => {
          console.log(res);
          commit("UPDATE_BOARD", res.data);
          alert("게시글 수정이 완료되었습니다.");
          router.push({
            name: "BoardDetail",
            params: { boardId: board.boardId },
          });
        })
        // 실패 시 에러 내보내기
        .catch((err) => {
          console.log(err);
        });
    },

    deleteBoard({ commit }, boardId) {
      const API_URL = `${BOARD_API_URL}/${boardId}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        // mutations으로 보낼 필요없다.
        // 그럼 BOARD삭제는 어떻게함?
        .then(() => {
          // commit을 안쓰면 난리가 나므로 그냥 써주기만 하자
          commit;
          alert("게시글이 삭제되었습니다.");
          router.push("/board");
        })
        // 실패 시 에러 내보내기
        .catch((err) => {
          console.log(err);
        });
    },

    // comment
    createComment({ commit, state }, comment) {
      const API_URL = `${COMMENT_API_URL}`;
      axios({
        url: API_URL,
        method: "POST",
        params: comment,
      })
        .then((res) => {
          console.log(res);
          if (!state.comments) {
            state.comments = [];
          }
          commit("CREATE_COMMENT", res.data);
          alert("댓글이 작성되었습니다.");
          return router.go(0);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getComments({ commit }, boardId) {
      const API_URL = `${COMMENT_API_URL}/${boardId}/`;
      axios({
        url: API_URL,
        method: "GET",
      })
        // 가져오기를 성공했으면 mutations로 보낸다
        .then((res) => {
          console.log(res);
          commit("GET_COMMENTS", res.data);
        })
        // 실패 시 에러 내보내기
        .catch((err) => {
          console.log(err);
        });
    },

    getAllComments({ commit }) {
      const API_URL = `${COMMENT_API_URL}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log(res);
          commit("GET_ALL_COMMENTS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    updateComment: function ({ commit }, comment) {
      console.log(commit);
      console.log(comment);
      const API_URL = `${COMMENT_API_URL}`;
      axios({
        url: API_URL,
        method: "PUT",
        data: comment,
      })
        .then(() => {
          alert("수정 완료!");
          router.go(0);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteComment: function ({ state }, commentId) {
      const API_URL = `${COMMENT_API_URL}/${commentId}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          alert("댓글이 삭제 되었습니다.");
          let index;
          for (let i = 0; i < state.comments.length; i++) {
            if (state.comments[i].commentId === commentId) {
              index = i;
            }
          }
          // 저장된 댓글을 지운다
          state.comments.splice(index, 1);

          router.go(0); // 이따 다시보기
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // follow
    getFollows({ commit }, userId) {
      console.log(userId);
      const API_URL = `${FOLLOW_API_URL}/${userId}/`;
      axios({
        url: API_URL,
        method: "GET",
      })
        // 가져오기를 성공했으면 mutations로 보낸다
        .then((res) => {
          console.log(res);
          commit("GET_FOLLOWS", res.data);
        })
        // 실패 시 에러 내보내기
        .catch((err) => {
          console.log(err);
        });
    },

    addFollow({ commit, state }, follow) {
      const API_URL = `${FOLLOW_API_URL}`;
      axios({
        url: API_URL,
        method: "POST",
        params: follow,
      })
        .then((res) => {
          console.log(res);
          if (!state.follows) {
            state.follows = [];
          }
          commit("ADD_FOLLOW", res.data);
          alert("팔로우 되었습니다.");
          return router.go(0);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    deleteFollow: function ({ state }, follow) {
      const API_URL = `${FOLLOW_API_URL}`;
      axios({
        url: API_URL,
        method: "DELETE",
        params: follow,
      })
        .then(() => {
          let index;
          for (let i = 0; i < state.follows.length; i++) {
            if (state.follows[i].followId === follow.followId) {
              index = i;
            }
          }
          // 저장된 댓글을 지운다
          state.follows.splice(index, 1);
          alert("팔로우를 취소했습니다.");
          return router.go(0);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // notice
    getNotices({ commit }, payload) {
      let params = null;
      if (payload) params = payload;

      const API_URL = `${NOTICE_API_URL}`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
        .then((res) => {
          console.log(res);
          commit("GET_NOTICES", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getNotice({ commit }, noticeId) {
      const API_URL = `${NOTICE_API_URL}/${noticeId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        // 가져오기를 성공했으면 mutations로 보낸다
        .then((res) => {
          console.log(res);
          commit("GET_NOTICE", res.data);
        })
        // 실패 시 에러 내보내기
        .catch((err) => {
          console.log(err);
        });
    },

    writeNotice({ commit }, notice) {
      // 요청을 보내줄 URL
      const API_URL = `${NOTICE_API_URL}`;
      axios({
        url: API_URL,
        method: "POST",
        params: notice,
      })
        // commit => 여기까지만 해도 게시글이 DB에 등록은 된다!!!!
        .then((res) => {
          // 상세보기가 불가능한 이유: spring에서 등록 시 success만 오기 때문에
          // 나중에 상세보기를 원할 시 등록 후 id 값을 줘야한다.
          console.log(res);
          commit("WRITE_NOTICE", res.data);
          alert("공지사항이 작성되었습니다.");
          // axios 에서 router.push는 $나 this가 필요없다.
          router.push(`/notice/${res.data.noticeId}`);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    updateNotice({ commit }, notice) {
      const API_URL = `${NOTICE_API_URL}`;
      axios({
        url: API_URL,
        method: "PUT",
        params: notice,
      })
        // mutations으로 보내고
        // detail 상세 페이지 출력
        .then((res) => {
          console.log(res);
          commit("UPDATE_NOTICE", res.data);
          alert("공지사항 수정이 완료되었습니다.");
          router.push(`/notice/${res.data.noticeId}`);
        })
        // 실패 시 에러 내보내기
        .catch((err) => {
          console.log(err);
        });
    },

    deleteNotice({ commit }, noticeId) {
      const API_URL = `${NOTICE_API_URL}/${noticeId}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        // mutations으로 보낼 필요없다.
        // 그럼 NOTICE삭제는 어떻게함?
        .then(() => {
          // commit을 안쓰면 난리가 나므로 그냥 써주기만 하자
          commit;
          alert("공지사항이 삭제되었습니다.");
          router.push("/notice");
        })
        // 실패 시 에러 내보내기
        .catch((err) => {
          console.log(err);
        });
    },

    // video
    getVideo: function ({ commit }, videoId) {
      // Added setVideos action
      const API_URL = `${VIDEO_API_URL}/${videoId}`;
      return axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log("video setting...");
          console.log(res.data);
          commit("GET_VIDEO", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getVideos: function ({ commit }) {
      // Added setVideos action
      const API_URL = `${VIDEO_API_URL}`;
      return axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log("videos setting...");
          console.log(res.data);
          commit("GET_VIDEOS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getVideosByPart: function ({ commit }, exerciseCode) {
      const API_URL = `${VIDEO_API_URL}/part/${exerciseCode}`;
      return axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_VIDEOS", res.data);
          commit("GET_PARTVIDEOS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // review
    getAllLike: function ({ commit }, { userId }) {
      const API_URL = `${REVIEW_API_URL}/${userId}/like`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_LIKE", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    likeReview: function ({ commit }, { videoId, reviewId, userId }) {
      const API_URL = `${REVIEW_API_URL}/${videoId}/${reviewId}/${userId}/like`;
      axios({
        url: API_URL,
        method: "PUT",
      })
        .then(() => {
          commit("UPDATE_REVIEW_LIKE", { videoId, reviewId });
          alert("좋아요!");
          window.location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    },

    dislikeReview: function ({ commit }, { videoId, reviewId, userId }) {
      const API_URL = `${REVIEW_API_URL}/${videoId}/${reviewId}/${userId}/dislike`;
      axios({
        url: API_URL,
        method: "PUT",
      })
        .then(() => {
          commit("UPDATE_REVIEW_DISLIKE", { videoId, reviewId });
          alert("싫어요!");
          window.location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    undoLike: function ({ commit }, { videoId, reviewId, userId, which }) {
      const API_URL = `${REVIEW_API_URL}/${videoId}/${reviewId}/${userId}/${which}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          alert("취소되었습니다.");
          window.location.reload();
          commit;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createReview: function ({ commit }, formData) {
      const API_URL = `${REVIEW_API_URL}/${formData.videoId}`;
      axios({
        url: API_URL,
        method: "POST",
        data: formData,
      })
        .then((response) => {
          const createdReview = response.data;
          window.location.reload();
          commit("CREATE_REVIEW", createdReview);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getReviews: function ({ commit }, videoId) {
      const API_URL = `${REVIEW_API_URL}/${videoId}`;
      return axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_REVIEWS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getDislikedReviews: function({commit}){
      const API_URL = `${REVIEW_API_URL}/disliked`;
      return axios({
        url: API_URL,
        method: "GET",
      })
      .then((res) => {
        commit("GET_DISLIKED_REVIEWS", res.data);
      })
      .catch((err) =>{
        console.log(err);
      })
    },
    modifyReview: function ({ commit }, review) {
      const videoId = review.videoId;
      const reviewId = review.reviewId;
      const API_URL = `${REVIEW_API_URL}/${videoId}/${reviewId}`;
      axios({
        url: API_URL,
        method: "PUT",
        params: review,
      })
        .then((res) => {
          console.log(res);
          commit("UPDATE_REVIEW", review);
          window.location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    removeReview({ commit }, { videoId, reviewId }) {
      const API_URL = `${REVIEW_API_URL}/${videoId}/${reviewId}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          alert("삭제되었습니다.");
          window.location.reload();
          commit;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // basket
    getBaskets({ commit }, userId) {
      const API_URL = `${BASKET_API_URL}/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log(res);
          commit("GET_BASKETS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addBasket({ commit }, { userId, videoId }) {
      const API_URL = `${BASKET_API_URL}/${userId}/${videoId}`;
      axios({
        url: API_URL,
        method: "POST",
      })
        .then((res) => {
          console.log(res);
          commit("ADD_BASKET", { userId, videoId });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    removeBasket({ state }, { userId, videoId }) {
      const API_URL = `${BASKET_API_URL}/${userId}/${videoId}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then((res) => {
          console.log(res);
          let index;
          for (let i = 0; i < state.baskets.length; i++) {
            if (state.baskets[i].userId === userId) {
              index = i;
            }
          }
          state.baskets.splice(index, 1);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {},
});
