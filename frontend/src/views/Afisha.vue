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

    <div class="row">
      <div class="col s12 m12 l6 xl6 titleRow">
        Подборки
      </div>
    </div>

    <div class="row">
      <div class="col s12 m12 l6" v-for="card in bigCards" v-bind:key="card">
        <big-card
          v-bind:imgUrl="card.imgUrl"
          v-bind:titleText="card.titleText"
          v-bind:subtitleText="card.subtitleText"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import SmallCardList from "@/components/SmallCardList.vue";
import BigCard from "@/components/BigCard.vue";
import { MainObject } from "@/model/MainObject";
import { defineComponent } from "vue";

export default defineComponent({
  name: "Afisha",
  components: {
    SmallCardList,
    BigCard,
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
          imgUrl: `https://picsum.photos/1101/600`,
          titleText: `Экскурсии`,
          subtitleText: `8 событий`,
        },
        {
          imgUrl: `https://picsum.photos/1100/601`,
          titleText: `Шоу`,
          subtitleText: `11 событий`,
        },
        {
          imgUrl: `https://picsum.photos/1102/600`,
          titleText: `Библиотеки`,
          subtitleText: `5 событий`,
        },
        {
          imgUrl: `https://picsum.photos/1100/602`,
          // titleText: `Развлечения`,
          titleText: `Спектакли`,
          subtitleText: `11 событий`,
        },
      ],
    };
  },
});
</script>

<style>
.titleRow {
  margin-top: 1%;
  text-align: left;
  font-size: 1.5em;
  font-weight: lighter;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
</style>
