import {
    addBookAPI,
    getBookAPI,
    getBookListAPI,
    updateBookAPI,
    updateImgAPI
} from '@/api/book'

import { message } from 'ant-design-vue'
import {storeShape} from "ant-design-vue/lib/_util/store/PropTypes";
import functionCaller from "less/lib/less/functions/function-caller";


const book = {
    state: {
        addBookVisible:false,
        getBookVisible:false,
        updateBookVisible:false,
        updateImgVisible:false,
        pageList:[

        ],
        bookList:[

        ],
    },
    mutations: {
        set_addBookVisible:function(state,data){
            state.addBookVisible=data
        },
        set_getBookVisible:function(state,data){
            state.getBookVisible=data
        },
        set_updateBookVisible:function(state,data){
            state.updateBookVisible=data
        },
        set_updateImgVisible:function(state,data){
            state.updateImgVisible=data
        },
        set_pageList:function(state,data){
            state.pageList=data
        },
        set_bookList:function(state,data){
            state.bookList=data
        },

    },
    actions: {
        addBook:async({state},data)=>{

            const res=await addBookAPI(data.sourcePath,data.bookName)

                message.success("上传成功")



        },
        getBook:async({state,commit},param)=>{
            const res=await getBookAPI(param)
            if(res){
                commit("set_pageList",res)
                message.success("获取成功")
            }
            else{
                message.error("获取失败")
            }
        },
        getBookList:async ({state,commit})=>{
            const res=await getBookListAPI()
            if(res){
                commit("set_bookList",res)
                message.success("获取成功")
            }
            else{
                message.error("获取失败")
            }
        },
        updateBook:async({state,dispatch},data)=>{
          const res=await updateBookAPI(data)
          dispatch("getBookList")
          message.success("更新成功")
        },
        updateImg:async({state,dispatch},data)=>{
            const res=await updateImgAPI(data)
            dispatch('getBookList')
            message.success("上传成功")
        }
    }
}
export default book