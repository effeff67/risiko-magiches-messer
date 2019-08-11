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
        font-family: Georgia;
        color: black;
    }

</style>
