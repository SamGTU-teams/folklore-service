<template>
  <div>
    <div>
      <img v-if="info.image" v-bind:src="info.image" />
      <img v-else src="@/assets/no-image.png" />
    </div>
    <div>
      <h1>{{ info.title }}</h1>
      <p>{{ info.subtitle }}</p>
    </div>
    <div>{{ info.rating }}</div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import { CardWithRatingInfo } from "@/model/CardWithRatingInfo";

export default defineComponent({
  name: "BigCardWithRating",
  props: {
    info: {
      type: Object as PropType<CardWithRatingInfo>,
      required: true,
    }
  }
});
</script>