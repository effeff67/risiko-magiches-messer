<template>
    <div id="playerMission">
        {{mission}}
    </div>
</template>

<script>
export default {
    name: "PlayerMission",
    data: function(){
      return {
        mission: 'not set',
      }
    }
}

</script>

<style scoped>
    #playerMission {
        font: bold;
        font-family: Georgia;
        color: black;
        background-position: center ;
    }

</style>
