<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
  import { mapState } from 'vuex'

  export default {
    name: 'app',
    methods: {
      requestVue: async function (path, options) {
        let vm = this;
        console.log('requesting from ', path, JSON.stringify(options))
        return fetch(vm.serverRoot + '/path', options).then(response => {
          return response.json()
        })
      }
    },
    computed: mapState({
      riskServerRoot: state => state.serverHost,
    }),
  }
</script>

<style>
    #app {
        top: 0;
        position: absolute;
        width: 100%;
        height: 100%;
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        background-color: darkgrey;
    }
</style>
