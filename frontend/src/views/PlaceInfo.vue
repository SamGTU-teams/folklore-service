<template>
  <div id="main">
    <loader v-if="loadingMain" />
    <div v-else-if='place' id="PlaceInfo">
      <div id="Image">
        <img v-if="place.imageUrl" :src="place.imageUrl" />
        <img v-else src="@/assets/no-image.png" />
      </div>
      <div id="AboutPlace">
        <div id="NamePlace">{{ place.name }}</div>
        <div id="AddressPlace">{{ place.address }}</div>
        <div
          id="GoToAfisha"
          @click="$router.push({ name: 'PlaceActivities', params: { id } })"
        >
          Go to Afisha Place id = {{ id }}
        </div>
      </div>
    </div>
    <div id="Description" v-if="place" v-html="place.description" />

    <div v-if='nearbyPlaces' id="NearbyPlaces">
      <div style="padding-left: 15px">Рядом</div>
      <place-component v-for="place in nearbyPlaces" v-bind:key="place.id" v-bind:place="place"/>
      <div style="width: 100%; clear: both" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import PlaceComponent from "@/components/PlaceComponent.vue";
import Loader from "@/components/Loader.vue";
import { placeApi, Place } from "@/api/backend-api";

export default defineComponent({
  name: "PlaceInfo",
  props: ["id"],
  components: {
    PlaceComponent,
    Loader,
  },
  data() {
    return {
      place: null as Place | null,
      loadingMain: true,
      nearbyPlaces: null as Place[] | null,
    };
  },
  methods: {
    loadPlaceInfo(id: number) {
      placeApi.getPlaceInfoById(id).then(response => {
        setTimeout(() => {
          let data = response.data;
          this.place = data;
          this.loadingMain = false;
          this.loadNearbyPlaces(data.lat, data.lon);
        }, 3000);
      });
    },
    loadNearbyPlaces(lat: number, lon: number) {
      placeApi.getNerbyPlaces(lat, lon, 3, 0)
      .then(response => {
        let data = response.data;
        this.nearbyPlaces = data.content;
      })
    }
  },
  created() {
    this.loadPlaceInfo(this.id);
  },
});
</script>

<style scoped>
body {
  margin: 0px;
  font-family: Arial;
}
#main {
  width: calc(100% - 20px);
  min-height: 75%;
  background: linear-gradient(to top, #373b3e, #687178);
  padding: 15px 10px;
}
#PlaceInfo {
  width: 100%;
  height: 50%;
}
#Image,
#AboutPlace {
  width: calc(50% - 7px);
  height: 100%;
  float: left;
  border-radius: 5px;
  /*background: #ccc;*/
}
#Image {
  text-align: center;
  margin-right: 7px;
}
#Image > img {
  height: 100%;
  border-radius: 5px;
}
#AboutPlace {
  margin-left: 7px;
}
#NamePlace,
#AddressPlace,
#GoToAfisha {
  text-align: center;
  width: 100%;
  font-weight: bold;
  font-size: calc(15px + (30 - 15) * ((100vw - 500px) / (1920 - 500)));
  border-radius: 5px;
  height: calc(100% / 3 - 20% / 3);
  color: #fff;
}
#NamePlace {
  background: linear-gradient(to top, #06366a, #047ff4);
  margin-bottom: 5%;
}
#AddressPlace {
  background: linear-gradient(to top, #267838, #39a050);
  margin: 5% 0px;
}
#GoToAfisha {
  background: linear-gradient(to top, #971a30, #cd2d49);
  margin-top: 5%;
}
#Description {
  width: calc(100% - 20px);
  margin-top: 15px;
  border-radius: 5px;
  padding: 10px;
  background: #6c777d;
  text-indent: 1.5em;
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
  border: 4px solid #824e5a;
  color: #824e5a;
}
</style>
