<template>
    <div id="playerActionConsole">
        <div id="playersInfo">
            <span v-for="player in gameToPlay.players" :class="[player.color, activePlayer(player.color) ]">{{ player.name}}</span>
        </div>
        <div v-if="!gameToPlay.started">
            <div v-if="gameToPlay.players[0].color == mePlayer.color">
                <div v-if="gameToPlay.players.length > 2">
                    <button id="Spielstarten" @click="startGame">Spiel starten</button>
                    <br/>
                </div>
                <div v-else>
                    <p>Warte bis sich genügend Mitspieler eingefunden haben!</p>
                </div>
            </div>
            <div v-else>
                <p>Warte bis der Spielführer das Spiel gestartet hat!</p>
            </div>
        </div>
        <div v-else>
            <div v-if="gameToPlay.activePlayer == mePlayer.color">
                <button>attack</button>
                <br/>
                <button>bewegen</button>
                <br/>
                <button>Karten eintauschen</button>
                <br/>
                <button>Truppen plazieren</button>
                <br/>
                <input id="ausgangsland"/><br/>
                <label for="ausgangsland">Wähle ein Ausgangsland!</label><br/>
                <input id="zielland"/><br/>
                <label for="zielland">Wähle ein Zielland!</label><br/>
                <input id="troopcount"/><br/>
                <label for="troopcount">Bestimme die Truppenzahl!</label><br/>
            </div>
        </div>

    </div>
</template>

<script>
  import { mapState } from 'vuex'
  import { GameChangeRequest } from '@/shared/model/GameChangeRequest'

  export default {
    name: 'PlayerActionConsole',
    computed: mapState({
      gameToPlay: state => state.game,
      mePlayer: state => state.player,
    }),
    methods: {
      startGame: function () {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'startGame', {}))
      },
      activePlayer(color) {
        return this.gameToPlay.activePlayer === color ? 'active' : 'inactive'
      },
    },
  }
</script>

<style scoped>
    #playerActionConsole {
        font-family: Georgia;
        font-size: 10pt;
        color: black;
        text-align: center;
    }

    #Spielstarten {

    }

    #playersInfo span {
        margin: 4px;
        padding: 4px;
        display: grid;
        min-width: 35px;
        text-align: center;
        float: left;
    }
    .inactive {
        opacity: .5;
    }
    .rot {
        background: red;
    }

    .grün {
        background: green;
        color: white;
    }

    .violett {
        background: blueviolet;
        color: white;
    }

    .gelb {
        background: yellow;
        color: black;
    }

    .blau {
        background: blue;
        color: white;
    }

</style>
