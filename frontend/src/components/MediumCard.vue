<template>
  <div class="card z-depth-3">
    <div class="img-container">
      <img v-if="imgUrl" v-bind:src="imgUrl" />
      <img v-else src="@/assets/no-image.png" />
      <div class="category">
        <h1 class="titleText flow-text">
          {{ titleText }}
        </h1>
        <p class="subtitleText flow-text">
          {{ subtitleText }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({
  name: "BigCard",
  props: ["imgUrl", "titleText", "subtitleText"],
});
</script>

<style scoped>
.category {
  width: 100%;
  position: absolute;
  bottom: 0;
  padding: 15px 30px;
}

.img-container {
  position: relative;
  padding-bottom: 56.25%; /* задаёт высоту контейнера для 16:9 (если 4:3 — поставьте 75%) */
  height: 0;
  overflow: hidden;
  z-index: 1;
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
  width: 100%;
  z-index: 2;
  text-align: left;
  margin-bottom: 5px;
  margin-top: 0px;
  font-size: max(1.25em, min(2.5em, calc(100vw * 4 / 120)));
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  color: #fff;
}
.subtitleText {
  width: 100%;
  z-index: 2;
  height: 2em;
  text-align: left;
  margin-bottom: 0px;
  margin-top: 0px;
  font-size: max(0.9em, min(2em, calc(100vw * 4 / 320)));
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #fff;
}
</style>
