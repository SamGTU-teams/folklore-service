<template>
  <div class="switch TopMapFilterSwitcher">
        <label class="swithText">
          {{ firstValue }}
          <input type="checkbox" v-model="status" @change="$emit('changed-value', status)"/>
          <span class="lever"></span>
          {{ secondValue }}
        </label>
      </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    props: {
        firstValue: {
            type: String,
            required: true,
        },
        secondValue: {
            type: String,
            required: true,
        }
    },
    data() {
        return {
            status: false,
        }
    }
})
</script>

<style scoped>
.TopMapFilterSwitcher {
  float: right;
  margin-right: 30px;
  bottom: 15px;
}

.swithText {
  color: #00028b !important;
}

.switch label input[type="checkbox"]:checked + .lever:before,
.swithText {
  font-weight: bold;
}

.switch label input[type="checkbox"]:checked + .lever:before,
.swithText {
  font-weight: normal;
}

.switch label {
  color: #fefbf7;
  font-size: 18px;
}

.switch label input[type="checkbox"]:checked + .lever {
  background-color: #ec79fc !important;
}

.switch label input[type="checkbox"]:checked + .lever:after {
  background-color: #e300ff !important;
}

[type="checkbox"]:not(.filled-in) + span:not(.lever):before,
.collapsible-body p:hover span:before {
  border-right: 2px solid #ffbd00 !important;
  border-bottom: 2px solid #ffbd00 !important;
  border-left: 2px solid #ffbd00 !important;
  border-top: 2px solid #ffbd00 !important;
}

[type="checkbox"]:not(.filled-in) + span:not(.lever):before {
  border-right: 2px solid #fcd56b !important;
  border-bottom: 2px solid #fcd56b !important;
  border-left: 2px solid #fcd56b !important;
  border-top: 2px solid #fcd56b !important;
}

[type="checkbox"]:checked + span:not(.lever):before {
  border-right: 2px solid #e300ff !important;
  border-bottom: 2px solid #e300ff !important;
  border-left: 2px solid #ffbb0000 !important;
  border-top: 2px solid #ffbb0000 !important;
}
</style>