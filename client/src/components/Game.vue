<template>
    <template v-if="available">
        <GameMap/>
        <PlayerMission/>
    </template>
    <template v-if="started">
        <PlayerActionConsole/>
        <PlayerCardStack/>
        <PlayerFiguresStack/>
    </template>
</template>

<script>
    import GameMap from "./game/GameMap";
    import PlayerMission from "./game/PlayerMission";
    import PlayerActionConsole from "./game/PlayerActionConsole";
    import PlayerCardStack from "./game/PlayerCardStack";
    import PlayerFiguresStack from "./game/PlayerFigureStack";

    export default {
        name: "Game",
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
        top: 0;
        height: 60%;
        background-color: beige;
    }

    #playerFigureStack {
        bottom: 0;
        height: 10%;
        background-color: white;
    }

    #playerCardStack {
        top: 60%;
        height: 30%;
        background-color: red;
    }
</style>
