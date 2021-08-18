<template>
  <div v-if="title && list" class="row">
    <div class="col s12 m12 l6 xl6 titleRow">{{ title }}</div>
  </div>

  <div class="row">
    <div class="col s12 m12 l6" v-for="elem in list" v-bind:key="elem.id">
      <medium-card
        v-bind:imgUrl="elem.imageUrl"
        v-bind:titleText="elem.name"
        v-bind:subtitleText="elem.address"
        @click="routeTo(elem.id)"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import MediumCard from "@/components/MediumCard.vue";
import { MainObject } from "@/model/MainObject";

export default defineComponent({
  name: "MediumCardList",
  components: {
    MediumCard,
  },
  props: {
    list: {
      type: Array as PropType<MainObject[]>,
      required: true,
    },
    title: {
      type: String,
      required: false,
    },
    routePage: {
      type: String,
      required: false,
    },
  },
  methods: {
    routeTo(id: any) {
      if (this.routePage)
        this.$router.push({ name: this.routePage, params: { id } });
    },
  },
});
</script>


