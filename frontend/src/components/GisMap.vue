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
      return{
        places:[]
      }
    },
  mounted() {
    let map = DG.map("map", {
      center: [this.centerLat, this.centerLon],
      zoom: this.zoom,
    });
    Api.getPlacesByTags(null,20,0)
    .then(response =>{
      console.log(1)
      this.places = response.data.content;
      this.places.forEach(place=>{
        var myIcon = DG.icon({
          iconUrl: place.labelUrl,
          iconSize: [30, 30],
        });
        var inPopap = `<div class='Popup'>
                          <img style src='${place.imageUrl}' width="100%"><br>
                          ${place.name}<br>
                          ${place.address}<br>                          
                          <a href="/place/${place.id}">
                            <div class="button">
                              К объекту                            
                            </div>
                          </a>
                        </div>`;
        // FIXME Обнови точки
        DG.marker([place.lat, place.lon],{icon: myIcon}).addTo(map).bindPopup(
          inPopap
        );
      })
    })
  },
});
</script>

<style >
#map {
  height: 65vh;
  width: 100%;
  border-radius: 5px;
}
.Popup{
  width: 150px;
  font-size: 15px;
  text-align: center;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}
.button{
  width: 100%;
  border-radius: 5px;
  padding: 10px 0;
  font-size: 18px;
  color: #fff;
  text-align: center;
  font-weight: lighter;
  background: #2b3f8d;
}
.button:hover{
  cursor: pointer;
  font-weight: bold;
}
</style>
