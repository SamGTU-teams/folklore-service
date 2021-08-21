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
      setplases: []
    }
  },
  mounted() {
    this.map = DG.map("map", {
      center: [this.centerLat, this.centerLon],
      zoom: this.zoom,
    });
    // placeApi.findPlacesByTags([], 60, 0).then(response => {
    //   this.places = response.data.content;
    //   this.places.forEach(place => this.createMarker(place));
    // });
    regionApi.getRegions(30, 0).then(response => {
      const data = response.data.content;
      this.regions = regionApi.castResponses(data);
      this.regions.forEach(region => this.createRegion(region));
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
      var marker = DG.marker([place.point.lat, place.point.lon], { icon })
                                        .bindPopup(popup);
      return marker;
    },
    OnClickByRegion(region,Center){
      document.querySelectorAll("#NameSelectedRegion").forEach((elem)=> { //Переписать Название района
        elem.setAttribute("innerHTML",region.name);
      });
      document.querySelectorAll("#ImgSelectedRegion").forEach((elem)=> { //Переписать URL картинки
        elem.setAttribute("src",region.imgUrl);
      });
      this.map.setView([Center.lat,Center.lng],9);
      this.clearMapByMarcers();
      this.CreateRegionMarcers(region.id);
    },
    createRegion(region) {
          var pointsLayer = [];
          region.points.forEach( point =>{
            pointsLayer[pointsLayer.length]=[point.lat, point.lon];
          });          
          // console.log(pointsLayer);
          const func = () => this.OnClickByRegion(region,poligon.getCenter());
          var poligon = DG.polygon(pointsLayer).addTo(this.map);
          poligon.on('click', func)
        .addTo(this.map);
        //this.CreateRegionMarcers(region.id);
    },
    buildPopup(place) {
      return `<div class='Popup'>
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
    },
    LoadAllMarcers(){
      placeApi.findPlacesByTags([], 60, 0).then(response => {
        this.places = response.data.content;
        this.places.forEach(place => this.createMarker(place));
      });
    },
    CreateRegionMarcers(IdRegion){ 
      placeApi.findByRegionId(IdRegion).then(response => {
        this.places= response.data.content;
        for(var i=0;i<this.places.length; i++){
          this.setplases[i] = this.createMarker(this.places[i])
          this.setplases[i].addTo(this.map);
        }
        });
        // this.places.map(place =>{
        //   this.setplases[0] = this.createMarker(place);
        //   console.log(this.setplases);
        //  
    },
    clearMapByMarcers(){
      if(this.map.markers){
        this.map.markers.removeAll();
      }
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
  color: #201F1C;
  font-weight: lighter;
  background: #FFBD00;
}
.button:hover {
  cursor: pointer;
  font-weight: bold;
}
</style>
