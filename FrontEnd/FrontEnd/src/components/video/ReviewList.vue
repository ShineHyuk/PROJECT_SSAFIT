<template>
  <div class="review">
    <div v-if="reviewCnt">
      <div class="header">
        <h5>{{ reviewCnt }}개의 리뷰가 있습니다.</h5>
        <b-button
          v-b-modal.modal-1
          variant="outline-secondary"
          @click="showReviewForm"
        >
          리뷰 작성
        </b-button>
      </div>
      <b-modal
        id="modal-1"
        title="BootstrapVue"
        hide-footer
        hide-header
        v-if="showForm"
      >
        <ReviewForm :videoId="video.videoId" />
      </b-modal>
      <b-modal id="modal-2" title="BootstrapVue" hide-footer hide-header>
        <ReviewModifyForm
          v-if="showModifyForm"
          :videoId="video.videoId"
          :reviewId="selectedReviewId"
          :content="reviewContent"
        />
      </b-modal>
      <div class="review-box" v-for="(review, index) in reviews" :key="index">
        <div class="review-header">
          <div class="writer-time">
            <span class="writer">{{ review.writer }}</span>
            <span class="time">{{ formatDate(review.regDate) }}</span>
            <div class="update-button" v-if="checkUser(review.writer)">
              <b-button
                v-b-modal.modal-2
                @click="modifyReviewForm(review.reviewId, review.content)"
                class="modify-button"
                variant="outline-success"
              >
                수정
              </b-button>
              <b-button
                variant="outline-danger"
                class="delete-button"
                @click="deleteReview(review.videoId, review.reviewId)"
                style="margin-right: 1%"
              >
                삭제
              </b-button>
            </div>
          </div>
          <div class="likes">
            <span class="like-count">{{ review.reviewLike }}</span>
            <span class="like-icon">명에게 도움됨</span>
          </div>
        </div>
        <div class="review-content">
          {{ review.content }}
        </div>
        <div class="likebuttons">
          <b-button
            variant="outline-success"
            class="like-button"
            @click="like(review.videoId, review.reviewId)"
            style="margin-right: 1%"
            v-if="!ifLiked(review.reviewId)"
          >
            좋아요
          </b-button>
          <b-button
            variant="success"
            class="like-button"
            @click="like(review.videoId, review.reviewId)"
            style="margin-right: 1%"
            v-else
          >
            좋아요
          </b-button>
          <b-button
            variant="outline-danger"
            class="dislike-button"
            @click="dislike(review.videoId, review.reviewId)"
            v-if="!ifDisLiked(review.reviewId)"
          >
            싫어요
          </b-button>
          <b-button
            variant="danger"
            class="dislike-button"
            @click="dislike(review.videoId, review.reviewId)"
            v-else
          >
            싫어요
          </b-button>
        </div>
      </div>
    </div>

    <div v-else>
      <div class="header">
        <h5>등록된 리뷰가 없습니다.</h5>
        <b-button
          v-b-modal.modal-1
          variant="outline-secondary"
          @click="showReviewForm"
        >
          첫 번째 리뷰를 작성해주세요!
        </b-button>
      </div>
      <b-modal
        id="modal-1"
        title="BootstrapVue"
        hide-footer
        hide-header
        v-if="showForm"
      >
        <ReviewForm :videoId="video.videoId" />
      </b-modal>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import ReviewForm from "./ReviewForm.vue";
import ReviewModifyForm from "./ReviewModifyForm.vue";

