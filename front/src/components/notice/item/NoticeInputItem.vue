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
              @click="modifyNotice"
              v-if="this.type === 'modify'"
            >
              저장
            </v-btn>
            <v-btn
              class="link mt-6 mr-4"
              color="primary"
              top
              right
              @click="writeNotice"
              v-if="this.type === 'write'"
            >
              등록
            </v-btn>
            <v-btn
              class="link mt-6 mr-4"
              color="error"
              top
              right
              @click="moveNotice()"
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
              v-model="notice.title"
            ></v-text-field>

            <v-textarea
              solo
              flat
              outlined
              no-resize
              required
              rows="19"
              label="내용"
              v-model="notice.text"
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

const noticeStore = "noticeStore";
const memberStore = "memberStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      notice : {
        num: null,
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
      console.log("notice num: ", this.$route.params.num)
      http
        .get(`/noticeApi/noticeDetail/${this.$route.params.num}`)
        .then(({ data }) => {
          this.notice = data;
        }
      );
    }
  },  

  computed: {
    ...mapState(noticeStore, ["isWrite"]),
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    ...mapActions(noticeStore, ["noticeWrite", "noticeModify"]),
    moveNotice() {
      this.$router.push({ 
        name: "noticeview",
        params: { num: this.notice.num },
      });
    },

    async writeNotice() {
      this.notice.user_id = this.userInfo.userid;
      this.noticeWrite(this.notice);
    },

    async modifyNotice() {
      console.log('수정')
      console.log(this.notice)
      this.noticeModify(this.notice);
    }
  },
}
</script>

<style></style>