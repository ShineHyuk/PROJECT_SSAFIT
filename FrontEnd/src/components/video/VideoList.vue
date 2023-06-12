<template>
  <div>
    <div class="category-button">
      <b-button variant="outline-primary" @click="getVideos">전체</b-button>
      <b-button variant="outline-primary" @click="categorize(100)"
        >전신</b-button
      >
      <b-button variant="outline-primary" @click="categorize(200)"
        >상체</b-button
      >
      <b-button variant="outline-primary" @click="categorize(300)"
        >하체</b-button
      >
      <b-button variant="outline-primary" @click="categorize(400)"
        >복부</b-button
      >
    </div>
    <div class="card-deck">
      <router-link
        v-for="video in videos"
        :to="`/video/${video.videoId}`"
        :key="video.videoId"
        class="card-link"
      >
        <div class="card">
          <div class="card-content">
            <div class="card-thumbnail">
              <img
                :src="getThumbnailUrl(video.videoId)"
                alt="Video Thumbnail"
                class="thumbnail"
              />
            </div>
            <div class="card-header">
              <h5 class="card-title">{{ video.videoTitle }}</h5>
              <p class="card-youtuber">{{ video.youtuber }}</p>
            </div>
          </div>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  computed: {
    ...mapState(["videos"]),
  },
  mounted() {
    this.getVideos();
  },
  methods: {
    ...mapActions(["getVideos", "getVideosByPart"]),
    getThumbnailUrl(videoId) {
      return `https://img.youtube.com/vi/${videoId}/0.jpg`;
    },
    categorize(exerciseCode) {
      this.$store.dispatch("getVideosByPart", exerciseCode);
    },
  },
};
</script>

<style scoped>
.category-button {
  padding-left: 20px;
  padding-bottom: 20px;
}
.card-deck {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  width: 100%;
}

.card-link {
  width: 23%;
  margin-left: 20px;
  margin-bottom: 20px;
  text-decoration: none;
}

.card {
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(43, 117, 214, 0.801);
  border-color: rgba(43, 117, 214, 0.363);
  padding: 20px;
  height: 100%;
  transition: background-color 0.3s ease;
}

.card:hover {
  background-color: rgba(18, 18, 18, 0.1);
}

.card-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  flex-grow: 1;
}

.card-thumbnail {
  display: flex;
  align-items: flex-end;
  justify-content: center;
  margin-top: auto;
}

.thumbnail {
  width: 19vw;
  height: auto;
  border-radius: 5px;
  margin-bottom: 10px;
}

.card-header {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  height: 100%;
  width: 100%;
}

.card-title,
.card-youtuber {
  margin: 0;
  color: #000000;
}
</style>
