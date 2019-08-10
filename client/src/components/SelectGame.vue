<template>
    <div id="Start">
        <form @submit.prevent="handleSubmit">
            <div>
                <img alt="Logo" src="@/assets/Reiter.png" id="Logo"/>
            </div>
            <div>
                <input v-model="playerName" id="playerName"/><br/>
                <label for="playerName">Dein Spielername</label>
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
        <p class="error">{{ failure }}</p>
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
      serverHost: state => state.serverHost,
    }),
    methods: {
      newGameColors () {
        return this.newGameMaps.filter((map) => {
          if (map.name === this.newGameMapName) return map
        })[0].playerColors
      },
      handleSubmit: function (e) {
        let vm = this
        this.failure = ''
        if (this.newGameName && this.playerName && this.playerColor) {
          let requestBody = JSON.stringify(new GameChangeRequest(
            new Player(this.playerName, this.playerColor),
            'addGame',
            { conquerTheWorld: this.newGameConquerTheWorld }
          ))
          console.log(requestBody)
          fetch('http://localhost:1301/mm-risiko/games/' + this.newGameName, {
            method: 'PUT',
            headers: { 'Content-type': 'application/json' },
            body: requestBody
          }).then(response => {
            response.json().then(json => {
              console.log(JSON.stringify(json))
            })
          })
        } else if (this.selectedGameName && this.playerName.trim()) {
          let requestBody = JSON.stringify(new GameChangeRequest(new Player(vm.playerName, vm.playerColor), 'addPlayer'))
          console.log(requestBody)
          /*
          fetch('http://localhost:1301/mm-risiko/games/' + this.selectedGameName + '/player', {
            method: 'POST',
            headers: { 'Content-type': 'application/json' },
            body: requestBody
          })
          */
        } else {
          this.failure = 'Bitte vollständig ausfüllen!'
        }
      }
    },
    created: function () {
      let vm = this
      fetch('http://localhost:1301/mm-risiko/games').then(response => {
        response.json().then(json => {
          console.log(JSON.stringify(json))
          vm.selectableGames = json.games
        })
      })
      fetch('http://localhost:1301/mm-risiko/maps').then(response => {
        response.json().then(json => {
          console.log(JSON.stringify(json))
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

#playerName{
    font-family: Georgia;
    color: gray;
    }

</style>
