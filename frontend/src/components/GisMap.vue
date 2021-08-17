<template>
  <div class="container">
    <div class="row">
      <div id="map" class="col s12 m12 l12"></div>
    </div>
    <div class="row tags">
      <div class="col s12 m12 l12">
        
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import DG from "2gis-maps";

export default defineComponent({
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
});
</script>

<style scoped>
#map {
  height: 65vh;
  width: 100%;
  border-radius: 5px;
}
</style>
