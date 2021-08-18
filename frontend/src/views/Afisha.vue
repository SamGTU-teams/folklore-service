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

    <medium-card-list
      v-bind:list="bigCards"
      v-bind:title="'Подборки'"
    />
  </div>
</template>

<script lang="ts">
import SmallCardList from "@/components/SmallCardList.vue";
import MediumCardList from "@/components/MediumCardList.vue";
import { MainObject } from "@/model/MainObject";
import { defineComponent } from "vue";

export default defineComponent({
  name: "Afisha",
  components: {
    SmallCardList,
    MediumCardList,
  },
  data() {
    const nearbyActivities: MainObject[] = [
      {
        id: 1,
        name: `Экскурсия в Новгородский кремль`,
        address: `8, Новгородский кремль, Великий Новгород`,
        point: {lat: 0,lon: 0},
        labelUrl:"",
        imageUrl: `https://picsum.photos/1920/1080`,
        tags:[],
      },
      {
        id: 2,
        name: `Экскурсия в Софийский собор`,
        address: `11, Новгородский кремль, Великий Новгород`,
        point: {lat: 0,lon: 0},
        labelUrl:"",
        imageUrl: `https://picsum.photos/1921/1080`,
        tags:[],
      },
      {
        id: 3,
        name: `День в мужском монастыре Свято-Юрьев`,
        address: `Юрьевское ш., 10, Великий Новгород`,
        point: {lat: 0,lon: 0},
        labelUrl:"",
        imageUrl: `https://picsum.photos/1920/1081`,
        tags:[],
      }
    ]
    const recomendations: MainObject[] = nearbyActivities;
    return {
      nearbyActivities,
      recomendations,
      bigCards: [
        {
          imageUrl: `https://picsum.photos/1101/600`,
          name: `Экскурсии`,
          address: `8 событий`,
        },
        {
          imageUrl: `https://picsum.photos/1100/601`,
          name: `Шоу`,
          address: `11 событий`,
        },
        {
          imageUrl: `https://picsum.photos/1102/600`,
          name: `Библиотеки`,
          address: `5 событий`,
        },
        {
          imageUrl: `https://picsum.photos/1100/602`,
          // titleText: `Развлечения`,
          name: `Спектакли`,
          address: `11 событий`,
        },
      ],
    };
  },
});
</script>