<template>
  <div class="review">
    <div>
      <div class="header">
      </div>
      <div class="review-box" v-for="(review, index) in dislikedReviews" :key="index">
        <div class="review-header">
          <div class="writer-time">
            <span class="writer">{{ review.writer }}</span>
            <span class="time">{{ formatDate(review.regDate) }}</span>
            <div class="update-button">
              <b-button
                variant="outline-danger"
                class="delete-button"
                @click="deleteReview(review.videoId, review.reviewId)"
                style="margin-right: 0%; width: 200%"
              >
                삭제
              </b-button>
            </div>
          </div>
          <div class="dislikes">
            <span class="dislike-count">{{ review.reviewDislike }}</span>
            <span class="dislike-icon">개의 싫어요</span>
          </div>
        </div>
        <div class="review-content">
          {{ review.content }}
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "AdminReviewList",
  methods: {
    ...mapActions([
      "removeReview",
      "getDislikedReviews",
    ]),

    formatDate(timestamp) {
      const date = new Date(timestamp);
      const formattedDate = date.toISOString().slice(0, 19).replace("T", " ");
      return formattedDate;
    },
    deleteReview(videoId, reviewId) {
      this.removeReview({ videoId, reviewId });
    },
  },
  computed: {
    ...mapState(["dislikedReviews"]),
    ...mapState(["loginUser"]),
  },
  created() {
      this.$store.dispatch("getDislikedReviews");
  },
};
</script>

<style scoped>
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

.dislikes {
  display: flex;
  align-items: flex-end;
}

.update-button {
  display: flex;
  align-items: flex-end;
}

.dislike-count {
  font-weight: bold;
}

.dislike-icon {
  color: #888;
}

.review-content {
  background-color: #f9f9f9;
  padding: 1em;
}
</style>
