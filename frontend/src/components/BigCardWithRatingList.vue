<template>
  <div v-if="title && list && list.length !== 0" class="row">
    <div class="col s12 m12 l6 xl6 titleRow">{{ title }}</div>
  </div>

  <div class="row">
    <div class="col s12 m12 l6" v-for="elem in list" v-bind:key="elem.id">
      <big-card-with-rating
        v-bind:info="elem"
        @click="routeTo(elem)"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import BigCardWithRating from "@/components/BigCardWithRating.vue";
import { CardWithRatingInfo } from "@/model/CardWithRatingInfo";

export default defineComponent({
  name: "BigCardWithRatingList",
  components: {
    BigCardWithRating,
  },
  props: {
    list: {
      type: Array as PropType<CardWithRatingInfo[]>,
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
    routeTo(elem: CardInfo): void {
      if (this.routePage)
        this.$router.push({ name: this.routePage, params: { id: elem.id } });
    },
  },
});