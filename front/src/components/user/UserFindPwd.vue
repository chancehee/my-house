<template>
  <v-app id="inspire">
    <v-main class="blue-grey lighten-5">
      <v-container style="max-width: 500px" fill-height>
        <v-layout align-center row wrap>
          <v-flex xs12>
            <v-card>
              <div class="pa-15">
                <h1 style="text-align: center" class="mb-10">
                  Find Password
                </h1>
                <div class="d-flex flex-column"> 
                  <v-form
										ref="form"
										lazy-validation
									>
                    <v-text-field
                      v-model="user.userid"
											:rules="[v => !!v || 'ID is required']"
                      label="ID"
											required
                    ></v-text-field>

										<v-text-field
											v-model="user.email"
											:rules="emailRules"
											label="Your E-mail"
											required
										></v-text-field>
										
                    <v-btn
											class="mt-3 mb-3"
                      color="blue lighten-1 text-capitalize"
                      @click="sendEmail"
											depressed
                      large
                      block
                      dark
                    >
                      Send Email
                    </v-btn>
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
  name: "UserFindPwd",
  data() {
    return {
      user: {
        userid: null,
        email: null,
      },
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
    }
  },

  computed: {
    ...mapState(memberStore, ["isSendEmail"]),
  },

  methods: {
    ...mapActions(memberStore, ["userSendEmail"]),
    async sendEmail() {
      this.$refs.form.validate();
      await this.userSendEmail(this.user);
      if (this.isSendEmail) {
        this.$router.push({ name: "login" });
      }
    },
  },
}
</script>

<style>

</style>