import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        serverHost: '',
        game: null,
    },
    mutations: {
        setServerHost(state, host) {
            state.serverHost = host
        },
        setGame: (state) => (g) => state.game = g,
        removeGame: state => state.game = null,
    },
    actions: {
        request() {

        },
        setServerHost: (state) => (host) => state.serverHost = host
    },
});
