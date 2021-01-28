const getters = {
  //book
  addBookVisible:state=>state.book.addBookVisible,
  getBookVisible:state=>state.book.getBookVisible,
  updateBookVisible:state=>state.book.updateBookVisible,
  updateImgVisible:state=>state.book.updateImgVisible,
  pageList:state=>state.book.pageList,
  bookList:state=>state.book.bookList,
  //audio
  addAudioVisible:state=>state.audio.addAudioVisible,
  audioList:state=>state.audio.audioList,

}
  
  export default getters