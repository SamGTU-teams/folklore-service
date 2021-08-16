<template>
  <div class="container">
    <div class="row">
      <div class="col s12 m12 l12 titleRow">
        В ближайшие дни
      </div>
    </div>

    <div class="row">
      <div
        class="col s12 m12 l12 xl4"
        v-for="card in smallCards"
        v-bind:key="card"
      >
        <small-card
          v-bind:imgUrl="card.imgUrl"
          v-bind:titleText="card.titleText"
          v-bind:subtitleText="card.subtitleText"
        />
      </div>
    </div>

    <div class="row">
      <div class="col s12 m12 l12 titleRow">
        Рекомендовано по профилю
      </div>
    </div>

    <div class="row">
      <div
        class="col s12 m12 l12 xl4"
        v-for="card in smallCards"
        v-bind:key="card"
      >
        <small-card
          v-bind:imgUrl="card.imgUrl"
          v-bind:titleText="card.titleText"
          v-bind:subtitleText="card.subtitleText"
        />
      </div>
    </div>

    <div class="row">
      <div class="col s12 m12 l12 titleRow">
        Подборки
      </div>
    </div>

    <div class="row">
      <div
        class="col s12 m12 l12 xl4"
        v-for="card in bigCards"
        v-bind:key="card"
      >
        <big-card
          v-bind:imgUrl="card.imgUrl" 
          v-bind:titleText="card.titleText" 
          v-bind:subtitleText="card.subtitleText" 
        />
      </div>
    </div>
  </div>
</template>

<script>
import SmallCard from "@/components/SmallCard.vue";
import BigCard from "@/components/BigCard.vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "Afisha",
  components: {
    SmallCard,
    BigCard,
  },
  data() {
    return {
      smallCards: [
        {
          imgUrl: `https://picsum.photos/1920/1080`,
          titleText: `Экскурсия в Новгородский кремль`,
          subtitleText: `8, Новгородский кремль, Великий Новгород`,
        },
        {
          imgUrl: `https://picsum.photos/1920/1080`,
          titleText: `Экскурсия в Софийский собор`,
          subtitleText: `11, Новгородский кремль, Великий Новгород, Россия`,
        },
        {
          imgUrl: `https://picsum.photos/1920/1080`,
          titleText: `День в мужском монастыре Свято-Юрьев`,
          subtitleText: `Юрьевское ш., 10, Великий Новгород`,
        },
      ],
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
  font-size: 1em;
  font-weight: bold;
}
</style>
