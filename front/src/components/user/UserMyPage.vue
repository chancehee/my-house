<template>
  <v-app id="inspire">
    <v-main class="blue-grey lighten-4">
      <v-container style="max-width: 1000px" fill-height>
        <v-layout align-center row wrap>
          <v-flex xs12>
            <v-card style="height: 600px">
              <v-tabs
                v-model="tab"
                fixed-tabs
                background-color="blue lighten-1 text-capitalize"
                centered
                dark
                icons-and-text
              >
                <!-- <v-tabs-slider></v-tabs-slider> -->
                <v-tab href="#tab-1">
                  관심 매물
                  <v-icon>mdi-heart</v-icon>
                </v-tab>

                <v-tab href="#tab-2">
                  내 정보
                  <v-icon>mdi-account-box</v-icon>
                </v-tab>

              </v-tabs>

              <v-tabs-items 
                v-model="tab"
              >
                <v-tab-item
                  value="tab-1"
                >
                  <user-fav-item></user-fav-item>
                </v-tab-item>
                  <v-tab-item
                    value="tab-2"
                  >
                  <user-info-item></user-info-item>
                </v-tab-item>
              </v-tabs-items>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import UserInfoItem from "@/components/user/item/UserInfoItem";
import UserFavItem from "@/components/user/item/UserFavItem";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  data: () => ({
    tab: null,
  }),
  
  components: {
    UserInfoItem,
    UserFavItem
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>