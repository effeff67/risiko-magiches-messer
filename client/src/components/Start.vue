<template>
    <div id="Start">
        <form @submit.prevent="handleSubmit">
            <div>
                <input v-model="playerName" id="playerName"/><br/>
                <label for="playerName">Dein Spieler name</label>
            </div>
            <div>
                <select v-model="gameName" id="gameName">
                    <option disabled value="--">Wähle ein Spiel, um einzusteogen</option>
                    <option v-for="game in games" v-bind:value="game.name"> {{ game.name }}</option>
                </select><br/>
                <label for="gameName">Wähle ein Spiel</label>
            </div>
        </form>
        <p class="error">{{ failure }}</p>
    </div>
</template>

<script>
    export default {
        name: 'Start',
        data: function () {
            return {
                playerName: '',
                gameName: '',
                games: [],
                failure: '',
            }
        },
        methods: {
            handleSubmit: function(e) {
                this.failure = '';
                if(this.gameName && this.playerName.trim()) {
                    fetch('http://localhost:1301/mm-risiko/games/'+this.gameName+'/player',{
                        method:'POST',
                        headers:{'Content-type': 'application/json'},
                        body: JSON.stringify({player:this.playerName})
                    })
                } else {
                    this.failure = 'Bitte vollständig ausfüllen!';
                }

            }
        },
        created: function () {
            let vm = this;
            fetch('http://localhost:1301/mm-risiko/games').then(response => {
                response.json().then(json => {
                    console.log(JSON.stringify(json));
                    vm.games = json.games;
                });
            })
        }


    }
</script>

<style scoped>

</style>
