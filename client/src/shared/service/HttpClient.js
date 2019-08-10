import { mapState } from 'vuex'

export default {
    serverHost: '',
    setServerHost: function(host) {
        this.serverHost = host + ':1301/mm-risiko';
        console.log('sets server root url to', this.serverHost)
    },
    requestClient: async function (path, options) {
      console.log(this.serverHost)
      return await fetch('http://' + (this.serverHost ? this.serverHost + '/' + path : path +':1301/mm-risiko/status'), options).then(response => {
        return response.json();
      }).catch( reason => {
          console.log(JSON.stringify(reason))
          return null
      })

    }

}

