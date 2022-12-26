<template>
  <b-container>
    <ul id="category">
      <li id="BK9" data-order="0">
        <span class="category_bg bank"></span>
        <b-button @click="searchPS('은행')" variant="outline-primary"
          >은행</b-button
        >
      </li>
      <li id="MT1" data-order="1">
        <span class="category_bg mart"></span>
        <b-button @click="searchPS('마트')" variant="outline-primary"
          >마트</b-button
        >
      </li>
      <li id="PM9" data-order="2">
        <span class="category_bg pharmacy"></span>
        <b-button @click="searchPS('약국')" variant="outline-primary"
          >약국</b-button
        >
      </li>
      <li id="OL7" data-order="3">
        <span class="category_bg oil"></span>
        <b-button @click="searchPS('주유소')" variant="outline-primary"
          >주유소</b-button
        >
      </li>
      <li id="CE7" data-order="4">
        <span class="category_bg cafe"></span>
        <b-button @click="searchPS('카페')" variant="outline-primary"
          >카페</b-button
        >
      </li>
      <li id="CS2" data-order="5">
        <span class="category_bg store"></span>
        <b-button @click="searchPS('편의점')" variant="outline-primary"
          >편의점</b-button
        >
      </li>
    </ul>

    <!-- <b-button variant="outline-primary">은행</b-button> -->
    <div class="map_wrap">
      <div
        ref="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
        class="border border-primary rounded kmap"
      ></div>
      <!-- 지도타입 컨트롤 div 입니다 -->
      <div class="custom_typecontrol radius_border">
        <span
          id="btnRoadmap"
          class="selected_btn"
          @click="setMapType('roadmap')"
          >지도</span
        >
        <span id="btnSkyview" class="btn" @click="setMapType('skyview')"
          >스카이뷰</span
        >
      </div>
      <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
      <div class="custom_zoomcontrol radius_border">
        <span @click="zoomIn()"
          ><v-img
            src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
            alt="확대"
        /></span>
        <span @click="zoomOut()"
          ><v-img
            src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png"
            alt="축소"
        /></span>
      </div>

      <div
        v-if="houseFlag == true && houses.length > 0"
        id="menu_wrap"
        class="bg_white"
      >
        <font-awesome-icon
          @click="houseDetailClose()"
          icon="fa-solid fa-xmark"
          size="2x"
        /><br />
        <b-img
          id="detailImage"
          :src="require(`@/assets/mainHouse.jpg`)"
        /><br />
        <div>관심 매물 등록</div>

        <font-awesome-icon
          v-if="this.favFlags.includes(house.no) == true"
          icon="fa-solid fa-heart"
          size="2x"
          color = "red"
          @click="favoriteFlag(house.no)"
        />
        <font-awesome-icon
          v-else
          icon="fa-regular fa-heart"
          size="2x"
          @click="favoriteFlag()"
        /><br />
        <table class="table">
          <tbody>
            <tr>
              <th>고유 번호</th>
              <td>{{ house.no }}</td>
            </tr>
            <tr>
              <th>거래 년도</th>
              <td>{{ house.dealYear }} 년</td>
            </tr>
            <tr>
              <th>거래 월</th>
              <td>{{ house.dealMonth }} 월</td>
            </tr>
            <tr>
              <th>아파트 이름</th>
              <td>{{ house.apartmentName }}</td>
            </tr>
            <tr>
              <th>거래 금액</th>
              <td>{{ house.dealAmount }} 만원</td>
            </tr>
            <tr>
              <th>면적</th>
              <td>{{ house.area }} cm<sup>2</sup></td>
            </tr>
            <tr>
              <th>층</th>
              <td>{{ house.floor }} 층</td>
            </tr>
            <tr>
              <th>건축 년도</th>
              <td>{{ house.buildYear }}</td>
            </tr>
            <tr>
              <th>소재지</th>
              <td>
                {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
              </td>
            </tr>
          </tbody>
        </table>

        <b-button>관심 매물 등록</b-button>
      </div>
    </div>

    <div
      id="roadview"
      class="border border-primary rounded"
      style="width: 100%; height: 200px"
    ></div>
    <div class="row justify-content-center mb-2">
      <div class="form-group col-md-2">
        <select
          @change="loadGugun"
          class="form-select bg-secondary text-light"
          id="sido"
        >
          <option value="">시도선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select
          @change="loadDong"
          class="form-select bg-secondary text-light"
          id="gugun"
        >
          <option value="">구군선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select
          @change="loadedDong"
          class="form-select bg-secondary text-light"
          id="dong"
        >
          <option value="">동선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select
          @change="loadedYear"
          class="form-select bg-dark text-light"
          id="year"
        >
          <option value="">매매년도선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select
          @change="loadedMonth"
          class="form-select bg-dark text-light"
          id="month"
        >
          <option value="">매매월선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <button
          @click="getHouses"
          type="button"
          id="list-btn"
          class="btn btn-light"
        >
          아파트매매정보가져오기
        </button>
      </div>
    </div>
    <br />

    <div class="houses" v-if="houses && houses.length != 0">
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">아파트 이름</th>
            <th scope="col">거래 금액</th>
            <th scope="col">거래 년도</th>
            <th scope="col">거래 월</th>
          </tr>
        </thead>

        <tbody
          v-for="house in houses"
          :key="house.seq"
          @click="showOnMap(house)"
        >
          <tr>
            <td>{{ house.apartmentName }}</td>
            <td>{{ house.dealAmount }}만원</td>
            <td>{{ house.dealYear }}</td>
            <td>{{ house.dealMonth }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>주택 목록이 없습니다. </b-alert></b-col>
      </b-row>
    </b-container>
  </b-container>
</template>

<script>
import http from "@/api/http";
import axios from "axios";
// vuex에 담긴 로그인 여부를 가져오기 위해서 import 합니다.
import { mapState } from "vuex";
// vuex module 중 memberStore에 접근하기 위해서 선언합니다.
const memberStore = "memberStore";

let kakao = window.kakao;
export default {
  name: "KaKaoMap",
  data() {
    return {
      map: null, // 맵 객체
      markers: [], // 마커들
      infowindows: [], // 인포윈도우들
      houses: [], // 집들
      house: null, // 집
      clusterer: null, // 클러스터 객체

      houseForm: {
        sidoName: "",
        gugunName: "",
        dongName: "",
        dealYear: "",
        dealMonth: "",
      },

      houseFlag: false, // 상세 페이지를 보여주기 위한 flag 변수 입니다.
      favFlag: false, // 관심 매물 등록 여부를 판단합니다.

      favorites: [], // 관심 매물 목록입니다.
      favFlags : [], // 관심 매물 플래그 변수들입니다.

      user: {
        // user 정보 입니다.
        userid: null,
        userpwd: null,
      },
    };
  },

  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },

  mounted() {
    http.get(`/favoriteApi/favorite/chan`).then(({data}) => {
      console.log(data);
      this.favorites = data;
    });

    let mapContainer = this.$refs.map;
    let mapOption = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3,
    };

    this.map = new kakao.maps.Map(mapContainer, mapOption);
    let roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
    let roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    let roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
    let position = new kakao.maps.LatLng(33.450701, 126.570667);

    roadviewClient.getNearestPanoId(position, 50, function (panoId) {
      roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
    });

    // 1) 매매년도 셀렉트 박스 채우기 !!
    let date = new Date();
    let yearEl = document.querySelector("#year");
    let yearOpt = `<option value="">매매년도선택</option>`;
    let year = date.getFullYear();
    for (let i = year; i > year - 20; i--) {
      yearOpt += `<option value="${i}">${i}년</option>`;
    }
    yearEl.innerHTML = yearOpt;

    // 2) 매매월 셀렉트 박스 채우기 !! (매매년도가 바뀌면 채워진다 !!)
    document.querySelector("#year").addEventListener("change", function () {
      let month = date.getMonth() + 1;
      let monthEl = document.querySelector("#month");
      let monthOpt = `<option value="">매매월선택</option>`;
      let yearSel = document.querySelector("#year");
      let m =
        yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
      for (let i = 1; i < m; i++) {
        monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
      }
      monthEl.innerHTML = monthOpt;
    });

    // 1. 시도코드 얻기 !!
    this.sendRequest("sido", "*00000000");

    // 2. 구군정보 얻기 !! (시도코드가 바뀌면 채워진다 !!)
    let regcode;
    document.querySelector("#sido").addEventListener("change", function () {
      if (this[this.selectedIndex].value) {
        regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
        console.log("regcode", regcode);
      } else {
        this.initOption("gugun");
        this.initOption("dong");
      }
    });
  }, // mounted
  watch: {},
  methods: {
    // 관심 매ㄹ 등록 / 제거 버튼입니다.
    async favoriteFlag(no) {
      console.log("하트 클릭함 !!!!");
      console.log(this.userInfo); // 현재 유저 정보를 콘솔에 출력합니다.

      // 아래 로직은 유저가 로그인 한 경우에만 실행 가능하도록 합니다.
      // 관심 매물 여부는 favorite에 있는 매물이라면 관심 매물입니다.
      // 로그인 상태에서 (로그인 정보와 일치하는 아파트들의 상태를 가져옵니다.)

      if (this.userInfo) {
        console.log("유저 정보 있음 !!");

        await http.get(`/favoriteApi/favorite/chan`).then(({ data }) => {
          console.log("chan data: " ,data);
        })
      }

      console.log("no", no);
      console.log("favFlags", this.favFlags);

      // 식별자가 존재할 때 
      if (this.favFlags.includes(no)) {
        if (confirm("관심 매물 등록을 취소 하겠습니까?")) {
          console.log("관심 매물 삭제!");
          for (let i=0; i<this.favFlags.length; i++) {
            if (this.favFlags[i] === no){
              this.favFlags.splice(i, 1);
              i--;
            }
          }
        } 
      } else {
        if (confirm("관심 매물로 등록 하시겠습니까?")) {
          console.log("관심 매물로 등록!");
          this.favFlags.push(no);

        }
      }

      console.log("this.favFlags" , this.favFlags);

      // DB 에 반영 
      

      // 관심 매물의 하트 색깔은 RED 컬러로 변경합니다.
      for (let favorite of this.favorites) {
        this.favFlags.push(favorite.no);
      }

    },

    // 상세 페이지의 정보 닫기 함수 입니다.
    houseDetailClose() {
      this.houseFlag = false;
    },
    // 클릭 이벤트 테스트를 위한 함수 입니다 (핵심 로직 아님!!!)
    printConsole(param) {
      console.log(param + "이 클릭 됨!!!!");
    },
    zoomIn() {
      this.map.setLevel(this.map.getLevel() - 1);
    },
    zoomOut() {
      this.map.setLevel(this.map.getLevel() + 1);
    },
    setMapType(mapType) {
      // ==================================================================================================================
      let map = this.map;
      console.log(mapType);
      let roadmapControl = document.getElementById("btnRoadmap");
      let skyviewControl = document.getElementById("btnSkyview");
      if (this.maptype === "roadmap") {
        map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
        roadmapControl.className = "selected_btn";
        skyviewControl.className = "btn";
      } else {
        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
        skyviewControl.className = "selected_btn";
        roadmapControl.className = "btn";
      }
    }, // ==================================================================================================================

    // 카카오 검색 API를 사용하여 키워드 검색 결과를 지도에 출력하는 함수입니다!!!
    searchPS(param) {
      //==================================================================================================================

      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.infowindows.forEach((infowindow) => {
        infowindow.setMap(null);
      });
      this.markers = [];
      this.infowindow = null;
      this.clusterer = null;
      let markerImage = null;
      let imageSrc = null;
      let imageSize = new kakao.maps.Size(35, 41);
      let imageOption = { offset: new kakao.maps.Point(27, 69) };

      if (param === "카페") {
        imageSrc = require("@/assets/icons/icon_cafe.png");
      } else if (param === "은행") {
        imageSrc = require("@/assets/icons/icon_bank.png");
      } else if (param === "마트") {
        imageSrc = require("@/assets/icons/icon_mart.png");
      } else if (param === "약국") {
        imageSrc = require("@/assets/icons/icon_hospital.png");
      } else if (param === "주유소") {
        imageSrc = require("@/assets/icons/icon_gas.png");
      } else if (param === "편의점") {
        imageSrc = require("@/assets/icons/icon_convenient.png");
      } else {
        imageSrc = require("@/assets/icons/icon_default_marker.png");
      }

      markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      let bounds = this.map.getBounds();
      console.log("bounds", bounds);
      // let sw = bounds.getSouthWest();
      // let ne = bounds.getNorthEast();

      let keyword = param;

      console.log(keyword);
      this.getSearchData(keyword, bounds).then((data) => {
        console.log(data);
        const bounds = new kakao.maps.LatLngBounds();

        data.forEach((v) => {
          const iwContent = `<div style=height:100%; width:100%; padding:5px;">${v.place_name}
            </div>`,
            iwPosition = new kakao.maps.LatLng(v.y, v.x),
            iwRemoveable = true;

          const infowindow = new kakao.maps.InfoWindow({
            map: this.map,
            position: iwPosition,
            content: iwContent,
            removable: iwRemoveable,
          });

          const marker = new kakao.maps.Marker({
            map: this.map,
            position: new kakao.maps.LatLng(v.y, v.x),
            infowindow: infowindow,
            image: markerImage,
          });
          this.markers.push(marker);
          this.infowindows.push(infowindow);
          marker.setMap(this.map);
          bounds.extend(new kakao.maps.LatLng(v.y, v.x));

          kakao.maps.event.addListener(marker, "click", function () {
            console.log("마커 클릭함!!");
            console.log(marker);
            infowindow.open(this.map, marker);
          });
        });
        this.map.setBounds(bounds);
      });
      //================================================================================================================== searchPS
    },
    getSearchData(keyword, value) {
      // ==================================================================================================================
      return new Promise((resolve) => {
        new kakao.maps.services.Places().keywordSearch(
          keyword,
          function placeSearchCB(data, status) {
            if (status === kakao.maps.services.Status.OK) {
              console.log("키워드 검색 성공!!");
              resolve(data);
            }
          },
          { bounds: value }
        );
      });
    }, // ==================================================================================================================

    getHouseData(keyword) {
      //======================================================================================================
      return new Promise((resolve) => {
        http.post("/houseApi/house", { dong: keyword }).then(({ data }) => {
          resolve(data);
        });
      });
    }, //==========================================================================================================================

    async showOnMap(house) {
      // ==================================================================================================================
      console.log("showOnMap start!!");

      // this.map.setCenter(new kakao.maps.LatLng(house.lat, house.lng));
      this.map.setLevel(1);
      this.map.panTo(new kakao.maps.LatLng(house.lat, house.lng));

      let roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      let roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      let roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      let position = new kakao.maps.LatLng(house.lat, house.lng);
      //var position = this.mapInstance.center;
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      await roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
      window.scrollTo(0, 75); // 가로 축, 새로 축

      this.houseFlag = true;
      this.house = house;
    }, // ==================================================================================================================

    loadGugun(event) {
      // ===========================================================================================================
      //console.log("sido 변경됨");
      this.houseForm.sidoName =
        event.target.options[event.target.selectedIndex].text;

      if (event.target.value) {
        let regcode = event.target.value.substr(0, 2) + "*00000";
        this.sendRequest("gugun", regcode);
      } else {
        this.initOption("gugun");
        this.initOption("dong");
      }
    }, // ===========================================================================================================

    loadDong(event) {
      // ===========================================================================================================
      //console.log("gugun 변경됨");
      this.houseForm.gugunName =
        event.target.options[event.target.selectedIndex].text;

      if (event.target.value) {
        let regcode = event.target.value.substr(0, 4) + "*";
        this.sendRequest("dong", regcode);
      } else {
        this.initOption("gugun");
        this.initOption("dong");
      }
    }, // ===========================================================================================================

    loadedDong(event) {
      // ===========================================================================================================
      //console.log("dong 변경됨");
      this.houseForm.dongName =
        event.target.options[event.target.selectedIndex].text;
    }, // ===========================================================================================================

    loadedYear(event) {
      // ===========================================================================================================
      this.houseForm.dealYear =
        event.target.options[event.target.selectedIndex].text;
      this.houseForm.dealMonth = "";
    }, // ===========================================================================================================

    loadedMonth(event) {
      // ===========================================================================================================
      this.houseForm.dealMonth =
        event.target.options[event.target.selectedIndex].text;
    }, // ===========================================================================================================

    deleteMarkers() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
    },

    async getHouses() {
      // ==============================================
      console.log("전송 버튼 클릭됨");

      // 유효성 검사 필요 (모든 데이터들 선택하였는가?? )
      let sido = this.houseForm.sidoName;
      let gugun = this.houseForm.gugunName;
      let year = this.houseForm.dealYear;
      let month = this.houseForm.dealMonth;

      if (sido === "") {
        alert("시도를 선택해 주세요 :)");
      } else if (gugun === "") {
        alert("구군을 선택해 주세요 :)");
      } else if (year === "") {
        alert("거래 년도를 선택해 주세요 :)");
      } else if (month === "") {
        alert("거래 월을 선택해 주세요 :)");
      }

      let bounds = new kakao.maps.LatLngBounds();

      this.clusterer = null;
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map,
        averageCenter: true,
        minLevel: 12,
      });

      // this.deleteMarkers();

      // this.markers.forEach((marker) => {
      //   marker.setMap(null);
      // });

      this.infowindows.forEach((infowindow) => {
        infowindow.setMap(null);
      });
      this.infowindows = [];

      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];

      let imageSrc = require("@/assets/icons/icon_default_marker.png");
      let imageSize = new kakao.maps.Size(35, 41);
      let imageOption = { offset: new kakao.maps.Point(27, 69) };
      let markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      this.houses = [];

      await http.post("/houseApi/house", this.houseForm).then(({ data }) => {
        //this.deleteMarkers();

        console.log("함수안 :", this);
        this.houses = data;

        // 마커 생성하기
        data.forEach((house) => {
          const iwContent = `<div style=height:100%; width:100%; padding:5px;">${house.apartmentName}
          </div>`,
            iwPosition = new kakao.maps.LatLng(house.lat, house.lng),
            iwRemoveable = true;

          const infowindow = new kakao.maps.InfoWindow({
            map: this.map,
            position: iwPosition,
            content: iwContent,
            removable: iwRemoveable,
          });

          const marker = new kakao.maps.Marker({
            map: this.map,
            position: new kakao.maps.LatLng(house.lat, house.lng),
            infowindow: infowindow,
            image: markerImage,
          });

          // 마커에 클릭 이벤트 리스너 등록하기!!!
          kakao.maps.event.addListener(marker, "click", () => {
            console.log("마커 클릭도ㅚㅁ!!!");
            console.log(this.houseFlag);
            this.house = house;
            this.houseFlag = true;
          });

          this.markers.push(marker);
          this.infowindows.push(infowindow);
          marker.setMap(this.map);
          bounds.extend(new kakao.maps.LatLng(house.lat, house.lng));
        });
        this.map.setBounds(bounds);
        this.clusterer.addMarkers(this.markers);
      });

      // 관심 매물의 하트 색깔은 RED 컬러로 변경합니다.
      for (let favorite of this.favorites) {
        this.favFlags.push(favorite.no);
      }

      console.log("favFlags", this.favFlags);

      window.scroll(0, 800); // 조회 결과 리스트를 보여주는 곳으로 화면을 이동시킵니다.
    }, // =======================================================================

    sendRequest(selid, regcode) {
      // ====================================================================
      console.log("regcode", regcode);
      const url =
        "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
      let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
      axios(`${url}?${params}`).then(({ data }) => {
        //console.log("data", data);
        //console.log("addOption 실행!!");
        this.addOption(selid, data);
        //console.log("addOption 실행 완료!!");
      });
    }, // =========================================================================================================

    addOption(selid, data) {
      // =========================================================================================
      let opt = ``;
      this.initOption(selid);
      let name = "";
      let idx = 2;
      switch (selid) {
        case "sido":
          opt += `<option value="">시도선택</option>`;
          data.regcodes.forEach((regcode) => {
            opt += `<option value="${regcode.code}">${regcode.name}</option>`;
          });
          break;
        case "gugun":
          opt += `<option value="">구군선택</option>`;
          for (let i = 0; i < data.regcodes.length; i++) {
            if (i != data.regcodes.length - 1) {
              if (
                data.regcodes[i].name.split(" ")[1] ==
                  data.regcodes[i + 1].name.split(" ")[1] &&
                data.regcodes[i].name.split(" ").length !=
                  data.regcodes[i + 1].name.split(" ").length
              ) {
                data.regcodes.splice(i, 1);
                i--;
              }
            }
          }

          data.regcodes.forEach(function (regcode) {
            if (regcode.name.split(" ").length == 2)
              name = regcode.name.split(" ")[1];
            else
              name =
                regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
            opt += `<option value="${regcode.code}">${name}</option>`;
          });
          break;
        case "dong":
          this.houseForm.dongName = "전체선택";
          opt += `<option value="">전체선택</option>`;

          data.regcodes.forEach(function (regcode) {
            if (regcode.name.split(" ").length != 3) idx = 3;
            opt += `<option value="${regcode.code}">${
              regcode.name.split(" ")[idx]
            }</option>`;
          });
      }
      document.querySelector(`#${selid}`).innerHTML = opt;
    }, // =================================================================================================================

    initOption(selid) {
      // =============================================================================================
      let options = document.querySelector(`#${selid}`);
      options.length = 0;
    }, // ===================================================================================================================
  }, // methods
  created() {},
};
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

