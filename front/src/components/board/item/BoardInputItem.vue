<template>
  <v-container style="max-width: 1300px" fill-height>
    <v-layout align-center row wrap>
      <v-flex class="mb-10">
        <v-row>
          <v-col>
            <!-- <h1 class="text-left mb-0">글쓰기</h1> -->
          </v-col>
          <v-col class="text-right mb-4 p-0">
            <v-btn
              class="link mt-6 mr-4"
              color="primary"
              top
              right
              @click="modifyBoard"
              v-if="this.type === 'modify'"
            >
              저장
            </v-btn>
            <v-btn
              class="link mt-6 mr-4"
              color="primary"
              top
              right
              @click="writeBoard"
              v-if="this.type === 'write'"
            >
              등록
            </v-btn>
            <v-btn
              class="link mt-6 mr-4"
              color="error"
              top
              right
              @click="moveList()"
            >
              취소
            </v-btn>
          </v-col>
        </v-row>

        <v-divider></v-divider>
        <v-card 
          height="700px" 
          flat
        >
          <v-form 
            ref="form"
            class="pa-15"
          >
            <v-text-field
              solo
              dense
              flat
              outlined
              required
              label="제목"
              v-model="board.title"
            ></v-text-field>

            <v-textarea
              solo
              flat
              outlined
              no-resize
              required
              rows="19"
              label="내용"
              v-model="board.text"
            ></v-textarea>
          </v-form>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/api/http";

const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      board : {
        article_no: null,
        user_id: null,
        title: null,
        text: null,
      },
    };
  },

  props: {
    type: { type: String },
  },

  created() {
    if (this.type === "modify") {
      console.log("board article_no: ", this.$route.params.article_no)
      http
        .get(`/boardApi/board/${this.$route.params.article_no}`)
        .then(({ data }) => {
          this.board = data;
        }
      );
    }
  },  

  computed: {
    ...mapState(boardStore, ["isWrite"]),
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    ...mapActions(boardStore, ["boardWrite", "boardModify"]),
    moveList() {
      this.$router.push({ 
        name: "boardview",
        params: { article_no: this.board.article_no },
      });
    },

    async writeBoard() {
      this.board.user_id = this.userInfo.userid;
      await this.boardWrite(this.board);
    },

    async modifyBoard() {
      await this.boardModify(this.board);
    },
  },
}
</script>

<style></style>