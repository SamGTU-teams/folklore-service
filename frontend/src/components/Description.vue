<template>
  <div class="row">
    <div class="Description" v-if="description" v-html="description" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "Description",
  props: {
    description: {
      type: String,
      required: true,
    },
  },
});
</script>

<style scoped>
.Description {
  padding: 0 0.75rem;
  text-indent: 20px;
  font-size: calc(25px + (20 - 25) * ((100vw - 500px) / (1920 - 500)));
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: #fff;
  text-align: justify;
}
</style>
