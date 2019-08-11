<template>
    <div id="playerActionConsole">
        <button id="Spielstarten">Spiel starten </button><br/>
    <button>attack</button><br/>
    <button>bewegen</button><br/>
    <button>Karten eintauschen</button><br/>
     <button>Truppen plazieren</button><br/>
        <input v-model="ausgangsland" id="ausgangsland"/><br/>
        <label for="ausgangsland">Wähle ein Ausgangsland!</label><br/>
        <input v-model="zielland" id="zielland"/><br/>
        <label for="zielland">Wähle ein Zielland!</label><br/>
        <input v-model="troopcount" id="troopcount"/><br/>
        <label for="troopcount">Bestimme die Truppenzahl!</label><br/>

    </div>
</template>

<script>
    export default {
        name: "PlayerActionConsole"
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


</style>
