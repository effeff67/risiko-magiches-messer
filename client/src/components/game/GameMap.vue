<template>
  <div id="gameMap" :style="{'background-image': 'url(' + require('@/assets/ClassicMap.png') + ')'}">
  </div>

</template>

<script>
export default {
  name: 'GameMap',
  data: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #gameMap
    {
        width: 80%;
        position: absolute;
        top: 0;
        height: 95%;
        background-repeat: no-repeat;
        background-size: 100%;
        background-position: center;
    }
</style>
