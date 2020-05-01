import Vue from 'vue'
import Router from 'vue-router'
import ToolsMain from "@/views/ToolsMain";
import UrlDecode from "@/views/tools/UrlDecode";
import BingPicture from "@/views/tools/BingPicture";
import IdiomSolitaire from "@/views/tools/IdiomSolitaire";
import ColorMap from "@/views/tools/ColorMap";
import DatabaseLab1 from "@/views/database-lab/DatabaseLab1";

Vue.use(Router)

export default new Router({
        mode: 'history',
        base: "/tools/",
        routes: [
            {
                path: '/',
                name: 'tools',
                component: ToolsMain,
                children: [
                    {
                        path: 'UrlDecode',
                        name: 'UrlDecode',
                        component: UrlDecode
                    },
                    {
                        path: 'BingPicture',
                        name: 'BingPicture',
                        component: BingPicture
                    },
                    {
                        path: 'IdiomSolitaire',
                        name: 'IdiomSolitaire',
                        component: IdiomSolitaire
                    },
                    {
                        path: 'ColorMap',
                        name: 'ColorMap',
                        component: ColorMap
                    },
                    {
                        path: 'DatabaseLab1',
                        name: 'DatabaseLab1',
                        component: DatabaseLab1
                    }
                ]
            }
        ]
    }
)
