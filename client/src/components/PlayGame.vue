<template>
    <div id="playGame">
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

  export default {
    name: 'PlayGame',
    components: {
      PlayerFiguresStack,
      PlayerCardStack,
      PlayerActionConsole,
      PlayerMission,
      GameMap,
    },
    data: function () {
      return {
        available: false,
        started: false,
      }
    },
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
        background-color: #c1e8c6;
    }

    #playerFigureStack {
        top: 712px;
        height: 109px;
        left: 1152px;
        width: 320px;
        background-color: #98d6a0;
    }

    #playerCardStack {
        top: 500px;
        height: 212px;
        width: 320px;
        background-color:#508a57;
    }
</style>