.map {
  width: 100%;
  height: 500px;
  margin-top: 15px;
}

.map_wrap {
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 350px;
}
.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}
.custom_typecontrol {
  position: absolute;
  top: 10px;
  right: 10px;
  overflow: hidden;
  width: 130px;
  height: 30px;
  margin: 0;
  padding: 0;
  z-index: 1;
  font-size: 12px;
  font-family: "Malgun Gothic", "맑은 고딕", sans-serif;
}
.custom_typecontrol span {
  display: block;
  width: 65px;
  height: 30px;
  float: left;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
}
.custom_typecontrol .btn {
  background: #fff;
  background: linear-gradient(#fff, #e6e6e6);
}
.custom_typecontrol .btn:hover {
  background: #f5f5f5;
  background: linear-gradient(#f5f5f5, #e3e3e3);
}
.custom_typecontrol .btn:active {
  background: #e6e6e6;
  background: linear-gradient(#e6e6e6, #fff);
}
.custom_typecontrol .selected_btn {
  color: #fff;
  background: #425470;
  background: linear-gradient(#425470, #5b6d8a);
}
.custom_typecontrol .selected_btn:hover {
  color: #fff;
}
.custom_zoomcontrol {
  position: absolute;
  top: 50px;
  right: 10px;
  width: 36px;
  height: 80px;
  overflow: hidden;
  z-index: 1;
  background-color: #f5f5f5;
}
.custom_zoomcontrol span {
  display: block;
  width: 36px;
  height: 40px;
  text-align: center;
  cursor: pointer;
}
.custom_zoomcontrol span img {
  width: 15px;
  height: 15px;
  padding: 12px 0;
  border: none;
}
.custom_zoomcontrol span:first-child {
  border-bottom: 1px solid #bfbfbf;
}

#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}

#detailImage {
  height: 100px;
}
</style>
