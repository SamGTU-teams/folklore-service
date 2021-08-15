<template>
  <div class="container">
    <div class="row">
      <div
        class="col s12 offset-m3 m6 l4"
        v-for="card in cards"
        v-bind:key="card"
      >
        <afisha-card
          v-bind:imgUrl="card.imgUrl"
          v-bind:textContent="card.textContent"
        />
      </div>
    </div>
  </div>
</template>
<script>
import AfishaCard from "@/components/SmallCard.vue";
import { defineComponent } from "vue";
import M from "materialize-css";
export default defineComponent({
  name: "Afisha",
  mounted() {
    M.AutoInit();
  },
  components: {
    AfishaCard,
  },
  data() {
    return {
      cards: [
        {
          imgUrl: `https://picsum.photos/1920/1080`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/3840/2160`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/1024/720`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/3840/2160`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/1024/720`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/3840/2160`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/1024/720`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/3840/2160`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/1024/720`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/3840/2160`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/1024/720`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
        {
          imgUrl: `https://picsum.photos/3840/2160`,
          textContent: `I am a very simple card. I am good at containing small bits of
              information. I am convenient because I require little markup to
              use effectively.`,
        },
      ],
    };
  },
});
</script>
<style>
.container {
  padding-top: 20px;
}
</style>
