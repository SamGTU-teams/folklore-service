<template>
  <div class="container">
    <div class="row">
      <div id="map" class="col s12 m12 l12"></div>
    </div>
  </div>
</template>

<script>
import DG from "2gis-maps";

export default {
  name: "GisMap",
  props: {
    centerLat: {
      type: Number,
      required: true,
    },
    centerLon: {
      type: Number,
      required: true,
    },
    zoom: {
      type: Number,
      required: true,
    },
  },
  mounted() {
    let map = DG.map("map", {
      center: [this.centerLat, this.centerLon],
      zoom: this.zoom,
    });
  },
};
</script>

<style scoped>
.s12 {
  height: 50vh;
}
.m12 {
  height: 75vh;
}
#map {
  width: 100%;
  border-radius: 5px;
}
</style>
