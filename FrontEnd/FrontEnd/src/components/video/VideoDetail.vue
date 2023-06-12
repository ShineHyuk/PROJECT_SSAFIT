<template>
  <div>
    <br />
    <div class="video-container">
      <div class="video-wrapper">
        <iframe
          width="100%"
          height="100%"
          :src="getVideoEmbedUrl(video.videoId)"
          frameborder="0"
          allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture;"
          allowfullscreen
        ></iframe>
      </div>
      <div class="related-video">
        <h3>연관동영상</h3>
        <div v-for="relatedVideo in randomVideos" :key="relatedVideo.videoId">
          <div @click="redirectToVideo(relatedVideo.videoId)">
            <img
              :src="getThumbnailUrl(relatedVideo.videoId)"
              alt="Video Thumbnail"
              class="thumbnail"
            />
          </div>
        </div>
      </div>
    </div>
    <br />
    <div class="header-box">
      <h2 class="video-title">{{ video.videoTitle }}</h2>
    </div>
    <div class="youtuber-box">
      <div>
        <h4 class="youtuber">{{ video.youtuber }}</h4>
        <div class="view-count">조회수 {{ video.viewCnt }}회</div>
      </div>
      <template v-if="checkInBasket(video.videoId)">
        <b-button
          variant="warning"
          class="basket-button"
          @click="removeFromBasket"
          >찜취소</b-button
        >
      </template>
      <template v-else>
        <b-button
          variant="outline-warning"
          class="basket-button"
          @click="addToBasket"
          >찜하기</b-button
        >
      </template>
    </div>
    <hr />
    <ReviewList :video="video" />
  </div>
</template>

<script>
import axios from "axios";
import ReviewList from "./ReviewList.vue";
import { mapState, mapActions } from "vuex";

export default {
  data() {
    return {
      randomVideos: [],
    };
  },
  computed: {
    ...mapState(["video", "loginUser"]),
  },
  components: {
    ReviewList,
  },

  methods: {
    ...mapActions(["getVideo", "addBasket", "removeBasket"]),
    redirectToVideo(videoId) {
      const videoLink = "http://localhost:8080/video/" + videoId;
      window.location.href = videoLink;
    },
    getThumbnailUrl(videoId) {
      return `https://img.youtube.com/vi/${videoId}/0.jpg`;
    },
    getVideoEmbedUrl(videoId) {
      return `https://www.youtube.com/embed/${videoId}`;
    },
    getRandomVideos(partVideos) {
      const relatedVideos = partVideos;
      const randomVideos = [];

      while (randomVideos.length < 3) {
        const randomIndex = Math.floor(Math.random() * relatedVideos.length);
        const randomVideo = relatedVideos[randomIndex];

        if (
          !randomVideos.includes(randomVideo) &&
          randomVideo.videoId !== this.video.videoId
        ) {
          randomVideos.push(randomVideo);
        }
      }
      return randomVideos;
    },
    addToBasket() {
      if (this.loginUser != null) {
        const userId = this.loginUser.id;
        const videoId = this.$route.params.videoId;
        this.addBasket({ userId, videoId }).then(() => {
          alert("찜 목록에 추가되었습니다.");
          window.location.reload();
        });
      } else {
        alert("로그인이 필요합니다.");
      }
    },
    removeFromBasket() {
      if (this.loginUser != null) {
        const userId = this.loginUser.id;
        const videoId = this.$route.params.videoId;
        this.removeBasket({ userId, videoId }).then(() => {
          alert("찜 목록에서 제거되었습니다.");
          window.location.reload();
        });
      }
    },
    checkInBasket(videoId) {
      if (this.loginUser == null) {
        return false;
      }
      const baskets = this.$store.state.baskets;
      console.log(baskets);
      for (let i = 0; i < baskets.length; i++) {
        if (baskets[i].videoId === videoId) {
          return true;
        }
      }
      return false;
    },
    getRandomVideosByPart: function (exerciseCode) {
      const API_URL = `http://localhost:9999/ssafit/video/part/${exerciseCode}`;
      return axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          this.randomVideos = this.getRandomVideos(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    const videoId = this.$route.params.videoId;
    this.getVideo(videoId)
      .then(() => {
        this.getRandomVideosByPart(this.video.exerciseCode);
      })
      .catch((error) => {
        console.log(error);
      });
    this.$store.dispatch("getReviews", videoId);
    if (this.loginUser != null) {
      this.$store.dispatch("getBaskets", this.loginUser.id);
    }
  },
};
</script>

<style scoped>
.header-box {
  background-color: #f2f2f2;
  border-radius: 5px;
  border: 1px solid #ccc;
  padding: 1em;
  width: 80%;
  margin: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
}

.youtuber-box {
  width: 80%;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-left: auto;
  margin-right: auto;
}

.thumbnail {
  width: 100%;
  margin-bottom: 5%;
  cursor: pointer;
}

.basket-button {
  margin-top: 10px;
}

.video-title {
  align-items: center;
  text-align: center;
}

.related-video {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
  align-items: center;
  text-align: center;
  width: 13%;
  margin-left: 4%;
  margin-right: 9%;
}

.youtuber {
  padding-top: 10px;
  padding-bottom: 10px;
  color: #333333;
  width: 200%;
}

.view-count {
  color: #888;
}
.video-container {
  display: flex;
  justify-content: center;
}
.video-wrapper {
  display: flex;
  position: relative;
  width: 67%;
  height: 0;
  padding-bottom: 33.75%;
  overflow: hidden;
  margin-left: 9%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
}

.video-wrapper iframe {
  position: absolute;
  width: 100%;
  height: 100%;
}
</style>
