import { sidoList, gugunList, houseList } from "@/api/house.js";
import http from "@/api/http";


const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    houses: [],
    houses2: [], // <->  HouseInfo2Dto
    house: null,
    mapCenter: null 
  },
  getters: {
    houses : state => {
      return state.houses;
    },
    markerList : state => {
      return state.markerList;
    },
    mapCenter : state => {
      return state.mapCenter;
    }
  },
  mutations: {

    CLEAR_MAKER_LIST(state) {

      state.markerList(marker => {
        marker.setMap(null);
      })
      state.markerList = [];
    },

    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_HOUSES2_LIST(state, houses2) {
      // HouseInfo2Dto
      state.houses2 = houses2;
    },
    SET_MARKER_LIST(state, payload) {
      state.markerList = payload;
    },
    SET_MAP_CENTER(state, payload) {
      state.mapCenter = payload;
    }
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          console.log("sido list !!!");
          console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log("getSido error!!");
          console.log(error);
        }
      );
    },

    getGugun: ({ commit }, sidoCode) => {
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          console.log("gugun list");
          console.log(data);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log("gugun list error!!!");
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, gugunCode) => {
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202001",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };

      houseList(
        params,
        ({ data }) => {
          console.log(
            "houselist !!" + data.response.body.items.item[0].거래금액
          );
          console.log(data.response.body.items);
          console.log(data.response.body.items.item.length);
          console.log(data);
          commit("SET_HOUSE_LIST", data.response.body.items.item);
        },
        (error) => {
          console.log("house list error!!!");
          console.log(error);
        }
      );
    },
    getHouse2List: ({ commit }) => {
      const params = {
        dong: "하대원동",
      };
      http
        .post(`/HouseApi/house2`, params)
        .then(({ data }) => {
          console.log(data);
          commit("SET_HOUSES2_LIST", data.response.body.items.item);
        })
        .catch((error) => console.log(error));
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },

    getMarkerList: ({state, commit}, [positions, map]) => {

      console.log("getMarkerList 호출!!");
      
      let tempMarkerList = [];

      commit("CLEAR_MAKER_LIST");

      console.log("마커리스트:", state.markerList);

      //마커 설정
      positions.forEach(position => {
        const marker = new window.kakao.maps.Marker({
          map,
          position: new window.kakao.maps.LatLng(position.getLng(), position.getLat()),
        });
        
        tempMarkerList.push(marker);
      })
      commit('SET_MARKER_LIST', tempMarkerList)
      console.log("마커 리스트: " ,state.markerList);

    }
  },
};

export default houseStore;
