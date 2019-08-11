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
                <div v-else id="WarteaufMitspieler">
                    <p>Warte bis sich genügend Mitspieler eingefunden haben!</p>
                </div>
            </div>
            <div v-else id="WarteaufSpielstart">
                <p>Warte bis der Spielführer das Spiel gestartet hat!</p>
            </div>
        </div>
        <div v-if="gameToPlay.activePlayer == mePlayer.color">
            <div v-if="gameToPlay.ready" id="actionButtons">
                <button @click="recruitTroops">Rekrutieren</button>
                <button>Angreifen</button>
                <br/>
                <button>Bewegen</button>
                <br/>
                <button id="Truppenplatzieren">Truppen plazieren</button>
                <br/>
                <input id="ausgangsland"/><br/>
                <label id="Lableausgangsland" for="Ausgangsland">Wähle ein Ausgangsland!</label><br/>
                <input id="Zielland"/><br/>
                <label id="Lablezielland" for="Zielland">Wähle ein Zielland!</label><br/>
                <input id="Troopcount"/><br/>
                <label id="Labletroopcount" for="Troopcount">Bestimme die Truppenzahl!</label><br/>
            </div>
            <div v-else>
                <div v-if="lastClickedRegion">
                    <button @click="placeTroop">1 auf {{ lastClickedRegion.name }} Plazieren</button>
                </div>
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
      lastClickedRegion: state => state.lastClickedRegion
    }),
    methods: {
      startGame: function () {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'startGame', {}))
      },
      placeTroop: function() {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'placeTroop', {country: this.lastClickedRegion.name}))
      },
      recruitTroops: function () {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'recruitTroops', {}))
      },
      activePlayer (color) {
        return this.gameToPlay.activePlayer === color ? 'active' : 'inactive'
      },
    },
  }
</script>

<style scoped>
    #playersInfo {
        top: 10px;
        left: 10px;
        height: 35px;
    }

    #actionButtons {
        position: absolute;
    }

    #playerActionConsole {
        font-family: Georgia;
        font-size: 10pt;
        color: black;
        text-align: center;
    }

    #Spielstarten {
        position: absolute;
        top: 100px;
        left: 30px;
    }

    #Angreifen {
        position: absolute;
        top: 125px;
        left: 30px;
    }

    #Bewegen {
        position: absolute;
        top: 150px;
        left: 30px;
    }

    #Truppenplatzieren {
        position: absolute;
        top: 175px;
        left: 30px;
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

    #WarteaufMitspieler {
        position: absolute;
        top: 60px;
    }

    #WarteaufSpielstart {
        position: absolute;
        top: 60px;
    }

    #ausgangsland {
        position: absolute;
        top: 200px;
        left: 30px;

    }

    #Lableausgangsland {
        position: absolute;
        top: 225px;
        left: 30px;
    }

    #Zielland {
        position: absolute;
        top: 245px;
        left: 30px;

    }

    #Lablezielland {
        position: absolute;
        top: 270px;
        left: 30px;
    }

    #Troopcount {
        position: absolute;
        top: 290px;
        left: 30px;

    }

    #Labletroopcount {
        position: absolute;
        top: 315px;
        left: 30px;
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
