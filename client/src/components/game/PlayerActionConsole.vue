<template>
    <div id="playerActionConsole">
        <div id="playersInfo">
            <span v-for="player in gameToPlay.players" :class="player.color">{{ player.name}}</span>
        </div>
        <button id="Spielstarten">Spiel starten </button><br/>
        <button>attack</button><br/>
        <button>bewegen</button><br/>
        <button>Karten eintauschen</button><br/>
        <button>Truppen plazieren</button><br/>
        <input id="ausgangsland"/><br/>
        <label for="ausgangsland">Wähle ein Ausgangsland!</label><br/>
        <input id="zielland"/><br/>
        <label for="zielland">Wähle ein Zielland!</label><br/>
        <input id="troopcount"/><br/>
        <label for="troopcount">Bestimme die Truppenzahl!</label><br/>
    </div>
</template>

<script>
  import { mapState } from 'vuex'

  export default {
    name: 'PlayerActionConsole',
    computed: mapState({
      gameToPlay: state => state.game,
      mePlayer: state => state.player,
    }),
    methods: {
      allPlayers: function () {
        return null
      }
    },
  }
</script>

<style scoped>
    #playerActionConsole{
        font-family: Georgia;
        font-size: 10pt;
        color: black;
        text-align: center;
    }
    #Spielstarten{

    }
    #playersInfo span {
        margin: 4px;
        padding: 4px;
        display: grid;
        width: 35px;
        text-align: center;
        float: left;
    }
    .rot {
        background: red;
    }
    .grün {
        background: green;
        color:white;
    }
    .violett {
        background: black;
        color:grey;
    }

</style>
