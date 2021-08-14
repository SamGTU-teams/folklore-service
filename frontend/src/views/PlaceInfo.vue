<template>
  <div id="main">
    <div id="PlaceInfo">
      <div id="Image">
        <img v-if="place.imageUrl" :src="place.imageUrl" />
        <img v-else src="@/assets/no-image.png" />
      </div>
      <div id="AboutPlace">
        <div id="NamePlace">{{ place.name }}</div>
        <div id="AddressPlace">{{ place.address }}</div>
        <div id="GoToAfisha">Go to Afisha Place id = {{ id }}</div>
      </div>
    </div>
    <div id="Description">{{ place.description }}</div>

    <div id="NearbyPlaces">
      <div style="padding-left: 15px">Рядом</div>
      <place-component v-bind:place="{id: 1, name: 'test name 1', address: 'test address 1'}"/>
      <place-component v-bind:place="{id: 2, name: 'test name 2', address: 'test address 2'}"/>
      <place-component v-bind:place="{id: 3, name: 'test name 3', address: 'test address 3'}"/>
      <div style="width: 100%; clear: both" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import PlaceComponent from "@/components/PlaceComponent.vue";
import { placeApi, Place } from "@/api/backend-api";

export default defineComponent({
  name: "PlaceInfo",
  props: ["id"],
  components: {
    PlaceComponent
  },
  data() {
    return {
      place: {
        id: -1,
        name: "",
        address: "",
        lat: 0,
        lon: 0,
        labelUrl: "",
        imageUrl: "",
        tags: [],
        mediaUrls: [],
        description: "",
      } as Place,
    };
  },
  created() {
    placeApi.getPlaceInfoById(this.id).then((response) => {
      this.place = response.data;
    });
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
