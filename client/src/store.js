import Vue from 'vue'
import Vuex from 'vuex'
import HttpClient from '@/shared/service/HttpClient'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        riskServerRoot: localStorage.getItem('riskServerRoot') || '',
        game: JSON.parse(localStorage.getItem('game')),
        player: JSON.parse(localStorage.getItem('player')),
        failure: '',
    },
    mutations: {
        setServerHost (state, host) {
            console.log('commits', host)
            state.riskServerRoot = host + ':1301/mm-risiko'
            localStorage.setItem('riskServerRoot', host + ':1301/mm-risiko')
        },
        setGame (state, game) {
            console.log('setGame called', JSON.stringify(game))
            state.game = game
            localStorage.setItem('game',  JSON.stringify(game))
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
            console.log(state.riskServerRoot)
            console.log(JSON.stringify(options))
            HttpClient.requestClient(this.state.riskServerRoot + '/games/' + options.gameName, {
                method: 'PUT',
                headers: { 'Content-type': 'application/json' },
                body: JSON.stringify(options.request)
            }).then(json => {
                this.state.player = options.request.player
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
            console.log('loading game', name)
            if(!name) {
                throw new Error('kein name für game gefunden')
            }
            HttpClient.requestClient(this.state.riskServerRoot + '/games/' + name , {
                method: 'GET',
                headers: { 'Content-type': 'application/json' },
            }).then(json => {
                console.log('game bekommen', JSON.stringify(json))
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
                state.game = json.game
                state.player = options.request.player
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
                // todo if success reload game

            }).catch(reason => {
                this.commit('setFailure', reason)
            })
        },
    },
})
