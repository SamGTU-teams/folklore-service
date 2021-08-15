<template>
  <div id="map"></div>
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
#map {
  width: 70%;
  height: 80%;
  margin-left: 15%;
}
</style>