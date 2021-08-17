<template>
  <div class="card z-depth-3">
    <div class="img-container">
      <img v-bind:src="imgUrl" />
    </div>
    <div class="decription">
      <h1 class="titleText flow-text">
        {{ titleText }}
      </h1>
      <p class="subtitleText flow-text">
        {{ subtitleText }}
      </p>
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
.decription {
  width: 100%;
  height: max(150px, min(160px, calc(100vw * 4 / 120)));
  position: relative;
  z-index: 2;
  bottom: 0;
  padding: 15px 15px;
  margin-left: 0px;
  background-color: #fff;
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
  z-index: 3;
  text-align: left;
  margin-bottom: 5px;
  margin-top: 0px;
  font-size: max(1.2em, min(2.3em, calc(100vw * 4 / 320)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  color: #2b3f8d;

  /* transform: translate(0%, -33%);
  position: relative;
  top: 33%; */
}
.subtitleText {
  width: 100%;
  z-index: 3;
  height: 2em;
  text-align: left;
  margin-bottom: 0px;
  margin-top: 0px;
  font-size: max(0.8em, min(1em, calc(100vw * 4 / 320)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: #0c0c0c;

  /* transform: translate(0%, -55%);
  position: relative;
  top: 55%; */
}
</style>
