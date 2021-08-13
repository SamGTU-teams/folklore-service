<template>
  <div class="activivty">Activity {{ id }}</div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: "ActivityInfo",
  props: ["id"],
})
</script>
