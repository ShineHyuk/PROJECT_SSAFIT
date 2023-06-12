<template>
  <div class="container">
    <br />

    <b-card :title="board.title" style="max-width: 80rem">
      <div class="d-flex justify-content-end">글쓴이 : {{ board.writer }}</div>
      <!-- <p v-html="formattedText"></p> -->
      <b-card-text> {{ board.content }} <br /> </b-card-text>
      <b-list-group flush>
        <b-list-group-item class="d-flex justify-content-end">작성일 : {{ board.regDate }}</b-list-group-item>
      </b-list-group>
      <div class="d-flex justify-content-between">
        <div>
          <b-button :to="{ name: 'BoardList' }" variant="secondary" class="align-self-center" style="width: 100px"
            >목록으로</b-button
          >
        </div>
        <div class="d-flex justify-content-end">
          <b-button v-if="getWriter" @click="moveUpdate" variant="primary" class="align-self-center" style="width: 60px"
            >수정</b-button
          >
          <b-button v-if="getWriter" @click="deleteBoard" variant="danger" class="align-self-center" style="width: 60px"
            >삭제</b-button
          >
        </div>
      </div>
    </b-card>
    <div class="card mb-2">
      <div class="card-header bg-light"><i class="bi-chat-fill"></i>댓글 작성</div>
      <div class="card-body">
        <ul class="list-group list-group-flush">
          <li class="list-group-item">
            <label for="content">내용</label>
            <textarea class="form-control" rows="3" id="content" v-model="content"></textarea>
            <div class="d-flex justify-content-end">
              <button type="button" class="btn btn-dark mt-3" @click="createComment">작성</button>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="card mt-2" v-for="(comment, index) in comments" :key="index">
      <div class="card-header p-2">
        <table>
          <tbody>
            <tr class="align-middle">
              <td rowspan="2" class="pr-2">
                <i class="bi bi-person" style="font-size: 40px"></i>
              </td>
              <td class="ml">{{ comment.writer }}</td>
            </tr>
            <tr>
              <td>
                <font size="2">{{ comment.regDate }}</font>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="card-body">
        <template v-if="comment.editing">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">
              <label for="content">수정 내용</label>
              <textarea class="form-control" rows="3" id="content" v-model="comment.content"></textarea>
              <div class="d-flex justify-content-end">
                <span class="badge badge-primary" style="cursor: pointer"
                  ><a @click="updateComment(index)">저장</a></span
                ><span class="badge badge-danger" style="cursor: pointer"
                  ><a @click="cancelEdit(comment)">취소</a></span
                >
              </div>
            </li>
          </ul>
        </template>
        <template v-else>
          <!-- <p v-html="formattedCommentText(comment)"></p> -->
          <p class="card-text">{{ comment.content }}</p>
          <div v-if="getCommentWriter(comment === null ? null : comment.writer)" class="d-flex justify-content-end">
            <span class="badge badge-primary" style="cursor: pointer"><a @click="startEdit(comment)">수정</a></span
            ><span class="badge badge-danger" style="cursor: pointer"
              ><a @click="deleteComment(comment.commentId)">삭제</a></span
            >
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
// state에 있는 board를 가져와야 하므로
import { mapState } from 'vuex';

export default {
  name: 'BoardDetail',

  data() {
    return {
      content: '',
      formattedText: '',
    };
  },

  // state에 있는 데이터를 가져온다
  computed: {
    // board를 가져온다.
    ...mapState(['board', 'loginUser', 'comments']),

    getWriter() {
      if (this.loginUser === null) {
        return false;
      } else {
        if (this.board.writer === this.loginUser.id) {
          return true;
        } else {
          return false;
        }
      }
    },
  },

  // mounted() {
  //   const textFromDB = this.board.content;
  //   this.formattedText = textFromDB.replace(/\n/g, '<br>');
  // },

  // 컴포넌트가 만들어질 때
  created() {
    console.log(this.board.regDate);
    // 전체 URL 정보를 가져온다.
    const pathName = new URL(document.location);
    console.log(pathName);
    // 여기서 pathname이라는 값이 /board/id값 이다.
    console.log(pathName.pathname);
    // 이때 '/' 로 쪼개면 배열로 나오게 된다
    console.log(pathName.pathname.split('/'));
    // 이때 배열의 마지막 부분이 id 값이다.
    const name = pathName.pathname.split('/');
    const id = name[name.length - 1];
    // 잘 가져왔는지 확인해본다.
    console.log(id);

    // actions 호출 , 게시글을 가져온다.
    if (this.loginUser === null) {
      this.$store.dispatch('getBoard', { boardId: id, userId: this.loginUser });
      this.$store.dispatch('getComments', id);
    } else {
      this.$store.dispatch('getBoard', {
        boardId: id,
        userId: this.loginUser.id,
      });
      this.$store.dispatch('getComments', id);
    }

    // 댓글의 editing 필드 초기화
    for (const comment of this.comments) {
      this.$set(comment, 'editing', false);
    }
    console.log(this.comments);
  },
  methods: {
    // formattedCommentText(comment) {
    //   const textFromDB = comment.content;
    //   this.formattedText = textFromDB.replace(/\n/g, '<br>');
    //   return this.formattedText;
    // },
    moveUpdate() {
      // vue의 script에서 router는 꼭 $를 써준다.
      this.$router.push({ name: 'BoardUpdate' });
    },
    // 삭제 메소드
    deleteBoard() {
      if (confirm('게시글을 삭제하시겠습니까?')) {
        this.$store.dispatch('deleteBoard', this.board.boardId);
      }
    },
    createComment() {
      if (this.loginUser != null) {
        let comment = {
          boardId: this.board.boardId,
          writer: this.loginUser.id,
          content: this.content,
        };
        console.log(comment);
        this.$store.dispatch('createComment', comment);
      } else {
        alert('로그인이 필요합니다.');
      }
    },
    startEdit(comment) {
      comment.editing = true;
      // console.log(comment);
      // 성능상의 이유로 사용을 권장하지는 않지만 렌더링을바로 해줌
      this.$forceUpdate();
      // this.$nextTick(() => {
      //   // 수정 모드로 전환한 후 바로 랜더링이 실행됨
      //   document.getElementById("content").focus(); // ID가 'content'인 요소에 포커스를 맞춤
      // });
    },

    cancelEdit(comment) {
      comment.editing = false;
      this.$forceUpdate();
    },

    updateComment(index) {
      const comment = this.comments[index];
      // 댓글 업데이트 로직 작성
      // comment.content를 이용하여 댓글을 업데이트하고 필요한 로직을 수행
      this.$store.dispatch('updateComment', comment);

      // 수정이 완료되면 editing 값을 false로 변경하여 수정 모드를 해제합니다.
      comment.editing = false;
    },

    deleteComment(commentId) {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        this.$store.dispatch('deleteComment', commentId);
      }
    },

    getCommentWriter(writer) {
      if (this.loginUser === null) {
        writer;
        return false;
      } else {
        if (this.loginUser.id === writer) {
          return true;
        } else {
          return false;
        }
      }
    },
  },
};
</script>

<style></style>
