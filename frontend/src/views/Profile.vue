<template>
    <div id="Contet" class="container">
        <div id="SelectedTags">
        </div>
        <div id="TagsSelector" class="row">
            <div id="VectorSelector" class="col s12 m5 l4">
                <svg width="167" height="107" xmlns="http://www.w3.org/2000/svg">
                    <g id="ToGroupsSelector" class="VectSelector" v-on:click="SeViewToWector(1,0)">
                        <rect id="svg_1" height="50" width="81" y="1.85" x="1.5" fill="#fca"/>
                    </g>
                    <g id="ToDistasne" class="VectSelector" v-on:click="SeViewToWector(1,266)">
                        <rect id="svg_2" height="50" width="79" y="1.85" x="84.5" fill="#acf"/>
                    </g>
                    <g id="ToEvents" class="VectSelector" v-on:click="SeViewToWector(1,798)">
                        <rect id="svg_5" height="50" width="81" y="54.85" x="1.5" fill="#fcf"/>
                    </g>
                    <g id="ToHzChtoSelector" class="VectSelector" v-on:click="SeViewToWector(1,532)">
                        <rect id="svg_6" height="50" width="79" y="54.85" x="84.5" fill="#faf"/>
                    </g>
                </svg>
            </div>
            <div id="VectorsDiv" class="col s12 m7 l8">
                <svg id="Vectors" width="100%" height="266" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 500 266">
                    <g id="svg_2">
                        <rect id="svg_1" height="266" width="500" y="0" x="0" fill-opacity="null" stroke-opacity="null" stroke-width="1.5" stroke="#000" fill="#fca"/>
                        <rect v-on:click="AddTagToList(' #1')" class="SelectTagButton" height="50" width="50" y="100" x="50" stroke-width="1.5" stroke="#000" fill="#fff"/>
                        <rect class="SelectTagButton" height="50" width="50" y="100" x="120" fill-opacity="null" stroke-opacity="null" stroke-width="1.5" stroke="#000" fill="#fff"/>
                    </g>
                    <g id="svg_3">
                        <rect id="svg_1" height="266" width="500" y="266" x="0" fill-opacity="null" stroke-opacity="null" stroke-width="1.5" stroke="#000" fill="#acf"/>
                    </g>
                    <g id="svg_4">
                        <rect id="svg_1" height="266" width="500" y="532" x="0" fill-opacity="null" stroke-opacity="null" stroke-width="1.5" stroke="#000" fill="#faf"/>
                    </g>
                    <g id="svg_4">
                        <rect id="svg_1" height="266" width="500" y="798" x="0" fill-opacity="null" stroke-opacity="null" stroke-width="1.5" stroke="#000" fill="#fcf"/>
                    </g>
                </svg>
            </div>
        </div>
    </div>
</template>
<script>
import { defineComponent } from "vue";
import M from "materialize-css";

export default defineComponent({
  name: "Profile",
  components: {
  },
  methods:{
        SeViewToWector: function(time, resultHeight){
            document.querySelectorAll("#Vectors").forEach((elem)=> {
                var ViewHeight = elem.getAttribute("viewBox").split(" ")[1];
                var timeTic = time/100;
                var HeightTik = (resultHeight - ViewHeight)/(time/timeTic);
                var NumTic = 0;                        
                var animation = setInterval(() => {
                    var setHeight=ViewHeight/1+HeightTik*NumTic
                    if(NumTic>=time/timeTic){clearInterval(animation);
                        elem.setAttribute("viewBox","0 "+resultHeight+" 500 266");
                    }
                    elem.setAttribute("viewBox","0 "+setHeight+" 500 266");
                    NumTic++;
                },timeTic);  
                                          
            });
        },
        AddTagToList: function(nameTag){
            
            document.querySelectorAll("#SelectedTags").forEach((elem)=> {
                console.log(elem.innerHTML);
                var retInnerHtml = elem.innerHTML + nameTag;
                elem.innerHTML = retInnerHtml;
            });
        }
    },
});
</script>
<style>
    .VectSelector:hover{
        cursor: pointer;
        stroke-width: 1.5px;
        stroke: #000; 
    }
    .SelectTagButton{

    }
    .SelectTagButton:hover{
        cursor: pointer;
    }
</style>