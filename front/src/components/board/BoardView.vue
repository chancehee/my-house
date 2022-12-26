<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="290">
      <template v-slot:activator="{ on, attrs }">
        <v-container style="max-width: 1300px" fill-height>
          <v-layout align-center row wrap>
            <v-flex class="mb-10">
              <v-card height="700px" class="m-3" flat>
                <v-row>
                  <v-col class="ml-3">
                    <v-card-title>
                      <span class="primary--text mr-3">Q.</span
                      >{{ board.title }}
                    </v-card-title>
                    <v-card-subtitle class="text-sm-left">
                      {{ board.user_id }}
                    </v-card-subtitle>
                  </v-col>
                  <v-col class="text-sm-right mr-3">
                    <v-card-subtitle>
                      {{ board.regtime }}
                    </v-card-subtitle>
                    <v-btn
                      @click="modifyBoard"
                      color="primary"
                      class="mr-2"
                      small
                      v-if="userInfo && userInfo.userid == board.user_id"
                    >
                      수정
                    </v-btn>
                    <v-btn
                      color="error"
                      class="mr-3"
                      v-bind="attrs"
                      v-on="on"
                      small
                      v-if="userInfo && userInfo.userid == board.user_id"
                    >
                      삭제
                    </v-btn>
                  </v-col>
                </v-row>
                <v-divider></v-divider>
                <v-card-text class="text-sm-left ml-3">
                  {{ board.text }}
                </v-card-text>
              </v-card>

              <div class="text-left ml-4 mt-15">
                <span>총 </span>
                <span class="primary--text">{{ comments.length }}</span>
                <span>건의 댓글이 있습니다.</span>
              </div>
              <!-- Comment -->
              <v-card class="ma-3 pb-5" flat>
                <v-form ref="form" class="pa-5 pb-0">
                  <v-textarea
                    solo
                    flat
                    outlined
                    no-resize
                    required
                    rows="2"
                    label="댓글을 입력하세요."
                    v-model="newComment.comment"
                    hide-details
                  ></v-textarea>
                </v-form>
                <v-col class="text-right">
                  <v-btn
                    color="primary"
                    class="mt-1 mr-2"
                    @click="writeComment"
                  >
                    등록
                  </v-btn>
                </v-col>

                <v-divider dark></v-divider>

                <v-card
                  v-for="(comment, index) in comments"
                  :key="index"
                  :comment="comment"
                  flat
                >
                  <v-row no-gutters>
                    <v-col class="text-sm-left ml-3" md="auto">
                      <v-card-subtitle class="grey--text">
                        {{ comment.user_id }} | {{ comment.memo_time }}
                      </v-card-subtitle>
                    </v-col>
                    <v-col class="text-sm-left mt-3" lg="2">
                      <v-btn
                        @click="modifyComment(index)"
                        color="primary"
                        small
                        text
                        hide-details
                        class="pr-0 pl-0"
                        v-if="
                          userInfo &&
                          userInfo.userid == comment.user_id &&
                          !flagModify
                        "
                      >
                        수정
                      </v-btn>

                      <v-btn
                        @click="saveComment(comment)"
                        color="primary"
                        small
                        text
                        hide-details
                        class="pr-0 pl-0"
                        v-if="
                          userInfo &&
                          userInfo.userid == comment.user_id &&
                          flagModify
                        "
                      >
                        저장
                      </v-btn>

                      <v-btn
                        @click="deleteComment(comment)"
                        color="error"
                        small
                        text
                        hide-details
                        class="pl-0"
                        v-if="userInfo && userInfo.userid == comment.user_id"
                      >
                        삭제
                      </v-btn>
                    </v-col>
                    <v-card-text
                      class="text-sm-left ml-4"
                      v-show="!flagModify || index != idxCmt"
                    >
                      {{ comment.comment }}
                    </v-card-text>
                    <v-form
                      ref="form"
                      class="pa-5 pb-0 ml-1"
                      v-show="flagModify && index == idxCmt"
                    >
                      <v-textarea
                        solo
                        flat
                        outlined
                        no-resize
                        required
                        rows="2"
                        label="댓글을 입력하세요."
                        v-model="comment.comment"
                        hide-details
                      ></v-textarea>
                    </v-form>
                  </v-row>
                  <v-divider dark></v-divider>
                </v-card>
              </v-card>

              <v-btn
                class="mt-5"
                color="blue lighten-1 text-capitalize white--text"
                @click="moveBoardList"
                x-large
              >
                목록
              </v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </template>
      <v-card>
        <v-card-title> 정말로 삭제하시겠습니까? </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="deleteBoard"> YES </v-btn>
          <v-btn color="red darken-1" text @click="dialog = false"> NO </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- 
    <template>
      <v-container style="max-width: 800px" fill-height>
        <v-layout align-center row wrap>
          <v-flex
            class="mb-10"
          >
            <v-card>
              <v-card-actions>
                <v-btn
                  text
                >
                  댓글
                </v-btn>
                <v-spacer></v-spacer>

                <v-btn
                  icon
                  @click="show = !show"
                >
                  <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </template> -->

    <!-- <comment-write />

    <comment
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
    /> -->
  </v-row>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions } from "vuex";
// import Comment from "@/components/board/include/comment/Comment.vue";
// import CommentWrite from "@/components/board/include/comment/CommentWrite.vue";

const memberStore = "memberStore";
const commentStore = "commentStore";

export default {
  name: "BoardView",
  data() {
    return {
      board: {},
      dialog: false,
      comments: [],
      show: false,
      newComment: {
        memo_no: null,
        user_id: null,
        comment: null,
        article_no: null,
        memotime: null,
      },
      flagModify: false,
      idxCmt: null,
    };
  },

  created() {
    http
      .get(`/boardApi/board/${this.$route.params.article_no}`)
      .then(({ data }) => {
        this.board = data;
      });

    http
      .get(`/commentApi/comment/${this.$route.params.article_no}`)
      .then((response) => {
        this.comments = response.data;
      });
  },

  // components: {
  //   Comment,
  //   CommentWrite,
  // },

  computed: {
    ...mapState(commentStore, ["isWrite", "isModify", "isDelete", "areDelete"]),
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    ...mapActions(commentStore, [
      "commentWrite",
      "commentModify",
      "commentDelete",
      "commentsDelete",
    ]),
    modifyBoard() {
      this.$router.replace({
        name: "boardmodify",
        params: { article_no: this.board.article_no },
      });
    },

    async deleteBoard() {
      this.dialog = false;
      await this.commentsDelete(this.$route.params.article_no);
      console.log("댓글 삭제 완료");
      console.log(this.areDelete);
      http
        .delete(`/boardApi/board/${this.$route.params.article_no}`)
        .then(({ data }) => {
          console.log("board delte", data);
          if (data.message == "success") {
            this.$router.push({ name: "boardlist" });
          }
        });
    },

    moveBoardList() {
      this.$router.push({ name: "boardlist" });
    },

    async writeComment() {
      console.log("댓글 등록");
      if (this.userInfo) {
        this.newComment.user_id = this.userInfo.userid;
        this.newComment.article_no = this.$route.params.article_no;
        await this.commentWrite(this.newComment);
      } else {
        alert("댓글 등록은 로그인 후에 가능합니다 :)");
        this.$router.push({ name: "login" });
      }
    },

    async modifyComment(index) {
      this.flagModify = true;
      this.idxCmt = index;
    },

    async saveComment(comment) {
      console.log(comment);
      await this.commentModify(comment);
      this.flagModify = false;
    },

    async deleteComment(comment) {
      await this.commentDelete(comment);
    },
  },
};
</script>

<style></style>
