<template>
    <div id="playerFigureStack">
        <span id="activatedFigures">{{ countActivatedFigures }}</span>
        <span id="inactiveFigures">{{ countInactiveFigures }}</span>
    </div>
</template>

<script>
    export default {
        name: "PlayerFiguresStack",
        data: function() {
            return {
                countActivatedFigures: 0,
                countInactiveFigures: 35,
            }
        }
    }
</script>

<style scoped>
    #activatedFigures, #inactiveFigures {
        display: block;
        float:left;
        margin: 2%;
        height: 2%;
        padding: 4%;
        border-radius: 20%;
        border: 1px solid black;
        background-color: white;
    }

    #activatedFigures {
        color: black;
    }

    #inactiveFigures {
        color:lightgrey;
    }
</style>
