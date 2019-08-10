import Vue from 'vue'
import Router from 'vue-router'

import SelectGameServer from '@/views/SelectGameServer'
import SelectGame from '@/views/SelectGame'
import PlayGame from '@/views/PlayGame'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'selectGameServer',
            component: SelectGameServer,
        },
        {
            path: '/selectGame',
            name: 'selectGame',
            component: SelectGame,
        },
        {
            path: '/playGame',
            name: 'playGame',
            component: PlayGame,
        },
    ]
})
