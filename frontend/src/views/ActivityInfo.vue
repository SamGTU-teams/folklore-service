<template>
  <div class="activivty">Activity {{ id }}</div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
// import M from "materialize-css";
// import Loader from "@/components/Loader.vue";
// import { Point } from "@/model/Point";
import { Activity } from "@/model/Activity";
import activityApi from "@/api/ActivityApi";

export default defineComponent({
  name: "ActivityInfo",
  props: ["id"],
  components: {
    // Loader,
  },
  data() {
    return {
      place: null as Activity | null,
      loadingMain: true,
      nearbyPlaces: [] as Activity[],
    };
  },
  methods: {
    loadActivityInfo(id: number) {
      activityApi.getActivityInfoById(id).then((response) => {
        let data = response.data;
        this.place = activityApi.castResponse(data);
        this.loadingMain = false;
      });
    },
  },
  created() {
    this.loadActivityInfo(this.id);
  },
  beforeRouteUpdate(to: any, from, next) {
    this.loadingMain = true;
    this.place = null;
    this.nearbyPlaces = [];
    this.loadActivityInfo(parseInt(to.params.id));
    next();
  },
});
</script>
<style scoped>

</style>