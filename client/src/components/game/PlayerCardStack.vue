<template>
        <div id="playerCardStack">
            <button id="Karteneintauschen">Karten eintauschen</button>
            <br/>
            <div id="Infanterie">{{ countInfanterie }}</div>
            <br/>
            <div id="Artillerie">{{ countArtillerie }}</div>
            <br/>
            <div id="Kavallerie">{{ countKavallerie }}</div>
            <br/>
            <div id="Joker">{{ countJoker }}</div>
            <br/>
            <img alt="Infanterie" src="@/assets/Soldat.png" id="InfanterieBild"/>
            <img alt="Artillerie" src="@/assets/artillerie.png" id="ArtillerieBild"/>
            <img alt="Kavallerie" src="@/assets/Reiter.png" id="KavallerieBild"/>
            <img alt="Joker" src="@/assets/Joker.png" id="JokerBild"/>
        </div>
</template>

<script>
    export default {
        name: "PlayerCardStack",
        data: function () {
            return {
                countInfanterie: 0,
                countArtillerie: 0,
                countKavallerie: 0,
                countJoker: 0,
            }
        }
    }
</script>

<style scoped>

    #Karteneintauschen {
        position: absolute;
        left: 100px;
        top: 20px;

    }

    #Infanterie{
        position: absolute;
        display: block;
        margin: 2%;
        padding: 4%;
        border-radius: 10px;
        border: 1px solid black;
        background-color: white;
        color: black;
        bottom: 10px;
        left: 30px;

    }

    #Artillerie{
        position: absolute;
        display: block;
        margin: 2%;
        padding: 4%;
        border-radius: 10px;
        border: 1px solid black;
        background-color: white;
        color: black;
        bottom: 10px;
        left: 150px;
    }

    #Kavallerie{
        position: absolute;
        display: block;
        margin: 2%;
        padding: 4%;
        border-radius: 10px;
        border: 1px solid black;
        background-color: white;
        color: black;
        bottom: 10px;
        left: 90px;
    }

    #Joker{
        position: absolute;
        display: block;
        margin: 2%;
        padding: 4%;
        border-radius: 10px;
        border: 1px solid black;
        background-color: white;
        color: black;
        bottom: 10px;
        left: 220px;
    }

    #ArtillerieBild {
        width: 20%;
        position: absolute;
        bottom: 80px;
        left: 145px;
    }

    #KavallerieBild{
        width: 25%;
        position: absolute;
        bottom: 80px;
        left: 85px;
    }

    #InfanterieBild{
        width: 10%;
        position: absolute;
        bottom: 80px;
        left: 40px;
    }

    #JokerBild{
        width: 20%;
        position: absolute;
        bottom: 80px;
        left: 210px;
    }


</style>
