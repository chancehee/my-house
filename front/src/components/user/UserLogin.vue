<template>
    <v-app id="inspire">
    <v-main class="blue-grey lighten-5">
      <!-- Login Component -->
      <v-container style="max-width: 500px" fill-height>
        <v-layout align-center row wrap>
          <v-flex xs12>
            <v-alert
              type="error"
              v-if="isLoginError"
            >
              아이디 또는 비밀번호를 확인하세요.
            </v-alert>
            <v-card>
              <div class="pa-15">
                <h1 style="text-align: center" class="mb-10">Login</h1>
                <div class="d-flex flex-column"> 
                  <v-form
                    ref="form"
                    lazy-validation
                  >
                    <v-text-field
                      id="userid"
                      v-model="user.userid"
                      :rules="[v => !!v]"
                      class="mb-5"
                      label="ID"
                      prepend-inner-icon="mdi-account"
                      hide-details
                      required
                      @keyup.enter="confirm"
                    ></v-text-field>

                    <v-text-field
                      id="userpwd"
                      v-model="user.userpwd"
                      :rules="[v => !!v]"
                      class="mb-1" 
                      prepend-inner-icon="mdi-lock"
                      type="password"
                      label="Password"
                      hide-details
                      required
                      @keyup.enter="confirm"
                    ></v-text-field>

                    <div class="ma-0 pa-0">
                      <v-checkbox
                        class="ma-0 pa-0 mb-3 checkbox"
                        v-model="checkbox"
                        label="Remember me"
                        hide-details
                      ></v-checkbox>
                    </div>

                    <v-btn
                      color="blue lighten-1 text-capitalize"
                      depressed
                      large
                      block
                      dark
                      class="mb-3"
                      @click="confirm"
                    >
                      Login
                    </v-btn>
                    
                    <v-btn
                      color="blue lighten-1 text-capitalize"
                      depressed
                      large
                      block
                      dark
                      @click="moveRegistPage"
                    >
                      Sign Up
                    </v-btn>

                    <v-chip-group class="float-right mt-3">
                      <v-chip
                        class="ma-0"
                        color="blue"
                        outlined
                        small
                        link
                        @click="moveFindPwdPage"
                      >
                        Forgot Password?
                      </v-chip>
                    </v-chip-group>
                  </v-form>
                </div>
              </div>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
      },
      checkbox: false,
    };
  },

  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },

  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      this.$refs.form.validate();
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    },

    moveRegistPage() {
      this.$router.push({ name: "join" });
    },

    moveFindPwdPage() {
      this.$router.push({ name: "findpwd" });
    },
  },
};
</script>

<style></style>
