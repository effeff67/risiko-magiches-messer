<template>
    <div id="playerFigureStack">
        <div id="activatedFigures">{{ countActivatedFigures }}</div><br/>
        <label id="LableactiveFigures">verfügbare Truppen</label>
    </div>
</template>

<script>
    export default {
        name: "PlayerFiguresStack",
        data: function() {
            return {
                countActivatedFigures: 0,
            }
        }
    }
</script>

<style scoped>
    #activatedFigures {
        display: block;
        margin: 2%;
        padding: 4%;
        border-radius: 10px;
        border: 1px solid black;
        background-color: white;
        color: black;
        position: absolute;
        left: 125px;
        top: 10px;
    }

    #LableactiveFigures{
        font-family: Georgia;
        position: absolute;
        font-size: 10pt;
        color: black;
        left: 90px;
        top: 75px;

    }
</style>
