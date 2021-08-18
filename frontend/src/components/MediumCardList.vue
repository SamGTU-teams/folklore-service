<template>
  <div v-if="title && list && list.length !== 0" class="row">
    <div class="col s12 m12 l6 xl6 titleRow">{{ title }}</div>
  </div>

  <div class="row">
    <div class="col s12 m12 l6" v-for="elem in list" v-bind:key="elem.id">
      <medium-card
        v-bind:info="elem"
        @click="routeTo(elem)"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import MediumCard from "@/components/MediumCard.vue";
import { CardInfo } from "@/model/CardInfo";

export default defineComponent({
  name: "MediumCardList",
  components: {
    MediumCard,
  },
  props: {
    list: {
      type: Array as PropType<CardInfo[]>,
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
</script>

<style scoped>
.titleRow {
  margin-top: 1%;
  text-align: left;
  font-size: 1.5em;
  font-weight: lighter;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
</style>