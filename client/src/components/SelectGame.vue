<template>
    <div id="Start">
        <div v-if="selectedGame">
            {{ goToNext() }}
        </div>
        <form v-else @submit.prevent="handleSubmit">
            <div>
                <img alt="Logo" src="@/assets/RisikoLogo.png" id="Logo"/>
                <br>
            </div>
            <div>
                <br/>
                <br/>
                <input v-model="playerName" id="playerName"/><br/>
                <label for="playerName">Dein Spielername</label>
                <br>
            </div>
            <div v-if="selectableGames.length > 0">
                <select v-model="selectedGameName" id="gameName">
                    <option disabled value="--">Wähle ein Spiel, um einzusteigen</option>
                    <option v-for="game in selectableGames" v-bind:value="game.name"> {{ game.name }}</option>
                </select><br/>
                <label for="gameName">Wähle ein Spiel</label>
            </div>

            <div>
                <p>Neues Spiel?</p>
                <select v-model="newGameMapName">
                    <option disabled value="--">Wähle eine Map zum Spielen!</option>
                    <option v-for="map in newGameMaps" v-bind:value="map.name"> {{ map.name }}</option>
                </select><br/><br>
                <input v-model="newGameName" id="newGameName"/><br/><br>
                <label for="newGameName">Spielname (ohne Leerzeichen)</label><br/><br>
                <input type="checkbox" v-model="newGameConquerTheWorld" value="true" id="conquerTheWorld"/><br/>
                <label for="conquerTheWorld">Global Mission Welteroberung für alle!</label>
            </div>
            <div v-if="newGameMapName">
                <select v-model="playerColor" id="playerColor">
                    <option disabled value="--">Wähle eine Farbe zum Spielen!</option>
                    <option v-for="color in newGameColors()" v-bind:value="color">{{ color }}</option>
                </select>
            </div>
            <div v-if="playerColor && playerName && (newGameName || selectedGameName)">
                <input type="submit" value="Abschicken"/>
            </div>

        </form>
        <img alt="Schlacht Links" src="@/assets/Schlacht Links.png" id="Schlachtlinks"/>
        <img alt="Schlacht Rechts" src="@/assets/Schlacht Rechts.png" id="Schlachtrechts"/>
    </div>
</template>

<script>
  import { GameChangeRequest } from '@/shared/model/GameChangeRequest'
  import { Player } from '@/shared/model/Player'
  import { mapState } from 'vuex'

  export default {
    name: 'SelectGame',
    data: function () {
      return {
        playerName: '',
        playerColor: '',
        selectedGameName: '',
        selectableGames: [],
        failure: '',
        newGameName: '',
        newGameConquerTheWorld: false,
        newGameMaps: [],
        newGameMapName: '',
      }
    },
    computed: mapState({
      riskServerRoot: state => state.riskServerRoot,
      selectedGame: state => state.game,
    }),
    methods: {
      goToNext: function () {
        console.log('called go to playGame');
        this.$router.push({ path: 'playGame' })
      },
      newGameColors () {
        return this.newGameMaps.filter((map) => {
          if (map.name === this.newGameMapName) return map
        })[0].playerColors
      },
      handleSubmit: function (e) {
        if(this.playerName && this.playerColor) {
          let player = new Player(this.playerName, this.playerColor);
          if (this.newGameName) {
            let gameChangeRequest = new GameChangeRequest(
              new Player(this.playerName, this.playerColor),
              'addGame', { conquerTheWorld: this.newGameConquerTheWorld }
            )
            this.$store.dispatch('addGame', {gameName: this.newGameName, request: gameChangeRequest});
          } else if (this.selectedGameName) {
            let gameChangeRequest = new GameChangeRequest(player, 'addPlayer')
            this.$store.dispatch('addPlayer', {gameName: this.selectedGameName, request: gameChangeRequest});
          }
        } else {
          this.failure = 'Bitte vollständig ausfüllen!'
        }
      }
    },
    created: function () {
      let vm = this
      fetch('http://' + this.$store.state.riskServerRoot + '/games').then(response => {
        response.json().then(json => {
          vm.selectableGames = json.games
        })
      })
      fetch('http://' + this.$store.state.riskServerRoot + '/maps').then(response => {
        response.json().then(json => {
          vm.newGameMaps = json
        })
      })
    }
  }
</script>

<style scoped>

#Start {
    margin-top: 5%;
    background-color: #508a57;
    width: 100%;
    height: 100%;
    font-family: Georgia;
    font-size: 15pt;
    color: black;
    text-align: center;
}

#Logo {
    width: 20%;
    height: auto;
}

#Schlachtlinks {
    width: 35%;
    height: auto;
    position: absolute;
    bottom: 0;
    left: 0;


}

#Schlachtrechts {
    width: 35%;
    height: auto;
    position: absolute;
    bottom: 0;
    right: 0;
}

</style>