export default {
  props: {
    video: {
      type: Object,
      required: true,
    },
  },
  name: "ReviewList",
  components: {
    ReviewForm,
    ReviewModifyForm,
  },
  methods: {
    ...mapActions([
      "likeReview",
      "dislikeReview",
      "removeReview",
      "getAllLike",
      "undoLike",
    ]),

    like(videoId, reviewId) {
      if (this.isLoggedIn()) {
        if (this.ifLiked(reviewId)) {
          const userId = this.loginUser.id;
          const which = "like";
          this.undoLike({ videoId, reviewId, userId, which });
        } else {
          if (this.ifDisLiked(reviewId)) {
            alert("이미 싫어요한 리뷰입니다.");
          } else {
            const userId = this.loginUser.id;
            this.likeReview({ videoId, reviewId, userId });
          }
        }
      } else {
        alert("로그인이 필요합니다.");
      }
    },
    dislike(videoId, reviewId) {
      if (this.isLoggedIn()) {
        if (this.ifDisLiked(reviewId)) {
          const userId = this.loginUser.id;
          const which = "dislike";
          this.undoLike({ videoId, reviewId, userId, which });
        } else {
          if (this.ifLiked(reviewId)) {
            alert("이미 좋아요한 리뷰입니다.");
          } else {
            const userId = this.loginUser.id;
            this.dislikeReview({ videoId, reviewId, userId });
          }
        }
      } else {
        alert("로그인이 필요합니다.");
      }
    },
    ifLiked(reviewId) {
      const like = this.$store.state.like;
      for (let i = 0; i < like.length; i++) {
        if (
          like[i].reviewId === reviewId &&
          like[i].userId === this.loginUser.id &&
          like[i].liked == true
        ) {
          return true;
        }
      }
      return false;
    },
    ifDisLiked(reviewId) {
      const like = this.$store.state.like;
      for (let i = 0; i < like.length; i++) {
        if (
          like[i].reviewId === reviewId &&
          like[i].userId === this.loginUser.id &&
          like[i].disliked == true
        ) {
          return true;
        }
      }
      return false;
    },
    formatDate(timestamp) {
      const date = new Date(timestamp);
      const formattedDate = date.toISOString().slice(0, 19).replace("T", " ");
      return formattedDate;
    },
    isLoggedIn() {
      return this.loginUser != null;
    },
    deleteReview(videoId, reviewId) {
      this.removeReview({ videoId, reviewId });
    },

    modifyReviewForm(reviewId, content) {
      this.selectedReviewId = reviewId;
      this.reviewContent = content;
      this.showModifyForm = true;
    },
    showReviewForm() {
      if (this.isLoggedIn()) {
        this.showForm = true;
      } else {
        alert("로그인이 필요합니다.");
      }
    },
    hideReviewForm() {
      this.showForm = false;
    },
    hideModifyForm() {
      this.showModifyForm = false;
    },
    checkUser(writer) {
      if (this.isLoggedIn()) {
        if (this.loginUser.id === writer) {
          return true;
        } else {
          return false;
        }
      }
    },
  },
  computed: {
    ...mapState(["reviews"]),
    ...mapGetters(["reviewCnt"]),
    ...mapState(["loginUser"]),
  },
  data() {
    return {
      showForm: false,
      showModifyForm: false,
      selectedReviewId: null,
    };
  },
  created() {
    if (this.loginUser != null) {
      this.$store.dispatch("getAllLike", { userId: this.loginUser.id });
    }
  },
};
</script>

<style scoped>
.delete-button,
.modify-button {
  width: 4em;
}
.review {
  width: 80%;
  margin-left: auto;
  margin-right: auto;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.review-box {
  margin-bottom: 10px;
  border: 1px solid #ccc;
  padding: 1em;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
}

.review-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1em;
}

.writer-time {
  display: flex;
  align-items: center;
}

.writer {
  font-weight: bold;
  margin-right: 1em;
}

.time {
  color: #888;
}

.likes {
  display: flex;
  align-items: flex-end;
}

.likebuttons {
  display: flex;
  align-items: flex-end;
  justify-content: flex-end;
}

.update-button {
  display: flex;
  align-items: flex-end;
}

.like-count {
  font-weight: bold;
}

.like-icon {
  color: #888;
}

.review-content {
  background-color: #f9f9f9;
  padding: 1em;
}
</style>
