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
import placeApi from "@/api/PlaceApi";
import regionApi from "@/api/RegionApi";
import activityApi from "@/api/ActivityApi";

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
      map: null,
      regions: [],
    }
  },
  mounted() {
    this.map = DG.map("map", {
      center: [this.centerLat, this.centerLon],
      zoom: this.zoom,
    });
    regionApi.getRegions(30, 0).then(response => {
      this.regions = response.data.content;
      this.regions.forEach(region => this.createRegion(region));
    });
    placeApi.findPlacesByTags([], 30, 0).then(response => {
      this.places = response.data.content;
      this.places.forEach(place => this.createMarker(place));
    });
  },
  methods:{
    loadContentByRegion(region) {
      return;
    },

    createMarker(place) {
      const icon = DG.icon({
        iconUrl: place.labelUrl,
          iconSize: [30, 30],
      });
      const popup = this.buildPopup(place);
      DG.marker([place.point.lat, place.point.lon], { icon })
                                        .addTo(this.map)
                                        .bindPopup(popup);
    },

    createRegion(region) {
      return;
    },

    buildPopup(place) {
      return `<div class='Popup'>
                          <div class="lable-container">
                            <img class="img"style src='${place.imageUrl}' width="100%"><br>
                            <div class="place-name">${place.name}<br></div>
                            <div class="place-address">${place.address}<br></div>                         
                            <a href="/place/${place.id}">
                              <div class="button">
                                Подробнее                            
                              </div>
                            </a>
                          </div>
                        </div>`;
    }
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
  text-align: center;
}
.img{
  width: 100%;
  height: 100%;
  margin: calc(7px + (2.5 - 7) * ((100vw - 500px) / (1920 - 500))) 0;
}
.place-name,
.place-address {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.lable-container{
  margin: calc(10px + (15 - 5) * ((100vw - 500px) / (1920 - 500))) 0;
}

.place-name {
  font-size: calc(20px + (16 - 20) * ((100vw - 500px) / (1920 - 500)));
  padding-bottom: calc(7px + (2.5 - 7) * ((100vw - 500px) / (1920 - 500)));
}

.place-address {
  font-size: calc(16px + (12 - 16) * ((100vw - 500px) / (1920 - 500)));
  padding-bottom: calc(7px + (2.5 - 7) * ((100vw - 500px) / (1920 - 500)));
}

.button {
  width: 100%;
  border-radius: 5px;
  margin: calc(7px + (2.5 - 7) * ((100vw - 500px) / (1920 - 500))) 0;
  padding: calc(10px + (8 - 10) * ((100vw - 500px) / (1920 - 500))) 0;
  font-size: calc(18px + (16 - 18) * ((100vw - 500px) / (1920 - 500)));
  color: #fff;
  text-align: center;
  font-weight: lighter;
  background: #2b3f8d;
}
.button:hover {
  cursor: pointer;
  font-weight: bold;
}
</style>
