<template>
  <v-container>
    <v-form 
      ref="form"
      class="pa-5 pb-0"
      v-if="userInfo"
    >
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
    <v-col 
      class="text-right" 
      v-if="userInfo"
    >
      <v-btn
        color="primary"
        class="mt-1 mr-2"
        @click="writeComment"
      >
        등록
      </v-btn>
    </v-col>
  </v-container>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const commentStore = "commentStore";
export default {
  name: "CommentWrite",
  data() {
    return {
      newComment: {
        memo_no: null,
        user_id: null,
        comment: null,
        article_no: null,
        memotime: null,
      },
    }
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  method: {
    writeComment() {
      console.log("댓글 등록");
      this.newComment.user_id = this.userInfo.userid;
      this.newComment.article_no = this.$route.params.article_no;
      this.commentWrite(this.newComment);
      this.newComment.memotime = "now";
      this.$set(this.comments, this.comments.length, this.newComment)
      console.log(this.isWrite)
    },
  }
}
</script>

<style>

</style>