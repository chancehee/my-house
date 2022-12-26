<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col show></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem";
import { mapState } from "vuex";
//import http from "@/api/http.js";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      housesOg: {},
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  created() {
    // http.post("/houseApi/house", { dong: "반석동" }).then(({ data }) => {
    //   console.log(data);
    //   this.housesOg = data;
    //   //this.articles = data; // 나중에 mutations에게 위임하자 !!!
    // });
  },
  methods: {
    showHousesOg() {
      console.log("show me the housesOg");
    },
  },
};
</script>

<style></style>
