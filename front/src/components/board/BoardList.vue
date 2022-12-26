<template>
  <v-container style="max-width: 1300px" fill-height>
    <v-layout align-center row wrap>
      <v-flex class="mt-10 mb-10">
        <h1 class="text-left mb-0">Q&A</h1>
        <v-col class="text-right mb-4 pa-0">
          <v-btn
            class="link m-0"
            top
            right
            color="primary"
            @click="moveWrite()"
          >
            글쓰기
          </v-btn>
        </v-col>

        <v-card height="700px" flat>
          <v-simple-table
            class="table-striped"
            :fields="fields"
            @row-clicked="viewBoard"
          >
            <thead>
              <tr>
                <th class="text-center">작성자</th>
                <th class="text-center">제목</th>
                <th class="text-center">작성일</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="board in boards"
                :key="board.article_no"
                @click="viewBoard(board)"
              >
                <td>{{ board.user_id }}</td>
                <td>{{ board.title }}</td>
                <td>{{ board.regtime }}</td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardList",
  data() {
    return {
      boards: [],
      fields: [
        { key: "article_no", tdClass: "tdClass" },
        { key: "title", tdClass: "tdClass" },
        { key: "user_id", tdClass: "tdClass" },
        { key: "regtime", tdClass: "tdClass" },
      ],
    };
  },

  created() {
    http.get("/boardApi/board").then(({ data }) => {
      this.boards = data;
    });
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    moveWrite() {
      if (this.userInfo) {
        this.$router.push({ name: "boardwrite" });
      } else {
        alert("글작성은 로그인 후에 가능합니다 :)");
        this.$router.push({ name: "login" });
      }
    },

    viewBoard(board) {
      console.log("viewBoard", board);
      this.$router.push({
        name: "boardview",
        params: { article_no: board.article_no },
      });
    },
  },
};
</script>

<style>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
