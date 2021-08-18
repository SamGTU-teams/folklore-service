<template>
  <div v-if="title && list && list.length !== 0" class="row">
    <div class="col s12 m12 l12 titleRow">{{ title }}</div>
  </div>

  <div class="row">
    <div class="col s12 m12 l4 xl4" v-for="elem in list" v-bind:key="elem.id">
      <small-card
        v-bind:info="elem"
        @click="routeTo(elem)"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import SmallCard from "@/components/SmallCard.vue";
import { CardInfo } from "@/model/CardInfo";

export default defineComponent({
  name: "SmallCardList",
  components: {
    SmallCard,
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
    routeTo(card: CardInfo) {
      if (this.routePage)
        this.$router.push({ name: this.routePage, params: { id: card.id } });
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

