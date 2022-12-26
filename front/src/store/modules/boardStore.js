import router from "@/router";
import { write, modify } from "@/api/board";

const boardStore = {
  namespaced: true,
  state: {
    isWrite: false,
    isModify: false,
  },
  mutations: {
    SET_IS_WRITE: (state, isWrite) => {
      state.isWrite = isWrite;
    },
    SET_IS_MODIFY: (state, isModify) => {
      state.isModify = isModify;
    },
  },
  actions: {
    async boardWrite({ commit }, board) {
      await write(
        board,
        ({ data }) => {
          if (data.message == "success") {
            commit("SET_IS_WRITE", true);
            router.push({ name: "boardlist" });
          } else {
            commit("SET_IS_WRITE", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async boardModify({ commit }, board) {
      await modify(
        board,
        ({ data }) => {
          if (data.message == "success") {
            commit("SET_IS_MODIFY", true);
            router.push({ 
              name: "boardview",
              params: { article_no: board.article_no },
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
  },
};

export default boardStore;