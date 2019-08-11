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
        position: absolute;
        top: 20px;
        font-family: Georgia;
        color: black;
    }

</style>
