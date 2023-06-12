<template>
  <div class="review-form">
    <h3>리뷰 수정하기</h3>
    <form @submit.prevent="submitForm">
      <div>
        <label for="writer">작성자: {{ loginUserId }}</label
        ><br />
      </div>
      <div>
        <label for="review">리뷰 내용:</label>
        <textarea
          rows="15"
          id="review"
          v-model="formData.content"
          required
        ></textarea>
      </div>
      <button type="submit">수정 완료</button>
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  props: {
    videoId: {
      type: String,
      required: true,
    },
    reviewId: {
      type: Number,
      required: true,
    },
    content: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      formData: {
        writer: "",
        videoId: "",
        content: this.content,
        reviewId: "",
      },
    };
  },
  methods: {
    submitForm() {
      this.formData.writer = this.loginUserId;
      this.formData.videoId = this.videoId;
      this.formData.reviewId = this.reviewId;
      this.$store.dispatch("modifyReview", this.formData);
      alert("수정되었습니다.");
      this.resetForm();
    },
    resetForm() {
      this.formData.videoId = "";
      this.formData.content = "";
      this.formData.reviewId = "";
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    loginUserId() {
      return this.loginUser.id;
    },
    getContent() {
      return this.content;
    },
  },
};
</script>

<style scoped>
.review-form {
  background-color: #f2f2f2;
  padding: 20px;
  border-radius: 5px;
}

.review-form h3 {
  margin-top: 0;
}

.review-form form {
  margin-top: 10px;
}

.review-form label {
  display: block;
  margin-bottom: 5px;
}

.review-form input,
.review-form textarea {
  width: 100%;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.review-form button[type="submit"] {
  margin-top: 10px;
  padding: 5px 10px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.review-form button[type="submit"]:hover {
  background-color: #555;
}
</style>
