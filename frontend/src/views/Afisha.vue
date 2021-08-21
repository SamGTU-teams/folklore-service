<template>
  <div class="container">
    <small-card-list
      v-bind:list="nearbyActivities"
      v-bind:title="'В ближайшие дни'"
      v-bind:routePage="'ActivityInfo'"
    />

    <small-card-list
      v-bind:list="recomendations"
      v-bind:title="'Рекомендовано по профилю'"
      v-bind:routePage="'ActivityInfo'"
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
import activityApi from "@/api/ActivityApi";
import { ActivityStatus } from "@/model/ActivityStatus";

export default defineComponent({
  name: "Afisha",
  components: {
    SmallCardList,
    MediumCardList,
  },
  data() {
    const nearbyActivities: Activity[] = [];
    const recomendations: Activity[] = [];

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
  mounted() {
    this.nearbyActivities = [];
    activityApi.findActivitiesByDateTime(new Date(), 3, 0, [])
    .then(response => {
      const scheduleds = response.data.content;
      const activities = scheduleds.map(scheduled => scheduled.activity);
      this.nearbyActivities = activityApi.castResponses(activities);
    });
    activityApi.findActivitiesByTagsIds([], 3, 0)
    .then(response => {
      const activities = response.data.content;
      this.recomendations = activityApi.castResponses(activities);
    })
  }
});
</script>