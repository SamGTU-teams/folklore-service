<template>
  <div class="container main">
    <div id="TopMapFilters" class="row">
        <div class="switch TopMapFilterSwitcher">
          <label>
            Объекты
            <input type="checkbox">
            <span class="lever"></span>
            События
          </label>
        </div>
        <div class="switch TopMapFilterSwitcher">
          <label>
            Все
            <input type="checkbox">
            <span class="lever"></span>
            Аватар
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
              <div class="collapsible-header"><i class="material-icons">place</i>Second</div>
              <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
            </li>
            <li>
              <div class="collapsible-header"><i class="material-icons">whatshot</i>Third</div>
              <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
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
  var elems = document.querySelectorAll('.collapsible');
  var options = {accordion: false};
  var instances = M.Collapsible.init(elems,options);
  document.querySelectorAll('.collapsible-body').forEach((elem)=> {
    elem.setAttribute("style","padding: 0px 0px");
  });
});
</script>
<style>
  .TopMapFilterSwitcher{
    float: right;
    margin-right: 30px;
    bottom: 15px;
  }
  #MapControll{
    position: absolute;
    height: 75vh;
    width: 20%;
    overflow: auto;
    border-radius: 7px;
    background: #FFF;
  }
  #MapFilters,#InfoRegion,#PokaHz{
    padding-top: 30px;
    width:100%;
    height: 100%;
    display: none;
  }
  #MapFilters{
    color: #000;
    display: block;
  }
  .collapsible-body{
    padding-top: 0px;
    padding-right: 0px;
    padding-bottom: 0px;
    padding-left: 0px;
    text-align: left;
  }
  .collapsible-body P{    
    margin: 0px;
    padding: 7px 10px;
  }
  .collapsible-body P:hover{
    cursor: pointer;
    background-color: #E8EDFF;
    color: #2B3F8D;    
  }
  .collapsible-body P:hover span{
    color: #2B3F8D;    
  }
  .collapsible-body P span{
    color: #000;
  }
  .collapsible-header:hover{
    background-color:#E8EDFF ;
    color: #2B3F8D;
  }
  li.active .collapsible-header{
    font-weight: bold;
  }
  .switch label{
    color:#FFF;
    font-size: 18px;
  } 
</style>
