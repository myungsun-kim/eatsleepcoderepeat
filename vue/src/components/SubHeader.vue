<template>
  <SubNavBar />
  <router-view></router-view>
</template>

<script>
import SubNavBar from '../components/SubNavBar.vue';

export default {
  components: {
    SubNavBar,
  },
};
</script>
