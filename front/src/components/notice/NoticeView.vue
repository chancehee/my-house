<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-container style="max-width: 1300px" fill-height>
          <v-layout align-center row wrap>
            <v-flex
              class="mb-10"
            >
              <v-card
                height="700px"
                class="m-3"
                flat
              >
                <v-row>
                  <v-col class="ml-3">
                    <v-card-title>
                      <span class="primary--text mr-3">[공지]</span>{{ notice.title }}
                    </v-card-title>
                    <v-card-subtitle
                      class="text-sm-left"
                    >
                      운영자
                    </v-card-subtitle>
                  </v-col>
                  <v-col class="text-sm-right mr-3">
                    <v-card-subtitle>
                      {{ notice.join_date }}
                    </v-card-subtitle>
                    <v-btn
                      @click="moveModifyNotice"
                      color="primary"
                      class="mr-2"
                      small
                      v-if="userInfo != null && userInfo.userid == 'dev'"
                    >
                      수정
                    </v-btn>
                    <v-btn
                      color="error"
                      class="mr-3"
                      v-bind="attrs"
                      v-on="on"
                      small
                      v-if="userInfo != null && userInfo.userid == 'dev'"
                    >
                      삭제
                    </v-btn>
                  </v-col>
                </v-row>
                <v-divider inset></v-divider>
                <v-card-text class="text-sm-left ml-3">
                  {{ notice.text }}
                </v-card-text>
              </v-card>
              <v-btn
                class="mt-5"
                color="blue lighten-1 text-capitalize white--text"
                @click="moveNoticeList"
                x-large
              >
                목록
              </v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </template>
      <v-card>
        <v-card-title>
          정말로 삭제하시겠습니까?
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="deleteNotice"
          >
            YES
          </v-btn>
          <v-btn
            color="red darken-1"
            text
            @click="dialog = false"
          >
            NO
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeView",
  data() {
    return {
      notice: {},
      dialog: false,
    };
  },

  created() {
    console.log(`${this.$route.params.num}`);
    http.get(`/noticeApi/noticeDetail/${this.$route.params.num}`)
        .then(({ data }) => {
          this.notice = data;
        }
    );
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    moveModifyNotice() {
      this.$router.replace({
        name: "noticemodify",
        params: { num: this.notice.num },
      });
    },

    deleteNotice() {
      let pgNo = 1;
      this.dialog = false;
      http
        .delete(`/noticeApi/notice/${this.$route.params.num}/${pgNo}}`)
        .then(({ data }) => {
          if (data.message == "success") {
            this.$router.push({ name: "noticelist" });
          }
        }
      );
    },

    moveNoticeList() {
      this.$router.push({ name: "noticelist" });
    },
  },
};
</script>

<style></style>