<template>
  <div id="app" v-title data-title="传书">
    <a-button @click="upload">上传</a-button>
    <a-button @click="download">读书</a-button>
    <a-button @click="clearContent">清除</a-button>
    <a-button @click="uploadAudio">音频</a-button>
    <a-button @click="get_audioList">音频列表</a-button>
    <a-button @click="clearAudio">清除</a-button>
    <a-button @click="get_BookList">书列表</a-button>
    <a-button @click="clearBook">清除</a-button>
    <a-button @click="update">修改书信息</a-button>
    <a-button @click="Img">修改书图片</a-button>
      <a-table
              :columns="columns1"
              :dataSource="pageList"
              bordered
      >
      </a-table>
      <a-table
              :columns="columns2"
              :dataSource="bookList"
              bordered
      >

      </a-table>
    <a-table
            :columns="columns3"
            :dataSource="audioList"
            bordered
    >

    </a-table>
    <AddBook></AddBook>
    <GetBook></GetBook>
    <AddAudio></AddAudio>
    <UpdateBook></UpdateBook>
    <UpdateImg></UpdateImg>
  </div>
</template>
<script>
  import { mapGetters, mapMutations, mapActions } from 'vuex'
  import AddBook from './components/addBook'
  import GetBook from './components/getBook'
  import AddAudio from './components/addAudio'
  import UpdateBook from './components/updateBook'
  import UpdateImg from './components/updateImg'
  const columns1 = [
    {
      title:'内容',
      dataIndex:'content'
    },
  ]
  const columns2 = [
    {
      title:'id',
      dataIndex:'id'
    },
    {
      title:'书名',
      dataIndex: 'bookName'
    },
    {
      title:'作者',
      dataIndex:'bookAuthor'
    },
    {
      title:'章节数',
      dataIndex:'bookLength'
    },
    {
      title:'介绍',
      dataIndex:'bookDescription'
    },
    {
      title:'存储地址',
      dataIndex:'bookUrl'
    },
    {
      title:'封面地址',
      dataIndex:'bookImgUrl'
    },
    {
      title:'类型',
      dataIndex:'label'
    }
  ]
const  columns3 =[
    {
      title:'id',
      dataIndex:'id',
    },
    {
      title: '名称',
      dataIndex: 'audioName'
    },
    {
      title: '编号',
      dataIndex: 'audioNo'
    },
    {
      title: '书Id',
      dataIndex: 'bookId'
    },
    {
      title: '介绍',
      dataIndex: 'audioDescription'
    },
    {
      title: 'URL',
      dataIndex: 'radioUrl'
    }
  ]
  export default {
    name: '传书',
    data(){
      return {
        columns1,
        columns2,
        columns3,
      }
    },
    components: {
      AddBook,
      GetBook,
      AddAudio,
      UpdateBook,
      UpdateImg,
    },
    computed: {
      ...mapGetters([
        "pageList",
        "bookList",
        'audioList'
      ]),
    },
    async mounted() {

    },
    methods: {
      ...mapMutations([
              "set_addBookVisible",
              "set_getBookVisible",
              'set_addAudioVisible',
              "set_updateBookVisible",
              "set_pageList",
              'set_audioList',
              "set_updateImgVisible",
              "set_pageList",
              "set_bookList",
              "set_audioList"

      ]),
      ...mapActions([
              "getBookList",
              'getAudioList'
      ]),
      upload(){
        this.set_addBookVisible(true)
      },
      download(){
        this.set_getBookVisible(true)
      },
      uploadAudio() {
          this.set_addAudioVisible(true)
      },
      update(){
        this.set_updateBookVisible(true)
      },
      Img(){
        this.set_updateImgVisible(true)
      },
      clearContent(){
        this.set_pageList(null)
      },
      clearBook(){
        this.set_bookList(null)
      },
      clearAudio(){
        this.set_audioList(null)
      },
      get_BookList(){
        this.getBookList();
      },
      get_audioList(){
        this.getAudioList()

      }
    }
  }
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100%;
  padding: 20px 100px 144px;
  min-height: 800px
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

/* fade-transform */
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all .5s;
}

.fade-transform-enter {
  opacity: 0;
  transform: translateX(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
</style>
