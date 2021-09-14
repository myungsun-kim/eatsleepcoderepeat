<template>
  <TopNavBar />
  <router-view></router-view>
</template>
<script>
import TopNavBar from '../components/TopNavBar.vue';

export default {
  components: {
    TopNavBar,
  },
};
</script>
