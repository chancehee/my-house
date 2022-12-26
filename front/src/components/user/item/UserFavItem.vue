<template>
  <div>
    <v-container fluid>
      <v-row dense>
        <v-col 
          v-for="favorite in favorites"
          :key = "favorite.no"
          :cols="4"
        >
          <v-card>
            <v-img
              :src="require(`@/assets/mainHouse.jpg`)"
              class="white--text align-center"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="240px"
            >
              <v-card-title v-text="favorite.apartmentName"></v-card-title>
              <v-card-text> 면적: {{favorite.area}} </v-card-text>
              <v-card-text>거래년월: {{favorite.dealYear}} / {{favorite.dealMonth}}</v-card-text>
              <font-awesome-icon
          icon="fa-solid fa-heart"
          size="2x"
          color = "red"/>

            </v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "UserFavItem",
  data: () => ({
    favorites: [],
    houses : [],
    isLiked: null,
  }),

  computed: {
    
  },
  mounted() {
    http.get(`/favoriteApi/favorite/chan`).then(({data}) => {
      console.log(data);
      this.favorites = data;
    });

    
    
  },
}
</script>

<style>

</style>