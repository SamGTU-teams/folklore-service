<template>
  <div class="container">
    <div class="row">
      <div class="col s12 m12 l12 titleRow">
        Экскурсии по Новгороду
      </div>
    </div>
    <div class="row">
      <div
        class="col s12 offset-m3 m6 l4"
        v-for="card in cards"
        v-bind:key="card"
      >
        <afisha-card
          v-bind:imgUrl="card.imgUrl"
          v-bind:titleText="card.titleText"
          v-bind:subtitleText="card.subtitleText"
        />
      </div>
    </div>
  </div>
</template>

<script>
import AfishaCard from "@/components/SmallCard.vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "Afisha",
  components: {
    AfishaCard,
  },
  data() {
    return {
      cards: [
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
    };
  },
});
</script>

<style>
.titleRow {
  text-align: left;
  font-size: 1em;
}
</style>
