<template>
    <div id="app">
        <GameMap/>
        <PlayerMission/>
        <PlayerActionConsole/>
        <PlayerCardStack/>
        <PlayerFiguresStack/>
    </div>
</template>

<script>
    import GameMap from './components/GameMap.vue'
    import PlayerMission from "./components/PlayerMission";
    import PlayerActionConsole from "./components/PlayerActionConsole";
    import PlayerCardStack from "./components/PlayerCardStack";
    import PlayerFiguresStack from "./components/PlayerFigureStack";

    export default {
        name: 'app',
        components: {
            PlayerFiguresStack,
            PlayerCardStack,
            PlayerActionConsole,
            PlayerMission,
            GameMap
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }

    #gameMap, #playerMission {
        position: absolute;
        width: 80%;
        left: 0;
    }

    #gameMap {
        top: 0;
        height: 95%;
        background-color: #2c3e50;
    }

    #playerMission {
        bottom: 0;
        height: 5%;
        background-color: brown;
    }

    #playerActionConsole, #playerFigureStack, #playerCardStack {
        position: absolute;
        left: 80%;
        width: 20%;
    }

    #playerActionConsole {
        top:0;
        height: 60%;
        background-color: beige;
    }

    #playerFigureStack {
        bottom:0;
        height: 10%;
        background-color: white;
    }

    #playerCardStack {
        top:60%;
        height: 30%;
        background-color: red;
    }
</style>
