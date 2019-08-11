import { mapState } from 'vuex'

export default {
    requestClient: async function (url, options) {
      return await fetch('http://' + url, options).then(response => {
        return response.json();
      }).catch( reason => {
          console.log(JSON.stringify(reason))
          return null
      })

    }

}

