<template>
    <v-container style="max-width: 1400px" fill-height>
      <v-layout align-center row wrap>
        <v-flex 
          class="mt-10 mb-10"
        >  
          <h1 class="text-left mb-0 pa-0">공지사항</h1>
          <v-col class="text-right mb-4 pa-0">
            <v-btn
              class="link m-0"
              top
              right
              color="primary"
              @click="moveWrite()"
              v-if="userInfo != null && userInfo.userid == 'dev'"
            >
              글쓰기
            </v-btn>
          </v-col>

          <v-card 
            height="700px" 
            flat
          >
            <v-simple-table   
              class="table-striped"           
              :fields="fields"
              @row-clicked="viewNotice"
            > 
              <thead>
                <tr>
                  <th class="text-center">
                    분류
                  </th>
                  <th class="text-center">
                    제목
                  </th>
                  <th class="text-center">
                    작성일
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="notice in notices"
                  :key="notice.num"
                  @click="viewNotice(notice)"
                >
                  <td>공지</td>
                  <td>{{ notice.title }}</td>
                  <td>{{ notice.join_date }}</td>
                </tr>
              </tbody>
            </v-simple-table>
          </v-card> 
        </v-flex>
      </v-layout>
    </v-container>
</template>

<script>
import http from '@/api/http'
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeList",
  data() {
    return {
      notices: [],
      fields: [
        { key: "num", tdClass: "tdClass" },
        { key: "title", tdClass: "tdClass" },
        { key: "userid", tdClass: "tdClass" },
        { key: "join_date", tdClass: "tdClass" },
      ],
    }
  },

  created() {
    http.get("/noticeApi/notice/1").then(({ data }) => {
      this.notices = data;
    });
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  }, 

  methods: {
    moveWrite() {
      this.$router.push({ name: "noticewrite" });
    },

    viewNotice(notice) {
      this.$router.push({
        name: "noticeview",
        params: { num: notice.num },
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