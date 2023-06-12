<template>
  <div class="container">
    <br />
    <b-form inline class="d-flex justify-content-center" @submit="searchUser">
      <b-form-group>
        <b-form-select v-model="key" size="lg">
          <b-form-select-option value="none">검색 기준</b-form-select-option>
          <b-form-select-option value="user_id">아이디</b-form-select-option>
          <b-form-select-option value="user_name">이름</b-form-select-option>
          <b-form-select-option value="user_email">이메일</b-form-select-option>
          <b-form-select-option value="user_age">나이</b-form-select-option>
        </b-form-select>
      </b-form-group>

      <b-form-group>
        <b-form-input
          type="text"
          name="word"
          v-model.trim="word"
          placeholder="검색어를 입력하세요."
          size="lg"
        />
      </b-form-group>

      <b-form-group>
        <b-form-select v-model="orderBy" size="lg">
          <b-form-select-option value="none">정렬 기준</b-form-select-option>
          <b-form-select-option value="user_id">아이디</b-form-select-option>
          <b-form-select-option value="user_name">이름</b-form-select-option>
          <b-form-select-option value="user_email">이메일</b-form-select-option>
          <b-form-select-option value="user_age">나이</b-form-select-option>
        </b-form-select>
      </b-form-group>

      <b-form-group>
        <b-form-select v-model="orderByDir" size="lg">
          <b-form-select-option value="asc">오름차순</b-form-select-option>
          <b-form-select-option value="desc">내림차순</b-form-select-option>
        </b-form-select>
      </b-form-group>

      <b-button variant="outline-primary" type="submit" size="lg">검색</b-button>
    </b-form>
    <br />
    <hr />

    <!-- 여기부터 사용자 목록 -->
    <div v-if="searchUserCnt">
      <b-table hover :items="searchFollowUser" :fields="fields" class="text-center">
        <!-- #cell(isFollowed) #cell(원하는행의key값)으로 데이터를 가져온다 -->
        <template #cell(isFollowed)="data">
          <!-- data.value => value값 즉 key값에 해당하는 value값 -->
          <!-- data.item  => items 즉 table에 바탕이되는 값들 중 하나 여기서는 유저 -->
          <b-icon v-if="data.value" icon="heart-fill" variant="danger" @click="toggleFollow(data.item)"></b-icon>
          <b-icon v-else icon="heart" variant="danger" @click="toggleFollow(data.item)"></b-icon>
        </template>
      </b-table>
    </div>
    <div v-else></div>
    <br />
  </div>
