import router from "@/router";
import { write, modify, del, deleteComments } from "@/api/comment";

const commentStore = {
  namespaced: true,
  state: {
    isWrite: false,
    isModify: false,
    isDelete: false,
    areDelete: false,
  },
  mutations: {
    SET_IS_WRITE: (state, isWrite) => {
      state.isWrite = isWrite;
    },
    SET_IS_MODIFY: (state, isModify) => {
      state.isModify = isModify;
    },
    SET_IS_DELETE: (state, isDelete) => {
      state.isDelete = isDelete;
    },
    SET_ARE_DELETE: (state, areDelete) => {
      state.areDelete = areDelete;
    },
  },
  actions: {
    async commentWrite({ commit }, comment) {
      await write(
        comment,
        ({ data }) => {
          console.log(data)
          if (data.message == "success") {
            commit("SET_IS_WRITE", true);
            router.go();
          } else {
            commit("SET_IS_WRITE", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async commentModify({ commit }, comment) {
      await modify(
        comment,
        ({ data }) => {
          if (data.message == "success") {
            commit("SET_IS_MODIFY", true);
            router.push({ 
              name: "boardview",
              params: { article_no: comment.article_no },
            });
          } else {
            commit("SET_IS_MODIFY", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async commentDelete({ commit }, comment) {
      await del(
        comment,
        ({ data }) => {
          if (data.message == "success") {
            commit("SET_IS_DELETE", true);
            router.go();
          } else {
            commit("SET_IS_DELETE", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async commentsDelete({ commit }, articleNo) {
      await deleteComments(
        articleNo,
        ({ data }) => {
          console.log("========commentsDelete=========")
          console.log(articleNo);
          console.log(data)
          if (data.message == "success") {
            commit("SET_ARE_DELETE", true);
          } else {
            commit("SET_ARE_DELETE", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default commentStore;