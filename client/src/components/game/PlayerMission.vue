<template>
    <div id="playerMission">
        {{ getPlayerMission() }}
    </div>
</template>

<script>
  import { mapState } from 'vuex'

  export default {
    name: 'PlayerMission',

    computed: mapState({
      gameToPlay: state => state.game,
      mePlayer: state => state.player,
    }),
    methods: {
      getPlayerMission () {
        return this.gameToPlay.players.filter((player) => {
          if (player.color === this.mePlayer.color) return player
        })[0].mission
      }
    },
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
