import { axios } from '@/utils/request'
const api = {
    audioPre: '/api/audio'
}
export function addAudioAPI(data){
    return  axios({
        url:`${api.audioPre}/addAudio`,
        method:'POST',
        data
    })
}

export function getAllAudiosAPI() {
    return axios({
        url:`${api.audioPre}/getAllAudio`,
        method:'POST'
    })
}
