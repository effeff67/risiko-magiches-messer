<template>
    <div v-if="gameToPlay" id="playGame">
        <GameMap/>
        <PlayerMission/>
        <PlayerActionConsole/>
        <PlayerCardStack/>
        <PlayerFiguresStack/>
    </div>
</template>

<script>
  import GameMap from '@/components/game/GameMap'
  import PlayerMission from '@/components/game/PlayerMission'
  import PlayerActionConsole from '@/components/game/PlayerActionConsole'
  import PlayerCardStack from '@/components/game/PlayerCardStack'
  import PlayerFiguresStack from '@/components/game/PlayerFigureStack'
  import { mapState } from 'vuex'

  export default {
    name: 'PlayGame',
    components: {
      PlayerFiguresStack,
      PlayerCardStack,
      PlayerActionConsole,
      PlayerMission,
      GameMap,
    },
    computed: mapState({
      gameToPlay: function(state){
        if(state.game) {
          let vm = this;
          setTimeout(function () {
            vm.$store.dispatch('reloadGame')
          }, 1000);
          return state.game
        } else {
          this.$router.back()
        }
      }
    }),
  }
</script>

<style scoped>




    #playerActionConsole, #playerFigureStack, #playerCardStack, #playerMission {
        position: absolute;
    }

    #playerMission {
        width: 1152px;
        top: 712px;
        height: 109px;
        background-color: #e06363;
    }

    #playerActionConsole {
        top: 0;
        left: 1152px;
        height: 500px;
        width: 320px;
        background-color: #84ad89;
    }

    #playerFigureStack {
        top: 712px;
        height: 109px;
        left: 1152px;
        width: 320px;
        background-color: #84ad89;
    }

    #playerCardStack {
        top: 500px;
        height: 212px;
        width: 320px;
        left: 1152px;
        background-color:#508a57;
    }
</style>
