<template>
  <div>
    <br />
    <div>
      <h2>헬스장 찾기</h2>
      <div class="map-area">
        <div class="searchbox">
          <div>
            <div>찾고자 하는 위치 검색</div>
            <input type="text" value="역삼역" @keyup.enter="searchPlace" />
          </div>
          <div class="results">
            <div class="place" v-for="rs in search.results" :key="rs.id">
              <h5>{{ rs.place_name }}</h5>
            </div>
          </div>
        </div>
        <div>
          <div id="map" style="width: 500px; height: 500px"></div>
          <div id="message">마커를 클릭하여 해당 헬스장으로 이동합니다.</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "KakaoMap",
  data() {
    return {
      infowindow: null,
      search: {
        keyword: null,
        pgn: null,
        results: [],
      },
      map: null,
      markers: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
      this.searchDefaultPlace();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },
  methods: {
    searchDefaultPlace() {
      const keyword = "역삼동 헬스장";
      if (keyword.length === 0) {
        return;
      }

      const ps = new window.kakao.maps.services.Places();

      ps.keywordSearch(keyword, (data, status, pgn) => {
        this.search.keyword = keyword;
        this.search.pgn = pgn;
        this.search.results = data;
        this.displayMarker(data);
      });
    },
    searchPlace(e) {
      const keyword = e.target.value.trim() + " 헬스장";
      if (keyword.length === 0) {
        return;
      }

      const ps = new window.kakao.maps.services.Places();

      ps.keywordSearch(keyword, (data, status, pgn) => {
        this.search.keyword = keyword;
        this.search.pgn = pgn;
        this.search.results = data;
        this.displayMarker(data);
      });
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5012428, 127.0395859),
        level: 4,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
        this.markers = [];
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(position.y, position.x)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);

        this.markers.forEach((marker, index) => {
          kakao.maps.event.addListener(marker, "click", () => {
            const place = markerPositions[index];
            const url = `https://place.map.kakao.com/${place.id}`;
            window.open(url, "_blank");
          });
        });
      }
    },
  },
};
</script>
<style>
h2 {
  display: flex;
  justify-content: center;
  align-items: center;
}

.map-area {
  display: flex;
  justify-content: center;
  align-items: center;
}
.searchbox {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
  margin-right: 5%;
}
.message {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px; /* Adjust margin-top as needed */
}
</style>
