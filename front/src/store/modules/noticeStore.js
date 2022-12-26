import router from "@/router";
import { write, modify } from "@/api/notice";

const noticeStore = {
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
    async noticeWrite({ commit }, notice) {
      await write(
        notice,
        ({ data }) => {
          if (data.message == "success") {
            commit("SET_IS_WRITE", true);
            router.push({ name: "noticelist" });
          } else {
            commit("SET_IS_WRITE", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async noticeModify({ commit }, notice) {
      await modify(
        notice,
        ({ data }) => {
          if (data.message == "success") {
            commit("SET_IS_MODIFY", true);
            router.push({ 
              name: "noticeview",
              params: { num: notice.num },
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

export default noticeStore;