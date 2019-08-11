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

    #playerMission {
        position: absolute;
        width: 80%;
        bottom: 0;
        height: 7%;
        background-color: #e06363;
    }


    #playerActionConsole, #playerFigureStack, #playerCardStack {
        position: absolute;
        left: 80%;
        width: 20%;
    }

    #playerActionConsole {
        top: 0;
        height: 60%;
        background-color: #c1e8c6;
    }

    #playerFigureStack {
        bottom: 0;
        height: 7%;
        background-color: #98d6a0;
    }

    #playerCardStack {
        top: 60%;
        height: 33%;
        background-color:#508a57;
    }
</style>
