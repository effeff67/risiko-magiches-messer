import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        game: null,
    },
    mutations: {
        setGame: (state) => (g) => state.game = g,
        removeGame: state => state.game = null
    },
    actions: {},
});
