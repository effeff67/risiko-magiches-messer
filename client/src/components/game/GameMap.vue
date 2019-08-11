<template>
  <div id="gameMap" :style="{'background-image': 'url(' + require('@/assets/ClassicMap.png') + ')'}">
      <div id="alaska" class="regionTroops">00</div>
      <div id="alberta" class="regionTroops">00</div>
      <div id="ägypten" class="regionTroops">00</div>
      <div id="afghanistan" class="regionTroops">00</div>
      <div id="argentinien" class="regionTroops">00</div>
      <div id="brasilien" class="regionTroops">00</div>
      <div id="china" class="regionTroops">00</div>
      <div id="grönland" class="regionTroops">00</div>
      <div id="großbritannien" class="regionTroops">00</div>
      <div id="indien" class="regionTroops">00</div>
      <div id="indonesien" class="regionTroops">00</div>
      <div id="irkutsk" class="regionTroops">00</div>
      <div id="island" class="regionTroops">00</div>
      <div id="jakutien" class="regionTroops">00</div>
      <div id="japan" class="regionTroops">00</div>
      <div id="kamptschatka" class="regionTroops">00</div>
      <div id="madagaskar" class="regionTroops">00</div>
      <div id="mittelamerika" class="regionTroops">00</div>
      <div id="mongolei" class="regionTroops">00</div>
      <div id="naherosten" class="regionTroops">00</div>
      <div id="neuguinea" class="regionTroops">00</div>
      <div id="nordafrika" class="regionTroops">00</div>
      <div id="nordeuropa" class="regionTroops">00</div>
      <div id="nordwest-territorium" class="regionTroops">00</div>
      <div id="ontario" class="regionTroops">00</div>
      <div id="ostafrika" class="regionTroops">00</div>
      <div id="ostaustralien" class="regionTroops">00</div>
      <div id="ostkanada" class="regionTroops">00</div>
      <div id="oststaaten" class="regionTroops">00</div>
      <div id="peru" class="regionTroops">00</div>
      <div id="russland" class="regionTroops">00</div>
      <div id="sibirien" class="regionTroops">00</div>
      <div id="skandinavien" class="regionTroops">00</div>
      <div id="südafrika" class="regionTroops">00</div>
      <div id="südeuropa" class="regionTroops">00</div>
      <div id="südostasien" class="regionTroops">00</div>
      <div id="ural" class="regionTroops">00</div>
      <div id="venezuela" class="regionTroops">00</div>
      <div id="westaustralien" class="regionTroops">00</div>
      <div id="westeuropa" class="regionTroops">00</div>
      <div id="weststaaten" class="regionTroops">00</div>
      <div id="zentralafrika" class="regionTroops">00</div>

  </div>

</template>

<script>
    import { mapState } from 'vuex';

export default {
  name: 'GameMap',
  computed: mapState({
        game: state => state.game,
      }
  ),
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #gameMap
    {
        width: 80%;
        position: absolute;
        top: 0;
        height: 93%;
        background-repeat: no-repeat;
        background-size: 103%;
        background-position: center;
    }

    .regionTroops {
        position:absolute;
        border-radius: 18px;
        background-color: white;
        color:black;
        width: 36px;
        height:28px;
        padding-top: 8px;
    }
    #alaska{
        top: 80px;
        left: 70px;
    }

    #alberta{
        top: 160px;
        left: 180px;
    }

    #ägypten{
        bottom: 350px;
        left: 700px;
    }

    #afghanistan{
        top: 328px;
        right: 400px;
    }

    #argentinien{
        bottom: 90px;
        left: 330px;
    }

    #brasilien{
        bottom: 240px;
        left: 400px;
    }

    #china{
        top: 380px;
        right: 250px;
    }

    #grönland{
        top: 90px;
        left: 450px;
    }

    #großbritannien{
        top: 265px;
        left: 520px;
    }

    #indien{
        bottom: 350px;
        right: 325px;
    }

    #indonesien{
        bottom: 200px;
        right: 255px;
    }

    #irkutsk{
        top: 220px;
        right:235px;
    }

    #island{
        top: 130px;
        left: 550px;
    }

    #jakutien{
        top: 60px;
        right: 190px;
    }

    #japan{
        top: 240px;
        right: 65px;
    }

    #kamptschatka{
        top: 120px;
        right: 130px;
    }

    #madagaskar{
        bottom: 50px;
        right: 435px;
    }

    #mittelamerika{
        top: 400px;
        left: 255px;
    }

    #mongolei{
        top: 260px;
        right: 175px;
    }

    #naherosten{
        bottom: 390px;
        right: 485px;
    }

    #neuguinea{
        bottom: 250px;
        right: 130px;
    }

    #nordafrika{
        bottom: 325px;
        left: 600px
    }

    #nordeuropa{
        top: 300px;
        left: 640px;
    }

    #nordwest-territorium{
        top: 80px;
        left: 180px;
    }

    #ontario{
        top: 175px;
        left: 265px;
    }

    #ostafrika{
        bottom: 275px;
        left: 740px;
    }

    #ostaustralien{
        bottom: 50px;
        right: 45px;
    }

    #ostkanada{
        top: 220px;
        left: 355px;
    }

    #oststaaten{
        top: 296px;
        left: 335px;
    }

    #peru{
        bottom: 230px;
        left: 278px;
    }

    #russland{
        top: 265px;
        left: 765px;
    }

    #sibirien{
        top: 80px;
        right: 310px;
    }

    #skandinavien{
        top: 108px;
        right: 585px;
    }

    #südafrika{
        bottom: 50px;
        left: 700px

    }

    #südeuropa{
        top: 375px;
        left: 650px;
    }

    #südostasien{
        bottom:315px;
        right: 210px;
    }

    #ural{
        top: 150px;
        right: 385px;
    }

    #venezuela{
        bottom: 313.5px;
        left: 288px;
    }

    #westaustralien{
        bottom: 100px;
        right: 160px;
    }

    #westeuropa{
        top: 365px;
        left: 560px;;
    }

    #weststaaten{
        top: 250px;
        left: 205px;
    }

    #zentralafrika{
        bottom: 155px;
        left: 700px;
    }

</style>
