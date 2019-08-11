<template>
  <div id="gameMap" :style="{'background-image': 'url(' + require('@/assets/ClassicMap.png') + ')'}">
      <div v-for="region in allRegions()" :id="region.name" :class="['regionTroops', region.holder ]">{{ region.troops }}</div>
      <!-- <div id="alaska" class="regionTroops">00</div>
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
      <div id="zentralafrika" class="regionTroops">00</div> -->

  </div>

</template>

<script>
    import { mapState } from 'vuex';

export default {
  name: 'GameMap',
  computed: mapState({
        gameToPlay: state => state.game,
      }
  ),
  methods: {
    allRegions() {
      let regions = [];
      let continentList = this.gameToPlay.gameMap.continentList;
      for(let i = 0; i < continentList.length; i++) {
        let countries = continentList[i].countries;
        for(let j = 0; j < countries.length; j++) {
          let country = countries[j];
          regions.push({name: country.region.toLowerCase().replace(' ',''), troops: country.troopCount, holder: country.holder });
        }
      }
      return regions;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #gameMap
    {
        width: 1152px;
        position: absolute;
        top: 0;
        height: 712px;
        background-repeat: no-repeat;
        background-size: 1152px;
        background-position: center;
        z-index: 100;
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
    .regionTroops.violett {
        background: blueviolet;
        color:white;
    }
    .regionTroops.rot{
        background: #e06363;
        color: black;
    }
    .regionTroops.grün{
        background: green;
        color: white;
    }
    .regionTroops.gelb{
        background: yellow;
        color: black;
    }
    .regionTroops.blau{
        background: blue;
        color: white;
    }
    #alaska{
        top: 60px;
        left: 75px;
    }

    #alberta{
        top: 130px;
        left: 180px;
    }

    #ägypten{
        bottom: 300px;
        left: 600px;
    }

    #afghanistan{
        top: 280px;
        right: 355px;
    }

    #argentinien{
        bottom: 80px;
        left: 300px;
    }

    #brasilien{
        bottom: 210px;
        left: 380px;
    }

    #china{
        top: 315px;
        right: 230px;
    }

    #grönland{
        top: 80px;
        left: 390px;
    }

    #großbritannien{
        top: 215px;
        left: 449px;
    }

    #indien{
        bottom: 280px;
        right: 290px;
    }

    #indonesien{
        bottom: 170px;
        right: 225px;
    }

    #irkutsk{
        top: 180px;
        right:215px;
    }

    #island{
        top: 105px;
        left: 480px;
    }

    #jakutien{
        top: 40px;
        right: 180px;
    }

    #japan{
        top: 240px;
        right: 50px;
    }

    #kamtschatka{
        top: 100px;
        right: 110px;
    }

    #madagaskar{
        bottom: 50px;
        right: 380px;
    }

    #mittelamerika{
        top: 340px;
        left: 230px;
    }

    #mongolei{
        top: 220px;
        right: 165px;
    }

    #naherosten{
        bottom: 320px;
        right: 430px;
    }

    #neuguinea{
        bottom: 210px;
        right: 125px;
    }

    #nordafrika{
        bottom: 270px;
        left: 520px
    }

    #nordeuropa{
        top: 250px;
        left: 560px;
    }

    #nordwest-territorium{
        top: 60px;
        left: 180px;
    }

    #ontario{
        top: 146px;
        left: 265px;
    }

    #ostafrika{
        bottom: 180px;
        left: 680px;
    }

    #ostaustralien{
        bottom: 50px;
        right: 45px;
    }

    #ostkanada{
        top: 183px;
        left: 340px;
    }

    #oststaaten{
        top: 240px;
        left: 290px;
    }

    #peru{
        bottom: 182px;
        left: 254px;
    }

    #russland{
        top: 225px;
        left: 665px;
    }

    #sibirien{
        top: 70px;
        right: 280px;
    }

    #skandinavien{
        top: 85px;
        right: 515px;
    }

    #südafrika{
        bottom: 35px;
        left: 615px
    }

    #südeuropa{
        top: 315px;
        left: 570px;
    }

    #südostasien{
        bottom:255px;
        right: 195px;
    }

    #ural{
        top: 130px;
        right: 340px;
    }

    #venezuela{
        bottom: 313.5px;
        left: 288px;
    }

    #westaustralien{
        bottom: 80px;
        right: 150px;
    }

    #westeuropa{
        top: 300px;
        left: 500px;;
    }

    #weststaaten{
        top: 208px;
        left: 200px;
    }

    #zentralafrika{
        bottom: 178px;
        left: 610px;
    }

</style>
