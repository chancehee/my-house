import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import houseStore from "@/store/modules/houseStore";
import memberStore from "@/store/modules/memberStore";
import noticeStore from "@/store/modules/noticeStore";
import boardStore from "@/store/modules/boardStore";
import commentStore from "@/store/modules/commentStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    memberStore,
    houseStore,
    noticeStore,
    boardStore,
    commentStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
