<template>
	<v-app id="inspire">
    <v-main class="blue-grey lighten-5">
      <v-container style="max-width: 500px" fill-height>
        <v-layout align-center row wrap>
          <v-flex xs12>
            <v-card>
              <div class="pa-15">
                <h1 style="text-align: center" class="mb-10">Create Account</h1>
                <div class="d-flex flex-column"> 
                  <v-form
										ref="form"
										lazy-validation
									>
										<v-text-field
											v-model="user.username"
											:rules="[v => !!v || 'Name is required']"
                      label="Your Name"
											required
                    ></v-text-field>

                    <v-text-field
                      v-model="user.userid"
											:rules="[v => !!v || 'ID is required']"
                      label="ID"
											required
                    ></v-text-field>

                    <v-text-field
											v-model="user.userpwd"
											:rules="[v => !!v || 'Password is required']"
                      type="password"
                      label="Password"
											required
                    ></v-text-field>

										<v-text-field
											v-model="user.confirmPassword"
											:rules="confirmPasswordRules"
                      type="password"
                      label="Repeat your password"
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
                      @click="join"
											depressed
                      large
                      block
                      dark
                    >
                      Sign Up
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
  name: "UserRegister",
	data() {
    return {
      user: {
        userid: null,
        userpwd: null,
        username: null,
        confirmPassword: null,
        email: null,
      },
      nameRules: [
          v => !!v || 'Name is required',
          v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
    }
	},

	computed: {
    ...mapState(memberStore, ["isRegist"]),
		confirmPasswordRules() {
			return [
				v => !!v || 'Confirm password is required',
				v => v === this.user.userpwd || 'Password confirmation does not match.',
			];
		},
	},

  methods: {
    ...mapActions(memberStore, ["userRegister"]),
		async join() {
			this.$refs.form.validate();
      await this.userRegister(this.user);
      if (this.isRegist) {
        this.$router.push({ name: "login" });
      }
		},
	},
}
</script>

<style></style>