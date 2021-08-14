<template>
  <div id="main">
    <div id="ObjectInfo">
      <div id="Image">
        <img v-if="building.imageUrl" :src="building.imageUrl" />
        <img v-else src="@/assets/no-image.png" />
      </div>
      <div id="AboutObject">
        <div id="NameObject">{{ building.name }}</div>
        <div id="Address">{{ building.address }}</div>
        <div id="GoToAfisha">Go to Afisha Building id = {{ id }}</div>
      </div>
    </div>
    <div id="Description">{{ building.description }}</div>

    <div id="CloseObjects">
      <div style="padding-left: 15px">Рядом</div>
      <div class="CloseObject">
        <div class="CloseObjectIMG">
          <img src="image.jpg" />
        </div>
        <div class="CloseObjectName">Название</div>
        <div class="CloseObjectAddress">Адрес</div>
      </div>
      <div class="CloseObject">
        <div class="CloseObjectIMG">
          <img src="image.jpg" />
        </div>
        <div class="CloseObjectName">Название</div>
        <div class="CloseObjectAddress">Адрес</div>
      </div>
      <div class="CloseObject">
        <div class="CloseObjectIMG">
          <img src="image.jpg" />
        </div>
        <div class="CloseObjectName">Название</div>
        <div class="CloseObjectAddress">Адрес</div>
      </div>
      <div style="width: 100%; clear: both" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { buildingApi, Building } from "@/api/backend-api";

export default defineComponent({
  name: "BuildingInfo",
  props: ["id"],
  data() {
    return {
      building: {
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
      } as Building,
    };
  },
  created() {
    buildingApi.getBuildingInfoById(this.id).then((response) => {
      this.building = response.data;
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
#ObjectInfo {
  width: 100%;
  height: 50%;
}
#Image,
#AboutObject {
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
#AboutObject {
  margin-left: 7px;
}
#NameObject,
#Address,
#GoToAfisha {
  text-align: center;
  width: 100%;
  font-weight: bold;
  font-size: calc(15px + (30 - 15) * ((100vw - 500px) / (1920 - 500)));
  border-radius: 5px;
  height: calc(100% / 3 - 20% / 3);
  color: #fff;
}
#NameObject {
  background: linear-gradient(to top, #06366a, #047ff4);
  margin-bottom: 5%;
}
#Address {
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
#CloseObjects {
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
.CloseObject {
  width: calc(100% / 3 - 15px * 2);
  padding: 0px 15px;
  padding: relative;
  text-align: left;
  float: left;
  margin-top: 20px;
}
.CloseObjectIMG {
  width: 100%;
  overflow: hidden;
  margin-bottom: 10px;
}
.CloseObjectIMG > img {
  width: 100%;
  border-radius: 5px;
}
.CloseObjectName,
.CloseObjectAddress {
  margin-bottom: 10px;
  font-size: calc(15px + (30 - 15) * ((100vw - 500px) / (1920 - 500)));
  text-transform: uppercase;
  font-weight: bold;
}
.CloseObjectAddress {
  font-size: calc(12px + (24 - 12) * ((100vw - 500px) / (1920 - 500)));
}
</style>
