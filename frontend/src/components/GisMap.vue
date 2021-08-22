<template>
  <div class="container main">
    <div id="TopMapFilters" class="row">
      <switcher :firstValue="'Объекты'" :secondValue="'События'" @changed-value="changedType"/>
    </div>
    <div class="row">
      <div id="MapContainer" class="col s12">
          <div id="map" class="col s12 m12 l12"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import M from "materialize-css";
import DG from "2gis-maps";

import Switcher from "@/components/Switcher.vue";

import { Point } from "@/model/Point";
import { Region } from "@/model/Region";
import { MainObject } from "@/model/MainObject";

import regionApi from "@/api/RegionApi";
import placeApi from "@/api/PlaceApi";
import activityApi from "@/api/ActivityApi";

export default defineComponent({
  name: "GisMap",
  components: {
    Switcher,
  },
  props: {
    center: {
      required: true,
    },
    zoom: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      map: null,
      isActivity: false,
      markers: DG.featureGroup(),
    };
  },
  mounted() {
    this.loadMap(this.center, this.zoom, "map");
  },
  methods: {
    changedType(value) {
      this.isActivity = value;
      this.removeMarkers();
    },

    loadMap(center, zoom, container) {
      this.map = DG.map(container, { center, zoom });
      this.markers.addTo(this.map);
      regionApi.getRegions(30, 0).then((response) => {
        const content = response.data.content;
        const regions = regionApi.castResponses(content);
        regions.forEach((region) => this.drawRegion(region));
      });
    },

    drawRegion(region) {
      const polygon = DG.polygon(region.points);
      polygon.addTo(this.map);
      const center = polygon.getCenter();
      polygon.on("click", () => this.regionClick(region, center));
    },

    regionClick(region, center) {
      this.map.setView(center, this.zoom);
      this.removeMarkers();
      this.loadMarkers(region);
    },

    loadMarkers(region) {
      if (this.isActivity) {
        activityApi.findByRegionId(region.id, 20, 0).then((response) => {
          const content = response.data.content;
          const activities = activityApi.castResponses(content);
          activities.forEach((activity) =>
            this.createMarker(activity, "ActivityInfo")
          );
        });
      } else {
        placeApi.findByRegionId(region.id, 20, 0).then((response) => {
          const content = response.data.content;
          const places = placeApi.castResponses(content);
          places.forEach((place) => this.createMarker(place, "PlaceInfo"));
        });
      }
    },

    createMarker(elem, routePage) {
      const icon = DG.icon({
        iconUrl: elem.labelUrl,
        iconSize: [30, 30],
      });
      const marker = DG.marker(elem.point, { icon });

      const popup = this.createPopup(elem, routePage);

      marker.bindPopup(popup);

      marker.addTo(this.markers);
    },

    createPopup(elem, routePage) {
      const root = document.createElement("div");
      root.setAttribute("class", "Popup");

      const label = document.createElement("div");
      label.setAttribute("class", "lable-container");
      root.append(label);

      const image = document.createElement("img");
      image.setAttribute("class", "img");
      image.setAttribute("width", "100%");
      image.setAttribute("src", elem.image);
      label.append(image);

      const name = document.createElement("div");
      name.setAttribute("class", "place-name");
      name.innerText = elem.name;
      label.append(name);

      const address = document.createElement("div");
      address.setAttribute("class", "place-address");
      address.innerText = elem.address;
      label.append(address);

      const button = document.createElement("div");
      button.setAttribute("class", "button");
      button.innerText = "Подробнее";
      button.onclick = () =>
        this.$router.push({ name: routePage, params: { id: elem.id } });

      label.append(button);

      return root;
    },

    removeMarkers() {
      this.markers.clearLayers();
    },
  },
});

document.addEventListener("DOMContentLoaded", function () {
  var elems = document.querySelectorAll(".collapsible");
  var options = { accordion: false };
  var instances = M.Collapsible.init(elems, options);
  document.querySelectorAll(".collapsible-body").forEach((elem) => {
    elem.setAttribute("style", "padding: 0px 0px");
  });
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
  color: #201f1c;
  font-weight: lighter;
  background: #ffbd00;
}
.button:hover {
  cursor: pointer;
  font-weight: bold;
}
.TopMapFilterSwitcher {
  float: right;
  margin-right: 30px;
  bottom: 15px;
}

#MapControll {
  position: absolute;
  height: 75vh;
  width: 20%;
  overflow: auto;
  border-radius: 3px;
  /* margin: 10px; */
  background: #201f1c;
}
#MapFilters,
#InfoRegion,
#PokaHz {
  padding-top: 30px;
  width: 100%;
  height: 100%;
  display: none;
}
#MapFilters {
  color: #201f1c;
  display: block;
}

.collapsible {
  border: 0px solid #201f1c !important;
  color: #fcd56b !important;
}

.collapsible-body {
  padding: 0px 0px !important;
  text-align: left;
}
.collapsible-body p {
  margin: 0px;
  padding: 7px 19px;
}
.collapsible-body p:hover {
  cursor: pointer;
  background-color: #fefbf7 !important;
  color: #00028b !important;
}

.collapsible-body p:hover span {
  color: #201f1c !important;
}

.collapsible-body p span {
  color: #fcd56b !important;
}

.collapsible-header:hover {
  background-color: #fefbf7 !important;
  color: #201f1c !important;
}

.collapsible-header {
  background-color: #201f1c !important;

  border-bottom: 1px solid #201f1c !important;
}

li.active .collapsible-header {
  font-weight: bold;
}
</style>
