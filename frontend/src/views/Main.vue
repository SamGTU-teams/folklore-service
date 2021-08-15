<template>
  <div class="main">
    <gis-map :centerLat="58.522875" :zoom="8" :centerLon="31.269739" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import GisMap from "@/components/GisMap.vue";

export default defineComponent({
  name: "Main",
  components: {
    GisMap,
  },
});
</script>
