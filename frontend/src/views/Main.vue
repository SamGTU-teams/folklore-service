<template>
  <div class="container main">
    <div class="row">
      <div class="slider">
        <ul class="slides">
          <li>
            <img src="https://cdn.discordapp.com/attachments/747497720926699573/878857519701843998/TITLE-BANNER.jpg"> <!-- random image -->
          </li>          
          <li>
              <a href="#TopMapFilters">
              <img src="https://lorempixel.com/580/250/nature/2"> <!-- random image -->
              <div class="caption left-align">
                <h3>Ультра пиздатая карта</h3>
                <h5 class="light grey-text text-lighten-3">С фильтрами</h5>
              </div>
              </a>
          </li>          
          <li>
            <img src="https://lorempixel.com/580/250/nature/3"> <!-- random image -->
            <div class="caption right-align">
              <h3>Ахуенное AR приложение</h3>
              <h5 class="light grey-text text-lighten-3">С журнальчиком :D</h5>
            </div>
          </li>
        </ul>
      </div>
    </div>
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
        <gis-map :centerLat="58.522875" :zoom="8" :centerLon="31.269739" />
      </div>
      <div id="MapControll">
        <div id="MapFilters">
          <ul class="collapsible">
            <li>
              <div class="collapsible-header">
                <i class="material-icons">filter_drama</i>
                First
              </div>
              <div class="collapsible-body" style="padding: 0px 0px;">
                <form action="#">
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px;">1</span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px;">2</span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px;">3</span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px;">4</span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input type="checkbox" />
                      <span style="font-size: 18px;">5</span>
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
import { defineComponent } from "vue";
import GisMap from "@/components/GisMap.vue";
import M from "materialize-css";

export default defineComponent({
  name: "Main",
  components: {
    GisMap,
  },
});
document.addEventListener('DOMContentLoaded', function() {    
    var elems = document.querySelectorAll('.slider');
    var options ={
      indicators: true,
      height: 450,
    };
    var instances = M.Slider.init(elems, options);
  });
document.addEventListener("DOMContentLoaded", function() {
  var elems = document.querySelectorAll(".collapsible");
  var options = { accordion: false };
  var instances = M.Collapsible.init(elems, options);
  document.querySelectorAll(".collapsible-body").forEach((elem) => {
    elem.setAttribute("style", "padding: 0px 0px");
  });
});
</script>
<style>
.TopMapFilterSwitcher {
  float: right;
  margin-right: 30px;
  bottom: 15px;
}

.swithText{
  color:#00028b !important;
}

.switch label input[type="checkbox"]:checked + .lever:before, .swithText{
  font-weight: bold;
}

.switch label input[type="checkbox"]:checked +.lever:before, .swithText{
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

[type="checkbox"]:not(.filled-in) + span:not(.lever):before, .collapsible-body p:hover span:before {
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
#Conteiner{
  overflow: hidden;
}
.MetodSelector{
  height: 200px;
  padding: 5px !important;
}
.MetodSelectorPart{
  font-size: 26px;
  width: 100%;
  height: 100%;
  border-radius: 15px;
  background: #fcd56b;
}
.slider .indicators .indicator-item.active {
    background-color: #00028b !important;
}
</style>
