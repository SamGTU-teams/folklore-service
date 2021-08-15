<template>
  <div class="card">
    <div class="img-container">
      <img v-bind:src="imgUrl" />
    </div>
    <div class="card-content">
      <p>
        {{ textContent }}
      </p>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({
  name: "SmallCard",
  props: ["imgUrl", "textContent"],
});
</script>
<style>
.card-content {
  background-color: #fff;
  color: #51495c;
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
</style>
