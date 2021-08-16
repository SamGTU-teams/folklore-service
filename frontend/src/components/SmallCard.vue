<template>
  <div class="card z-depth-3">
    <div class="img-container">
      <img v-bind:src="imgUrl" />
    </div>
    <div class="card-content">
      <p class="titleText flow-text">
        {{ titleText }}
      </p>
      <p class="subtitleText flow-text">
        {{ subtitleText }}
      </p>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({
  name: "SmallCard",
  props: ["imgUrl", "titleText", "subtitleText"],
});
</script>

<style scoped>
.card-content {
  background-color: #fff;
  color: #000;
}

.img-container {
  position: relative;
  padding-bottom: 56.25%; /* задаёт высоту контейнера для 16:9 (если 4:3 — поставьте 75%) */
  height: 0;
  overflow: hidden;
}
.img-container img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-width: 0;
  outline-width: 0;
}

.titleText {
  /* padding-bottom: 5px; */
  height: 2em;
  text-align: left;
  font-size: max(0.65em, min(0.8em, calc(100vw * 4 / 320)));
  font-weight: bold;
  color: #2b3f8d;
}
.subtitleText {
  padding-top: 10px;
  height: 2em;
  text-align: left;
  font-size: max(0.5em, min(0.6em, calc(100vw * 4 / 320)));
}
</style>
