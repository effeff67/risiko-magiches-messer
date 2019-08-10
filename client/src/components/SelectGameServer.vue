<template>
    <form @submit.prevent="checkServerRoot">
        <h2>Select game server</h2>
        <input type="text" v-model="serverHost"/>
        <input type="submit" value="check server and continue"/>
    </form>
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
    methods: {
      checkServerRoot: function (e) {
        let vm = this;
        HttpClient.requestClient(this.serverHost, {
          method: 'GET'
        }).then( response => {
          if('OK' === response.message) {
           HttpClient.setServerHost(vm.serverHost);
           vm.$router.push({path: 'selectGame'})
          }
        })

      }
    }
  }
</script>

<style scoped>

</style>
