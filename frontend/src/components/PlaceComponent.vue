<template>
  <div
    class="CloseObject"
    @click="$router.push({ name: 'PlaceInfo', params: { id: place.id } })"
  >
    <div class="CloseObjectIMG">
      <img v-if="place.imageUrl" :src="place.imageUrl" />
      <img v-else src="@/assets/no-image.png" />
    </div>
    <div class="CloseObjectName">{{ place.name }}</div>
    <div class="CloseObjectAddress">{{ place.address }}</div>
  </div>
</template>

<script lang="ts">
import { Place } from "@/model/Place";
import { defineComponent, PropType } from "vue";

export default defineComponent({
  name: "PlaceComponent",
  props: {
    place: {
      type: Object as PropType<Place>,
      required: true,
    },
  },
});
</script>

<style scoped>
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