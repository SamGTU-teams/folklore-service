<template>
  <div id="main">
    <loader v-if="loadingMain" />
    <div v-else-if="activity" class="container">
      <div class="row">
        <div class="col s12 m12 l6 xl6">
          <div class="img-container">
            <img v-if="activity.imageUrl" :src="activity.imageUrl" />
            <img v-else src="@/assets/no-image.png" />
          </div>
        </div>
        <div id="AboutPlace" class="col s12 m12 l6 xl6">
          <div class="tags">
            {{ activity.tags.map(tag => "#" + tag.name).join(", ") }}
          </div>

          <div id="NamePlace">{{ activity.name }}</div>

          <div id="AddressPlace">{{ activity.address }}</div>
        </div>
      </div>
      <div class="row">
        <div id="Description" v-if="activity" v-html="activity.description" />
      </div>
      <small-card-list
        v-bind:list="nearbyActivities"
        v-bind:routePage="'ActivityInfo'"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
// import M from "materialize-css";
import Loader from "@/components/Loader.vue";
import SmallCardList from "@/components/SmallCardList.vue";
// import { Point } from "@/model/Point";
import { Activity } from "@/model/Activity";
import activityApi from "@/api/ActivityApi";

export default defineComponent({
  name: "ActivityInfo",
  props: ["id"],
  components: {
    Loader,
    SmallCardList
  },
  data() {
    return {
      activity: null as Activity | null,
      loadingMain: true,
      nearbyActivities: [] as Activity[],
    };
  },
  methods: {
    loadActivityInfo(id: number) {
      activityApi.findActivityInfoById(id).then(response => {
        const data = response.data;
        this.activity = activityApi.castResponse(data);
        this.loadingMain = false;
        this.loadNearbyActivities();
      });
    },

      loadNearbyActivities() {
        activityApi.findActivitiesByTags([], 3, 0)
        .then(response => {
          let data = response.data.content;
          this.nearbyActivities = activityApi.castResponses(data);
        })
        return;
      },
  },
  created() {
    this.loadActivityInfo(this.id);
  },
  beforeRouteUpdate(to: any, from, next) {
    this.loadingMain = true;
    this.activity = null;
    this.nearbyActivities = [];
    this.loadActivityInfo(parseInt(to.params.id));
    next();
  },
});
</script>

<style scoped>
body {
  margin: 0px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

#AboutPlace {
  position: relative;
}

.tags {
  padding-bottom: 2rem;
  height: 4rem;
  color: #e300ff;
  font-weight: 700;
  font-size: calc(20px + (15 - 20) * ((100vw - 500px) / (1920 - 500)));
  /* margin-bottom: max(1.3rem, min(2.8rem, calc(100vw * 4 / 350))); */
  margin-bottom: 0;
}

#NamePlace {
  
  color: #201F1C;
  font-weight: 700;
  font-size: calc(40px + (35 - 40) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  margin-top: calc(50px + (0 - 50) * ((100vw - 500px) / (1920 - 500)));
  margin-bottom: max(1.5rem, min(2.8rem, calc(100vw * 4 / 350)));
  text-transform: uppercase;
  /* margin-bottom: 1%; */
}
#AddressPlace {
  color: #201F1C;
  font-weight: 500;
  font-size: calc(20px + (15 - 20) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  margin-bottom: max(1.5rem, min(2.8rem, calc(100vw * 4 / 350)));
  /* margin-bottom: 1%; */
}
#GoToAfisha {
  width: 100%;
  background-color: #e300ff;
  padding: 15px 0px;
  margin: 0 0;
  border-radius: 5px;
  text-transform: uppercase;
  color: #FEFBF7;
  text-align: center;
  font-size: calc(20px + (25 - 20) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

#GoToAfisha:hover {
  cursor: pointer;
  font-weight: bold;
}

#Description {
  padding: 0 0.75rem;
  color: #201F1C;
  font-weight: lighter;
  font-size: calc(25px + (20 - 25) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: justify;
  text-indent: 20px;
}
#NearbyPlaces {
  width: calc(100% - 8px);
  padding-top: 15px;
  font-size: calc(15px + (30 - 15) * ((100vw - 500px) / (1920 - 500)));
  height: auto;
  border-radius: 5px;
  text-transform: uppercase;
  font-weight: bold;
  margin-top: 30px;
}

.img-container {
  position: relative;
  padding-bottom: 56.25%; /* задаёт высоту контейнера для 16:9 (если 4:3 — поставьте 75%) */
  height: 0;
  overflow: hidden;
  z-index: 1;
}

.img-container img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-width: 0;
  outline-width: 0;
}
</style>