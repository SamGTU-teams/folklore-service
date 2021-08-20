<template>
    <div class="row">
      <div id="map" class="col s12 m12 l12"></div>
    </div>
    <!-- <div class="row tags">
      <div class="col s12 m12 l12"></div>
    </div> -->

</template>

<script>
import { defineComponent } from "vue";
import DG from "2gis-maps";
import Api from "@/api/PlaceApi";

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
  data() {
    return {
      places: [],
      AllPlases: [],
      PersonalPlases: []
    }
  },
  methods:{
    METOD(){
      this.AllPlases
    },
  },
  mounted() {
    this.AllPlases=[];
    this.PersonalPlases=[];
    let map = DG.map("map", {
      center: [this.centerLat, this.centerLon],
      zoom: this.zoom,
    });
    Api.getPlacesByTags(null, 20, 0).then((response) => {
      console.log(1);
      this.places = response.data.content;
      this.places.forEach((place) => {
        var myIcon = DG.icon({
          iconUrl: place.labelUrl,
          iconSize: [30, 30],
        });
        var inPopap = `<div class='Popup'>
                          <div class="lable-container">
                            <img class="img" style src='${place.imageUrl}' width="100%">
                            <div class="place-name">${place.name}</div>
                            <div class="place-address">${place.address}</div>                         
                            <a href="/place/${place.id}">
                                <div class="button">
                                Подробнее                         
                                </div>
                            </a>
                          </div>
                        </div>`;
        // FIXME Обнови точки
        AllPlases[AllPlases.length]=DG.marker([place.lat, place.lon], { icon: myIcon })
                                        .addTo(map)
                                        .bindPopup(inPopap);
        if(place.id % 2 === 0){
          PersonalPlases[PersonalPlases.length]=DG.marker([place.lat, place.lon], { icon: myIcon })
            .bindPopup(inPopap);
        }
      });
      console.log(PersonalPlases);
      console.log(AllPlases);
    });
  },
});
</script>

<style>
#map {
  height: 75vh;
  width: 100%;
  border-radius: 5px;
}
.Popup {
  width: 150px;
  height: 245px;
  text-align: center;
}
.img {
  width: 100%;
  height: 100%;
  margin: 7px 0px;
  /* margin: calc(7px + (2.5 - 7) * ((100vw - 500px) / (1920 - 500))) 0; */
}
.place-name,
.place-address {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.lable-container {
  margin: calc(10px + (15 - 5) * ((100vw - 500px) / (1920 - 500))) 0;
}

.place-name {
  height: 50px;
  font-size: 20px;
  overflow: hidden;
  /* font-size: calc(20px + (16 - 20) * ((100vw - 500px) / (1920 - 500))); */
  /* padding-bottom: 7px; */
  margin: 7px 0;
  /* padding-bottom: calc(7px + (2.5 - 7) * ((100vw - 500px) / (1920 - 500))); */
}

.place-address {
  height: 20px;
  font-size: 16px;
  overflow: hidden;
  /* font-size: calc(16px + (12 - 16) * ((100vw - 500px) / (1920 - 500))); */
  /* padding-bottom: 7px; */
  margin: 7px 0;
  /* padding-bottom: calc(7px + (2.5 - 7) * ((100vw - 500px) / (1920 - 500))); */
}

.button {
  width: 100%;
  height: 40px;
  padding-top: 15px;
  border-radius: 5px;
  /* margin: 7px 0px; */
  /* margin: calc(7px + (2.5 - 7) * ((100vw - 500px) / (1920 - 500))) 0; */
  /* padding: 10px 0px; */
  /* padding: calc(10px + (8 - 10) * ((100vw - 500px) / (1920 - 500))) 0; */
  font-size: 18px;
  /* font-size: calc(18px + (16 - 18) * ((100vw - 500px) / (1920 - 500))); */
  color: #fff;
  font-weight: lighter;
  background: #2b3f8d;
}
.button:hover {
  cursor: pointer;
  font-weight: bold;
}
</style>
