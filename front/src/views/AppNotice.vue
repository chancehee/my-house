<template>
  <v-app class="inspire">
    <v-main class="blue-grey lighten-5">
      <router-view></router-view>
    </v-main>
  </v-app>
</template>
<script>
export default {
  name: "AppNotice",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
