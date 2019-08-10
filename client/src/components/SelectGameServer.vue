<template>
    <div id="selectGameServer ">
        <img alt="Logo" src="@/assets/Reiter.png" id="Logo"/>
        <div v-if="storedServerHost">
            {{ goToNext() }}
        </div>
        <form v-else @submit.prevent="checkServerRoot">
            <h2>Select game server</h2>
            <input type="text" v-model="serverHost"/>
            <input type="submit" value="check server and continue"/>
        </form>
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
        serverHost: ''
      }
    },
    computed: mapState({
      storedServerHost: state => state.serverHost,
    }),
    methods: {
      goToNext: function () {
        console.log('called go to next');
        this.$router.push({ path: 'selectGame' })
      },
      checkServerRoot: function (e) {
        let vm = this
        HttpClient.requestClient(this.serverHost, {
          method: 'GET'
        }).then(response => {
          if ('OK' === response.message) {
            HttpClient.setServerHost(vm.serverHost)
            vm.$store.state.serverHost = vm.serverHost
            vm.goToNext()
          }
        })
      }
    }
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
        width: 20%;
        height: auto;
    }
</style>
