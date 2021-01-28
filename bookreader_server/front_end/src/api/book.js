import { axios } from '@/utils/request'
const api = {
    bookPre: '/api/book'
}
export function addBookAPI(sourcePath,bookName){
   return  axios({
        url:`${api.bookPre}/${sourcePath}/${bookName}/addBook`,
        method:'GET'
    })
}
export function getBookAPI(param){
    return axios({
        url:`${api.bookPre}/${param.book}/${param.chapter}/${param.line}/${param.word}/getChapterAsPages`,
        method:'GET',
    })
}
export function getBookListAPI(){
    return axios({
        url:`${api.bookPre}/getBookList`,
        method:'POST',
    })
}
export function updateBookAPI(data) {
    return axios({
        url:`${api.bookPre}/${data.id}/${data.bookAuthor}/${data.bookDescription}/${data.label}/updateBook`,
        method:'POST',
    })
}
export function updateImgAPI(data){
    return axios({
        url:`${api.bookPre}/${data.id}/${data.path}/${data.filename}/updateImg`,
        method:'POST',
    })
}
