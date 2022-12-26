<template>
  <v-row>
    <v-col></v-col>
    <v-col
      v-model="user"
      sm="6"
      md="20"
      lg="5"
    > 
      <v-list dense class="mt-10">
        <v-list-item class="mb-3">
          <v-list-item-content>Name:</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ userInfo.username }}
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-content>ID:</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ userInfo.userid }}
          </v-list-item-content>
        </v-list-item>
  
        <v-list-item>
          <v-list-item-content>Password:</v-list-item-content>
          <v-list-item-content class="align-end">
            <v-text-field
              id="userpwd"
              v-model="changedPwd"
              type="password"
              hide-details
              required
            ></v-text-field>
          </v-list-item-content>
        </v-list-item>

        <v-list-item class="mb-3">
          <v-list-item-content>Email:</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ userInfo.email }}
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-content>Join Date:</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ userInfo.joindate }}
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-btn
        color="primary"
        class="mt-5"
        depressed
        large
        block
        dark
        @click="save"
      >
        수정
      </v-btn>

      <v-btn
        color="error"
        class="mt-2"
        depressed
        large
        block
        dark
        @click="leave"
      >
        탈퇴
      </v-btn>

      <v-alert
        type="success"
        v-if="saveBtn"
        class="mt-10 mb-0"
      >
        저장되었습니다.
      </v-alert>

      <v-alert
        dense
        outlined
        type="error"
        v-if="leaveBtn"
        class="mt-5"
      >
        <v-row align="center">
          <v-col>
            탈퇴하시겠습니까?
          </v-col>
          <v-col>
            <v-btn
              color="error"
              depressed
              outlined
              colored-border
              @click="confirmLeave"
            >
              Yes
            </v-btn>
            <v-btn
              color="error"
              class="mr-0 ml-1"
              depressed
              outlined
              colored-border
              dismissible
              @click="cancel"
            >
              No
            </v-btn>
          </v-col>
        </v-row>
      </v-alert>
    </v-col>
    <v-col></v-col>
  </v-row>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserInfoItem",
  data: () => ({
    user: {
      userid: null,
      userpwd: null,
    },
    changedPwd: null,
    saveBtn: false,
    leaveBtn: false,
  }),
  
  computed: {
    ...mapState(memberStore, ["userInfo", "isModify", "isLeave"]),
  },

  methods: {
    ...mapActions(memberStore, ["userModify", "userLeave", "userLogout"]),
    async save() {
      this.user.userid = this.userInfo.userid;
      this.user.userpwd = this.changedPwd;
      await this.userModify(this.user);
      if (this.isModify) {
        this.saveBtn = true;
        setTimeout(() => this.saveBtn = false, 3000);
      }
    },

    leave() {
      this.saveBtn = false;
      this.leaveBtn = true;
    },

    async confirmLeave() {
      this.user.userid = this.userInfo.userid;
      await this.userLeave(this.user);
      if (this.isLeave) {
        this.userLogout(this.userInfo.userid);
        sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
        sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
        if (this.$route.path != "/") this.$router.push({ name: "main" });
      }
    },

    cancel() {
      this.leaveBtn = false;
    },
  },
}
</script>

<style></style>
