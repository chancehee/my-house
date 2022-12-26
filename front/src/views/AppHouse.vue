<template>
  <b-container class="home">
    <div class="map-area">
      <ka-kao-map />
    </div>
  </b-container>
</template>

<script>
import KaKaoMap from "@/components/KaKaoMap";

export default {
  name: "HomeView",
  components: {
    KaKaoMap,
  },
  computed: {},

  data() {
    return {
      isColor: false,
    };
  },
  mounted() {},
  methods: {},
};
</script>

<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
button {
  border: 1px solid transparent;
  padding: 6px;
  background-color: darkgray;
  border-radius: 6px;
}
</style>
