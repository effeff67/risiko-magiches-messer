<template>
    <div id="app">
        <h1 v-if="game">Game</h1>
        <Start v-else/>
    </div>
</template>

<script>
    import Start from './components/Start';
    import Game from './components/Game';
    import { mapState } from 'vuex';


    export default {
        name: 'app',
        components: {
            Start,
            Game,
        },
        computed: mapState({
            game: state => state.game,
        }),
    }
</script>

<style>
    #app {
        position: absolute;
        width: 100%;
        height:100%;
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        background-color: darkgrey;
    }
</style>
