<template>
  <v-toolbar 
    dark 
    color="blue lighten-1 text-capitalize" 
    class="hidden-xs-and-down"
  >
    <v-toolbar-title >
      <router-link style="text-decoration-line:none;"  :to="{ name: 'main' }">

        <v-row>
        <v-col>
        <v-img
        class="pa-4"
          :src="require('@/assets/rocket.png')"
          id="logo"
          alt="logo"
          max-heigh = "35"
          max-width = "35"
          
        ></v-img>
        </v-col>
        <v-col>
          <div>마하</div>
        </v-col>
        </v-row>
      </router-link>
    </v-toolbar-title>

    <v-spacer></v-spacer>

    <v-toolbar-items>
      <v-btn
        text
        class="link"
        v-for="item in nav"
        :key="item.text"
        :to="item.path"
      >
        {{ item.text }}
      </v-btn>
      
      <v-btn
        text
        @click.prevent="onClickLogout"
        v-if="userInfo"
      >
        LOGOUT
      </v-btn>
      
      <v-btn
        text
        class="link"
        :to="{ name: 'join' }"
        v-else
      >
        SIGN UP
      </v-btn>

      <v-btn
        text
        class="link"
        :to="{ name: 'mypage' }"
        v-if="userInfo"
      >
        <v-avart color="indigo">
          <v-icon dark>
            mdi-account-circle
          </v-icon>
        </v-avart>
      </v-btn>

      <v-btn
        text
        class="link"
        :to="{ name: 'login' }"
        v-else
      >
        LOGIN
      </v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {
      nav: [
        {
          text: 'HOME',
          path: { name: 'main' },
          active: false,
        },
        {
          text: 'MAP',
          path: { name: 'house' },
          active: false,
        },
        {
          text: 'Q&A',
          path: { name: 'board' },
          active: false,
        },
      ]
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      // console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style>
  #logo {
    width: 50px;
  }
  .v-btn:before {
    opacity: 0 !important;
  }
  .v-ripple__container {
    opacity: 0 !important;
  }


  
</style>
