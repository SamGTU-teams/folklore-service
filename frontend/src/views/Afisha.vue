<template>
  <div class="container">
    <small-card-list
      v-bind:list="nearbyActivities"
      v-bind:title="'В ближайшие дни'"
    />

    <small-card-list
      v-bind:list="recomendations"
      v-bind:title="'Рекомендовано по профилю'"
    />

    <medium-card-list v-bind:list="compilations" v-bind:title="'Подборки'" />
  </div>
</template>

<script lang="ts">
import SmallCardList from "@/components/SmallCardList.vue";
import MediumCardList from "@/components/MediumCardList.vue";
import { CardInfo } from "@/model/CardInfo";
import { defineComponent } from "vue";
import { Activity } from "@/model/Activity";

export default defineComponent({
  name: "Afisha",
  components: {
    SmallCardList,
    MediumCardList,
  },
  data() {
    const nearbyActivities: Activity[] = [
      new Activity(
        1,
        "Экскурсия в Новгородский кремль",
        "8, Новгородский кремль, Великий Новгород",
        { lat: 0, lon: 0 },
        1,
        "",
        "https://picsum.photos/1920/1080",
        [],
        3,
        true,
        new Date(),
        undefined,
        undefined,
        undefined
      ),
      new Activity(
        2,
        "Экскурсия в Софийский собор",
        "11, Новгородский кремль, Великий Новгород",
        { lat: 0, lon: 0 },
        1,
        "",
        "https://picsum.photos/1921/1080",
        [],
        3,
        true,
        new Date(),
        undefined,
        undefined,
        undefined
      ),
      new Activity(
        3,
        "День в мужском монастыре Свято-Юрьев",
        "Юрьевское ш., 10, Великий Новгород",
        { lat: 0, lon: 0 },
        2,
        "",
        "https://picsum.photos/1920/1081",
        [],
        3,
        true,
        new Date(),
        undefined,
        undefined,
        undefined
      ),
    ];
    const recomendations: Activity[] = nearbyActivities;

    const compilations: CardInfo[] = [
      {
        id: 1,
        title: "Экскурсии",
        subtitle: "8 событий",
        image: "https://picsum.photos/1101/600",
      },
      {
        id: 2,
        title: "Шоу",
        subtitle: "11 событий",
        image: "https://picsum.photos/1101/601",
      },
      {
        id: 3,
        title: "Библиотеки",
        subtitle: "5 событий",
        image: "https://picsum.photos/1100/601",
      },
      {
        id: 4,
        title: "Спектакли",
        subtitle: "11 событий",
        image: "https://picsum.photos/1100/600",
      },
    ];
    return {
      nearbyActivities,
      recomendations,
      compilations,
    };
  },
});
</script>