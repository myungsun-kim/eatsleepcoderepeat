<template>
  <div class="height100">
    <div class="height8">
      <SubNavBar />
    </div>
    <div class="height92">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import SubNavBar from '../components/SubNavBar.vue';

export default {
  components: {
    SubNavBar,
  },
};
</script>