</template>
<script>
import { mapState, mapGetters } from 'vuex';
export default {
  name: 'UserSearch',
  data() {
    return {
      key: 'none',
      word: '',
      orderBy: 'none',
      orderByDir: 'asc',

      isHeartFilled: false,
      isPersonIcon: true,
      fields: [
        {
          label: '아이디',
          key: 'id',
          sortable: true,
        },
        {
          label: '이름',
          key: 'name',
          sortable: true,
        },
        {
          label: '이메일',
          key: 'email',
          sortable: true,
        },
        {
          label: '나이',
          key: 'age',
          sortable: true,
        },
        {
          label: '선호 운동',
          key: 'exerciseName',
          sortable: true,
        },
        {
          label: '팔로우',
          key: 'isFollowed',
          sortable: true,
          // Variant applies to the whole column, including the header and footer
          variant: 'danger',
        },
      ],
    };
  },
  methods: {
    searchUser(event) {
      event.preventDefault(); // 폼의 기본 동작인 페이지 새로고침을 막음

      const searchCondition = {
        key: this.key,
        word: this.word,
        orderBy: this.orderBy,
        orderByDir: this.orderByDir,
      };

      // 검색 조건을 사용하여 원하는 작업 수행
      // 예: this.$http.post('/search', searchCondition).then(response => { ... });

      // 예시로 검색 조건을 콘솔에 출력
      console.log('검색버튼을 누르면 메소드가 실행된다.');
      console.log(searchCondition);
      this.$store.dispatch('searchName', searchCondition);
      console.log('검색버튼을 누르면 실행되는 메소드 종료.');
    },

    // searchFollowUser(searchUsers) {
    //   // users를 순회하면서 follows에 존재하는 followingId와 일치하는 유저들에한해
    //   // boolean값을 추가한다.
    //   // some이라는 메소드는 순회하면서 조건을 만족하면 true 아니면 false를 내뱉는다.

    //   // window.location.reload();
    //   console.log('여기는 follower 찾는곳');
    //   console.log(this.follows);

    //   if (this.follows) {
    //     searchUsers.forEach((user) => {
    //       user.isFollowed = this.follows.some((follow) => follow.followingId === user.id);
    //     });
    //   } else {
    //     searchUsers.forEach((user) => {
    //       user.isFollowed = false;
    //     });
    //   }

    //   return searchUsers;
    // },

    toggleFollow(user) {
      // 팔로우 상태를 토글하고 API 요청 등 필요한 작업 수행
      // 예: this.$http.post('/toggle-follow', { userId: user.id }).then(response => { ... });
      if (this.loginUser === null) {
        return alert('로그인이 필요합니다.');
      }

      if (this.loginUser.id === user.id) {
        return alert('자기 자신을 팔로우 할 수 없습니다.');
      }

      const follow = {
        userId: this.loginUser.id,
        followingId: user.id,
      };

      if (user.isFollowed === false) {
        this.$store.dispatch('addFollow', follow).then(() => {
          this.$store.dispatch('getFollows', this.loginUser.id);

          console.log('여기는 팔로우 추가/삭제');
        });
      } else {
        this.$store.dispatch('deleteFollow', follow).then(() => {
          this.$store.dispatch('getFollows', this.loginUser.id);
          // this.$router.go(0);
          // this.$forceUpdate();
          // window.location.reload();
          console.log('여기는 팔로우 추가/삭제');
        });
      }
    },

    // toggleUnFollow(user) {
    //   if (this.loginUser === null) {
    //     return alert('로그인이 필요합니다.');
    //   }

    //   if (this.loginUser.id === user.id) {
    //     return alert('자기 자신을 팔로우 할 수 없습니다.');
    //   }

    //   const follow = {
    //     userId: this.loginUser.id,
    //     followingId: user.id,
    //   };
    //   this.$store.dispatch('deleteFollow', follow).then(() => {
    //     this.$store.dispatch('getFollows', this.loginUser.id);
    //     this.$forceUpdate();
    //   });
    // },
  },
  computed: {
    ...mapState(['searchUsers', 'loginUser', 'follows', 'users']),
    ...mapGetters(['searchUserCnt']),
    searchUserCnt() {
      console.log('이건 computed');
      console.log(this.searchUsers.length);
      return this.searchUsers.length;
    },

    searchFollowUser() {
      // users를 순회하면서 follows에 존재하는 followingId와 일치하는 유저들에한해
      // boolean값을 추가한다.
      // some이라는 메소드는 순회하면서 조건을 만족하면 true 아니면 false를 내뱉는다.

      // window.location.reload();
      console.log('여기는 follower 찾는곳');
      console.log(this.follows);

      if (this.follows) {
        this.searchUsers.forEach((user) => {
          user.isFollowed = this.follows.some((follow) => follow.followingId === user.id);
        });
      } else {
        this.searchUsers.forEach((user) => {
          user.isFollowed = false;
        });
      }

      return this.searchUsers;
    },
  },

  beforeMount() {
    console.log('이건 beforeMount');
  },
  mounted() {
    console.log('이건 mounted');
  },

  beforeUpdate() {
    console.log('이건 beforeUpdate');
  },
  updated() {
    console.log('이건 updated');
  },

  beforeDestroy() {
    console.log('이건 beforeDestroy');
  },
  destroyed() {
    console.log('이건 destroyed');
  },
  // 팔로우 하기전에 로그인 확인
  created() {
    console.log('웹이 만들어지면서 뜬다');
    if (this.loginUser !== null) {
      this.$store.dispatch('getFollows', this.loginUser.id);
    } else {
      this.$store.dispatch('getFollows', null);
    }
    this.$store.dispatch('getUsers');

    console.log(this.users);
    console.log(this.follows);
  },
};
</script>
<style scoped></style>
