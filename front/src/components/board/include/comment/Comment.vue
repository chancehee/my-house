<template>
  <div v-show="isShow" class="comment">
    <div class="head">{{ comment.user_id }} {{ comment.memo_time }}</div>
    <div class="content">{{ comment.comment }}</div>
    <div class="cbtn">
      <label @click="modifyCommentView">수정</label> |
      <label @click="deleteComment">삭제</label>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  name: "CommentView",
  data() {
    return {
      isShow: true,
    };
  },
  props: {
    comment: Object,
    idx: Number,
  },
  methods: {
    modifyCommentView() {
      console.log("댓글 수정 버튼 클릭 !!!");

      this.$emit("modify-comment", {
        memo_no: this.comment.memo_no,
        comment: this.comment.comment,
      });
    },
    deleteComment() {
      console.log(this.comment);
      if (confirm("정말로 삭제?")) {
        console.log(this.comment.memo_no);

        http
          .delete(`/commentApi/comment/${this.comment.memo_no}`)
          .then(({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "삭제가 완료되었습니다.";
            }
            alert(msg);
          });
        location.reload();
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
};
</script>

<style>
.comment {
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 10px 20px;
  margin: 10px;
}
.head {
  font-weight: bold;
  margin-bottom: 5px;
}
.content {
  padding: 5px;
}
.cbtn {
  text-align: right;
  color: steelblue;
  margin: 5px 0px;
}
</style>
