<template>
    <div id="playerFigureStack">
        <div id="activatedFigures">{{ countActivatedFigures }}</div>

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
        border-radius: 20%;
        border: 1px solid black;
        background-color: white;
        color: black;
        position: absolute;
        left:40%;
    }
</style>
