<template>
  <div class="container main">
    <div id="TopMapFilters" class="row">
      <div class="switch TopMapFilterSwitcher">
        <label class="swithText">
          Все
          <input type="checkbox" />
          <span class="lever"></span>
          Рекомендовано
        </label>
      </div>
      <div class="switch TopMapFilterSwitcher">
        <label class="swithText">
          Объекты
          <input type="checkbox" />
          <span class="lever"></span>
          События
        </label>
      </div>
    </div>
    <div class="row">
      <div id="MapContainer" class="col s12">
        <div class="row">
          <div id="map" class="col s12 m12 l12"></div>
        </div>
      </div>
      <div id="MapControll">
        <div id="MapFilters">
          <ul class="collapsible">
            <li>
              <div class="collapsible-header">
                <i class="material-icons">filter_drama</i>
                First
              </div>
              <div class="collapsible-body" style="padding: 0px 0px">
                <form action="#">
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px">1</span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px">2</span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px">3</span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px">4</span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px">5</span>
                    </label>
                  </p>
                </form>
              </div>
            </li>
            <li>
              <div class="collapsible-header">
                <i class="material-icons">place</i>Second
              </div>
              <div class="collapsible-body">
                <span>Lorem ipsum dolor sit amet.</span>
              </div>
            </li>
            <li>
              <div class="collapsible-header">
                <i class="material-icons">whatshot</i>Third
              </div>
              <div class="collapsible-body">
                <span>Lorem ipsum dolor sit amet.</span>
              </div>
            </li>
          </ul>
        </div>
        <div id="InfoRegion"></div>
        <div id="PokaHz"></div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import M from "materialize-css";
import DG from "2gis-maps";

import { Point } from "@/model/Point";
import { Region } from "@/model/Region";
import { MainObject } from "@/model/MainObject";

import regionApi from "@/api/RegionApi";
import placeApi from "@/api/PlaceApi";

export default defineComponent({
  name: "GisMap",
  props: {
    center: {
      type: Object as PropType<Point>,
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
      regions: [] as Region[],
      isActivity: false,
      markers: DG.featureGroup(),
    };
  },
  mounted() {
    this.loadMap(this.center, this.zoom, "map");
  },
  methods: {
    loadMap(center: Point, zoom: number, container: string): any {
      this.map = DG.map(container, { center, zoom });
      this.markers.addTo(this.map);
      regionApi.getRegions(30, 0).then((response) => {
        const content = response.data.content;
        this.regions = regionApi.castResponses(content);
        this.regions.forEach((region) => this.drawRegion(region));
      });
    },
    drawRegion(region: Region) {
      const polygon = DG.polygon(region.points);
      polygon.addTo(this.map);
      const center = polygon.getCenter();
      polygon.on("click", () => this.regionClick(region, center));
    },
    regionClick(region: Region, center) {
      this.map.setView(center, this.zoom);
      this.removeMarkers();
      this.loadMarkers(region);
    },
    loadMarkers(region: Region) {
      if (this.isActivity) {
        return;
      } else {
        placeApi.findByRegionId(region.id, 20, 0).then((response) => {
          const content = response.data.content;
          const places = placeApi.castResponses(content);
          places.forEach((place) => this.createMarker(place));
        });
      }
    },

    createMarker(obj: MainObject) {
      const icon = DG.icon({
        iconUrl: obj.labelUrl,
        iconSize: [30, 30],
      });
      const marker = DG.marker(obj.point, { icon });
      
      marker.addTo(this.markers);
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

.swithText {
  color: #00028b !important;
}

.switch label input[type="checkbox"]:checked + .lever:before,
.swithText {
  font-weight: bold;
}

.switch label input[type="checkbox"]:checked + .lever:before,
.swithText {
  font-weight: normal;
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
.switch label {
  color: #fefbf7;
  font-size: 18px;
}

.switch label input[type="checkbox"]:checked + .lever {
  background-color: #ec79fc !important;
}

.switch label input[type="checkbox"]:checked + .lever:after {
  background-color: #e300ff !important;
}

/* [type="checkbox"]+span:not(.lever):after, [type="checkbox"]:not(.filled-in)+span:not(.lever):after {
    border: 2px solid #FEFBF7 !important;
} */

[type="checkbox"]:not(.filled-in) + span:not(.lever):before,
.collapsible-body p:hover span:before {
  border-right: 2px solid #ffbd00 !important;
  border-bottom: 2px solid #ffbd00 !important;
  border-left: 2px solid #ffbd00 !important;
  border-top: 2px solid #ffbd00 !important;
}

[type="checkbox"]:not(.filled-in) + span:not(.lever):before {
  border-right: 2px solid #fcd56b !important;
  border-bottom: 2px solid #fcd56b !important;
  border-left: 2px solid #fcd56b !important;
  border-top: 2px solid #fcd56b !important;
}

[type="checkbox"]:checked + span:not(.lever):before {
  border-right: 2px solid #e300ff !important;
  border-bottom: 2px solid #e300ff !important;
  border-left: 2px solid #ffbb0000 !important;
  border-top: 2px solid #ffbb0000 !important;
}
</style>
