<template>
  <div>
    <br />
    <div class="text-center">
      <template>
        <div class="carousel-container">
          <b-carousel
            id="carousel-1"
            v-model="slide"
            :interval="8000"
            img-width="1920"
            img-height="1080"
            controls
            indicators
            background="#ababab"
            style="text-shadow: 1px 1px 2px #333"
            @sliding-start="onSlideStart"
            @sliding-end="onSlideEnd"
          >
            <b-carousel-slide>
              <template #img>
                <router-link to="/notice/1">
                <img
                  class="d-block img-fluid w-100"
                  width="1920"
                  height="1080"
                  src="../assets/pic3.png"
                  alt="image slot"
                />
              </router-link>
              </template>
            </b-carousel-slide>
            <b-carousel-slide>
              <template #img>
                <router-link to="/notice/2">
                <img
                  class="d-block img-fluid w-100"
                  width="1920"
                  height="1080"
                  src="../assets/pic1.png"
                  alt="image slot"
                />
              </router-link>
              </template>
              <h3>멀티캠퍼스에 헬스장 들어오다?</h3>
            </b-carousel-slide>
            <b-carousel-slide>
              <template #img>
                <router-link to="/searchMap">
                  <img
                    class="d-block img-fluid w-100"
                    width="1920"
                    height="1080"
                    src="../assets/pic2.png"
                    alt="image slot"
                /></router-link>
              </template>
              <h3>내 주변 헬스장 찾기</h3>
            </b-carousel-slide>

            <b-carousel-slide>
              <template #img>
                <router-link to="/notice/3">
                <img
                  class="d-block img-fluid w-100"
                  width="1920"
                  height="1080"
                  src="../assets/pic4.png"
                  alt="image slot"
                /></router-link>
              </template>
              <h3>꾸준한 건강관리를 통해 건강한 운동생활을</h3>
            </b-carousel-slide>
          </b-carousel>
        </div>
      </template>
    </div>
    <hr />
    <video-list></video-list>
  </div>
</template>

<script>
import VideoList from "@/components/video/VideoList.vue";

export default {
  name: "Home",
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  components: {
    VideoList,
  },
  methods: {
    onSlideStart(slide) {
      console.log(slide);
      this.sliding = true;
    },
    onSlideEnd(slide) {
      console.log(slide);
      this.sliding = false;
    },
  },
};
</script>
<style>
.text-center {
  text-align: center;
}

.carousel-container {
  max-width: 50%;
  margin: 0 auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
}

.overlay {
  position: relative;
  top: 0;
  bottom: 20em;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  opacity: 0;
  transition: 0.5s ease;
}

.carousel-container:hover .overlay {
  opacity: 1;
}
</style>
