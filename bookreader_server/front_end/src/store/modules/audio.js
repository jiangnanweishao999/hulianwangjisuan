import {
    addAudioAPI,
    getAllAudiosAPI
} from "../../api/audio";
import { message } from 'ant-design-vue'


const audio = {
    state : {
        addAudioVisible:false,
        audioList:[

        ],
    },
    mutations : {
        set_addAudioVisible: function(state,data){
            state.addAudioVisible=data
        },
        set_audioList: function (state,data) {
            state.audioList=data
        }
    },
    actions: {
        addAudio: async ({state,commit},data)=>{
            const res = await addAudioAPI(data)
            if(res){
                message.success('上传成功')
            }
        },
        getAudioList: async ({state,commit}) => {
            const res = await getAllAudiosAPI()
            if(res){
                commit('set_audioList',res)
                message.success('获取成功')
            }else{
                message.error('获取失败')
            }
        }
    }
}
export default audio