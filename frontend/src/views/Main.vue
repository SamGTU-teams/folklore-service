<template>
  <gis-map :center="{lat:58.522875, lon: 31.269739}" :zoom="8" />
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
