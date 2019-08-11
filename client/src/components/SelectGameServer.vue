<template>
    <div id="selectGameServer ">
        <div v-if="storedServerHost">
            {{ goToNext() }}
        </div>
        <form v-else @submit.prevent="checkServerRoot">
            <h2>Select game server</h2>
            <input type="text" v-model="serverHostNameOrIP"/>
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

</style>
