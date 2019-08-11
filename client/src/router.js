import Vue from 'vue'
import Router from 'vue-router'

import SelectGameServer from '@/components/SelectGameServer'
import SelectGame from '@/components/SelectGame'
import PlayGame from '@/components/PlayGame'

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
