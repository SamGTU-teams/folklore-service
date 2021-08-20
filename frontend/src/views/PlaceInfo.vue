<template>
  <div id="main">
    <loader v-if="loadingMain" />
    <div v-else-if="place" class="container">
      <div class="row">
        <div class="col s12 m12 l6 xl6">
          <div class="img-container">
            <img v-if="place.imageUrl" :src="place.imageUrl" />
            <img v-else src="@/assets/no-image.png" />
          </div>
        </div>
        <div id="AboutPlace" class="col s12 m12 l6 xl6">
          <div class="tags">
            {{ place.tags.map(tag => "#" + tag.name).join(", ") }}
          </div>

          <div id="NamePlace">{{ place.name }}</div>

          <div id="AddressPlace">{{ place.address }}</div>

          <div
            id="GoToAfisha"
            @click="$router.push({ name: 'PlaceActivities', params: { id } })"
          >
            Посмотреть на афише
          </div>
        </div>
      </div>
      <div class="row">
        <div id="Description" v-if="place" v-html="place.description" />
      </div>
      <small-card-list
        v-bind:list="nearbyPlaces"
        v-bind:title="'Nearby Places'"
        v-bind:routePage="'PlaceInfo'"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
// import M from "materialize-css";
import Loader from "@/components/Loader.vue";
import { Point } from "@/model/Point";
import { Place } from "@/model/Place";
import placeApi from "@/api/PlaceApi";
import SmallCardList from "@/components/SmallCardList.vue";

export default defineComponent({
  name: "PlaceInfo",
  props: ["id"],
  components: {
    SmallCardList,
    Loader,
  },
  data() {
    return {
      place: null as Place | null,
      loadingMain: true,
      nearbyPlaces: [] as Place[],
    };
  },
  methods: {
    loadPlaceInfo(id: number) {
      placeApi.findPlaceInfoById(id).then((response) => {
        let data = response.data;
        this.place = placeApi.castResponse(data);
        this.loadingMain = false;
        this.loadNearbyPlaces(data.point);
      });
    },
    loadNearbyPlaces(point: Point) {
      placeApi.findNearbyPlaces(point, 3, 0).then((response) => {
        let data = response.data.content;
        this.nearbyPlaces = placeApi.castResponses(data);
      });
    },
  },
  created() {
    this.loadPlaceInfo(this.id);
  },
  beforeRouteUpdate(to: any, from, next) {
    this.loadingMain = true;
    this.place = null;
    this.nearbyPlaces = [];
    this.loadPlaceInfo(parseInt(to.params.id));
    next();
  },
  // mounted(){
  //   document.addEventListener("DOMContentLoaded", function() {
  //     var elems = document.querySelectorAll(".slider");
  //     var options = {
  //       indicators: false,
  //       height: "300",
  //     };
  //      var instances = M.Slider.init(elems, options);
  //   },
  // },
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
  font-size: calc(20px + (15 - 20) * ((100vw - 500px) / (1920 - 500)));
  /* margin-bottom: max(1.3rem, min(2.8rem, calc(100vw * 4 / 350))); */
  margin-bottom: 0;
}

#NamePlace {
  text-transform: uppercase;
  font-size: calc(40px + (35 - 40) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  margin-top: calc(50px + (0 - 50) * ((100vw - 500px) / (1920 - 500)));
  margin-bottom: max(1.5rem, min(2.8rem, calc(100vw * 4 / 350)));
  /* margin-bottom: 1%; */
}
#AddressPlace {
  font-size: calc(20px + (15 - 20) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  margin-bottom: max(1.5rem, min(2.8rem, calc(100vw * 4 / 350)));
  /* margin-bottom: 1%; */
}
#GoToAfisha {
  width: 100%;
  background-color: #e8edff;
  padding: 15px 0px;
  margin: 0 0;
  border-radius: 5px;
  text-transform: uppercase;
  color: #2b3f8d;
  text-align: center;
  font-size: calc(20px + (25 - 20) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}
#Description {
  padding: 0 0.75rem;
  text-indent: 20px;
  font-size: calc(25px + (20 - 25) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: #fff;
  text-align: justify;
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