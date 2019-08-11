import Vue from 'vue'
import Vuex from 'vuex'
import HttpClient from '@/shared/service/HttpClient'

Vue.use(Vuex)

export default new Vuex.Store({
    mutations: {
        setServerHost (state, host) {
            state.riskServerRoot = host + ':1301/mm-risiko'
            localStorage.setItem('riskServerRoot', host + ':1301/mm-risiko')
        },
        setGame (state, game) {
            state.game = game
            localStorage.setItem('game',  JSON.stringify(game));
        },
        setPlayer (state, player) {
            state.player = player;
            localStorage.setItem('player',  JSON.stringify(player));
        },
        setFailure (state, failure) {
            state.failure = failure
        },
        removeGame: state => state.game = null,
    },
    actions: {
        checkServerRoot: function (state, serverHostNameOrIP) {
            console.log('checking', serverHostNameOrIP)
            HttpClient.requestClient(serverHostNameOrIP + ':1301/mm-risiko/status').then(json => {
                if (json && 'OK' === json.message) {
                    this.commit('setServerHost', serverHostNameOrIP)
                }
            }).catch(reason => {
                this.commit('setFailure', reason)
            })
        },
        addGame: function (state, options) {
            HttpClient.requestClient(this.state.riskServerRoot + '/games/' + options.gameName, {
                method: 'PUT',
                headers: { 'Content-type': 'application/json' },
                body: JSON.stringify(options.request)
            }).then(json => {
                this.commit('setPlayer', options.request.player)
                if (json && 'SUCCESS' === json.status) {
                    console.log(JSON.stringify(json), options.gameName)
                    this.dispatch('loadGame', options.gameName)
                }else {
                    console.error('kein json oder ohne success')
                }

            }).catch(reason => {
                this.commit('setFailure', reason)
            })
        },
        loadGame: function (state, name) {
            if(!name) {
                throw new Error('kein name für game gefunden')
            }
            HttpClient.requestClient(this.state.riskServerRoot + '/games/' + name , {
                method: 'GET',
                headers: { 'Content-type': 'application/json' },
            }).then(json => {
                this.commit('setGame', json)
            }).catch(reason => {
                this.commit('setFailure', reason)
            })
        },
        addPlayer: function (state, options) {
            HttpClient.requestClient(this.state.riskServerRoot + '/games/' + options.gameName, {
                method: 'POST',
                headers: { 'Content-type': 'application/json' },
                body: JSON.stringify(options.request)
            }).then(json => {
                this.commit('setPlayer', options.request.player)
                if (json && 'SUCCESS' === json.status) {
                    this.dispatch('loadGame', options.gameName)
                }else {
                    console.error('kein json oder ohne success')
                }
            }).catch(reason => {
                this.commit('setFailure', reason)
            })
        },
        changeGame: function (state, gameChangeRequest) {
            HttpClient.requestClient(this.state.riskServerRoot + '/games/' + this.state.game.name, {
                method: 'POST',
                headers: { 'Content-type': 'application/json' },
                body: JSON.stringify(gameChangeRequest)
            }).then(json => {
                console.log(JSON.stringify(json))

            }).catch(reason => {
                this.commit('setFailure', reason)
            })
        },
        reloadGame: function(state, options) {
            this.dispatch('loadGame', this.state.game.name);
        }
    },
    state: {
        riskServerRoot: localStorage.getItem('riskServerRoot') || '',
        game: JSON.parse(localStorage.getItem('game')),
        player: JSON.parse(localStorage.getItem('player')),
        failure: '',
    },
})
