<template>
    <div id="playerActionConsole">
        <div id="playersInfo">
            <span v-for="player in gameToPlay.players" :class="[player.color, activePlayer(player.color) ]">{{ player.name}}</span>
        </div>
        <br style="clear: both"/>
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
                <div v-if="activeTurn === 'recruitTroops'">
                    <button @click="recruitTroops">Truppen Rekrutieren</button>
                    <br/>
                </div>
                <div v-if="activeTurn === 'placeRecruitedTroops'">
                    <input v-model="placeRecruitTroopsCount"/><br/>
                    <button @click="placeTroops">auf {{ lastClickedRegion.name }} Plazieren</button>
                    <br/><br/>
                    <button @click="gotToAttack">Truppen bewegen beenden!</button>
                </div>
                <div v-if="activeTurn === 'attackRegion'">
                    <input v-bind:value="previousClickedRegion.name" type="text" id="source"/><br/>
                    <label for="source">Von wo angreifen?</label><br/>
                    <input v-bind:value="lastClickedRegion.name" type="text" id="target"/><br/>
                    <label for="target">Wen angreifen?</label><br/>
                    <input v-model="attackingTroopCount" id="attackingTroopCount"/><br/>
                    <label for="attackingTroopCount">Mit wieviel Truppen?</label><br/>
                    <button
                        @click="attackRegion">Angreifen</button>
                    <br/>
                    <input v-model="moveTroopsAfterOccupation" id="moveTroopsAfterOccupation"/><br/>
                    <label for="moveTroopsAfterOccupation">Anzahl der neuen Besatzer</label><br/>
                    <button @click="moveTroopsIntoOccupiedCountry">Besetzen</button><br/>
                    <br/><button @click="function() { nextCommand('moveTroops') }">Weiter zu Truppen bewegen</button>
                </div>
                <div v-if="activeTurn === 'moveTroops'">
                    <input v-bind:value="previousClickedRegion.name" id="movingSource"/><br/>
                    <label for="movingSource">Von wo bewegen?</label><br/>
                    <input v-bind:value="lastClickedRegion.name" id="movingTarget"/><br/>
                    <label for="movingTarget">Wen angreifen?</label><br/>
                    <input v-model="movingTroopCount" id="movingTroopCount"/><br/>
                    <label for="movingTroopCount">Anzahl der zu bewegenden Truppen</label><br/>
                    <button @click="moveTroops">Bewegen</button>
                </div>
                <div v-if="activeTurn === 'finishTurn'">
                    <button @click="finishTurn">Zug beenden!</button>
                </div>

                <br/>
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
    data: function () {
      return {
        placeRecruitTroopsCount: 0,
        attackingTroopCount: 0,
        moveTroopsAfterOccupation: 0,
        movingTroopCount: 0,
        activeTurn: 'recruitTroops',
        turns: ['recruitTroops', 'attackRegion', 'moveTroops', 'finishTurn'],
      }
    },
    computed: mapState({
      gameToPlay: state => state.game,
      mePlayer: state => state.player,
      lastClickedRegion: state => state.lastClickedRegion,
      previousClickedRegion: function (state) {
        return state.previousClickedRegion ? state.previousClickedRegion : { name: '' }
      }
    }),
    methods: {
      startGame: function () {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'startGame', {}))
      },
      placeTroop: function () {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'placeTroop', { country: this.lastClickedRegion.name }))
      },
      placeTroops: function () {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'placeTroops',
          { country: this.lastClickedRegion.name, troopCount: this.placeRecruitTroopsCount }))
      },
      recruitTroops: function () {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'recruitTroops', {}));
        this.activeTurn = 'placeRecruitedTroops';
      },
      activePlayer (color) {
        return this.gameToPlay.activePlayer === color ? 'active' : 'inactive'
      },
      placeTroopCount () {
        return this.gameToPlay.players.filter((pl) => {
          if (this.mePlayer.color === pl.color) return pl
        })[0].inactiveTroops
      },
      gotToAttack(){
        this.activeTurn = 'attackRegion';
      },
      attackRegion () {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'attackRegion', {
          targetCountry: this.lastClickedRegion.name,
          sourceCountry: this.previousClickedRegion.name,
          troopCount: this.attackingTroopCount
        }))
      },
      moveTroopsIntoOccupiedCountry() {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'moveTroopsIntoOccupiedCountry', {
          countTroops: this.moveTroopsAfterOccupation
        }))
      },
      moveTroops() {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'moveTroops', {
          troopCount: this.movingTroopCount,
          targetCountry: this.lastClickedRegion.name,
          sourceCountry: this.previousClickedRegion.name,
        }));
        this.activeTurn = 'finishTurn'
      },
      finishTurn() {
        this.$store.dispatch('changeGame', new GameChangeRequest(this.mePlayer, 'finishTurn', {}))
      },
      nextCommand (what) {
        this.activeTurn = what
      }
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
