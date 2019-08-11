<template>
    <div id="selectGameServer ">
        <img alt="Logo" src="@/assets/RisikoLogo.png" id="Logo"/>
        <div v-if="storedServerHost">
            {{ goToNext() }}
        </div>
        <form v-else @submit.prevent="checkServerRoot">
            <h2>Select game server</h2>
            <input type="text" v-model="serverHostNameOrIP"/>
            <input type="submit" value="check server and continue"/>
        </form>
    <img alt="Schlacht Links" src="@/assets/Schlacht Links.png" id="Schlachtlinks"/>
    <img alt="Schlacht Rechts" src="@/assets/Schlacht Rechts.png" id="Schlachtrechts"/>
</div>
</template>

<script>
  import { mapState } from 'vuex'
  import HttpClient from '@/shared/service/HttpClient.js'

  export default {
    name: 'SelectGameServer',
    service: {},
    data: function () {
      return {
        serverHostNameOrIP: ''
      }
    },
    computed: mapState({
      storedServerHost: state => state.riskServerRoot,
    }),
    methods: {
      goToNext: function () {
        console.log('called go to next');
        this.$router.push({ path: 'selectGame' })
      },
      checkServerRoot: function (e) {
        this.$store.dispatch('checkServerRoot', this.serverHostNameOrIP);
      },
    },
  }
</script>

<style scoped>
    #selectGameServer
    {
        margin-top: 5%;
        background-color: #508a57;
        width: 100%;
        height: 100%;
        color: black;
        text-align: center;
    }
    #Logo {
        margin-top: 10%;
        width: 30%;
        height: auto;
    }

    #Schlachtlinks {
        width: 35%;
        height: auto;
        position: absolute;
        bottom: 0;
        left: 0;


    }

    #Schlachtrechts {
        width: 35%;
        height: auto;
        position: absolute;
        bottom: 0;
        right: 0;
    }
</style>
