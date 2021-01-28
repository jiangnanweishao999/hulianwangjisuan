package com.example.bookreader.blImpl.book;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.example.bookreader.bl.book.BookFormatService;
import com.example.bookreader.bl.book.BookTransferService;
import com.example.bookreader.data.book.BookMapper;
import com.example.bookreader.data.message.MessageMapper;
import com.example.bookreader.po.book.Book;
import com.example.bookreader.po.message.Message;
import com.example.bookreader.vo.Chapter;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class BookTransferServiceImpl implements BookTransferService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    MessageMapper  messageMapper;
   @Autowired
    BookFormatService bookFormatService;

    private OSS oss=null;

    @Override
    public void initialize(){
       // String endpoint= OSSconfig.endpoint;
        String endpoint="oss-cn-shanghai.aliyuncs.com";
        //String accessKeyId=OSSconfig.accessKeyId;
        String accessKeyId="LTAI4GHKVzMMMQYYHrspbMFF";
        //String accessKeyId="LTAI4GCTpsxuJBMD2CoNbXGv";
        //String accessKeySecret=OSSconfig.accessKeySecret;
        String accessKeySecret="DNoaGbNwZfnehzS2vWVtHmYHLbu2IB";
        //String accessKeySecret="0K75e8beplHBYDGBgYsqQZtxwqh8A6";
        oss= new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }


    @Override
    public boolean isInitialized(){
        return oss != null;
    }

    /*
    * 上传图书
    *
    * */
    @Override
    public ResponseVO uploadBook(String sourcePath, String destBucket, String destFileName)  {
        Book book=new Book();
        book.setBookName(destFileName.substring(0,destFileName.length()-4));
        book.setBookUrl(destBucket+":"+destFileName);
        bookMapper.addBook(book);
        int bookId=bookMapper.getBookId(book.getBookName());
       // BookFormatService bookFormatService=new BookFormatServiceImpl();
       double chapters=bookFormatService.getBookChapters(sourcePath,bookId);
       bookMapper.setBookLength(bookId,chapters);
       initialize();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourcePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("找不到文件");
        }
        oss.putObject(destBucket, destFileName, inputStream);
        oss.shutdown();

        return ResponseVO.buildSuccess(true);
    }

     /*
     * 下载图书
     *
     * */
     @Override
     public OSSObject downloadBook(String bookUrl){
         String bucket=bookUrl.split(":")[0];
         String fileName=bookUrl.split(":")[1];
         return downloadBook(bucket,fileName);
     }

    /**
     * 获取图书列表
     * @return
     */
    @Override
    public List<Book> getBookList(String content){
         List<Book> target=bookMapper.getBookList();
        for (Book value : target) {
            String label = value.getLabel();
            List<String> labels = Arrays.asList(label.split("#"));
            value.setLabels(labels);
        }
         List<Book> result=new ArrayList<>();
         if(!content.equals("")){
             for(Book book:target){
                 boolean type=false;
                 for(int i=0;i<book.getLabels().size();i++){
                     if(book.getLabels().get(i).equals(content)){
                         type=true;
                         break;
                     }
                 }
                 if(book.getBookName().contains(content)||book.getBookAuthor().contains(content)||book.getBookDescription().contains(content)||type){
                     result.add(book);
                 }
             }
         }
         else{
             result=target;
         }
         return result;
     }
     @Override
     public List<Book> getGoodBookList(){
        List<Book> result=new ArrayList<>();
        List<Book> target=bookMapper.getBookList();
         for (Book value : target) {
             String label = value.getLabel();
             List<String> labels = Arrays.asList(label.split("#"));
             value.setLabels(labels);
         }
        List<Integer> bookId=new ArrayList<>();
         for (Book book : target) {
             bookId.add(book.getId());
         }
        List<Message> messages=messageMapper.getAllUserMessages();
         SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
         Date date = new Date(System.currentTimeMillis());
         String Date = sf.format(date);
         List<Message> todayMessage=new ArrayList<>();
         for (Message message : messages) {
             if (message.getDate().substring(0, 10).equals(Date)) {
                 todayMessage.add(message);
             }
         }
         List<Integer> times=new ArrayList<>();
         for (Integer integer : bookId) {
             int time = 0;
             for (Message message : todayMessage) {
                 if (message.getBookId() == integer && message.getAction().equals("finish reading")) {
                     time++;
                 }
             }
             times.add(time);
         }
        int max1=0;
        int max2=0;
        int max3=0;
         for (Integer time : times) {
             if (time > max1) {
                 max1 = time;
             }
         }
        for(int i=0;i<times.size();i++){
            if(times.get(i)==max1){
                result.add(target.get(i));
                target.remove(i);
                times.remove(i);
                break;
            }
        }
         for (Integer time : times) {
             if (time > max2) {
                 max2 = time;
             }
         }
         for(int i=0;i<times.size();i++){
             if(times.get(i)==max2){
                 result.add(target.get(i));
                 target.remove(i);
                 times.remove(i);
                 break;
             }
         }
         for (Integer time : times) {
             if (time > max3) {
                 max3 = time;
             }
         }
         for(int i=0;i<times.size();i++){
             if(times.get(i)==max3){
                 result.add(target.get(i));
                 target.remove(i);
                 times.remove(i);
                 break;
             }
         }
         return result;
     }

    /**
     * 上传图片
      * @param id
     * @param sourcePath
     * @param destBucket
     * @return
     */
    @Override
    public ResponseVO uploadImg(int id,String sourcePath,String destBucket,String filename){
        initialize();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourcePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("找不到文件");
        }
        oss.putObject(destBucket, filename, inputStream);
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = oss.generatePresignedUrl(destBucket, filename, expiration).toString();
        bookMapper.updateImg(id,url);
        oss.shutdown();
        return ResponseVO.buildSuccess();
     }

    private OSSObject downloadBook( String targetBucket, String targetFileName){
            initialize();

        OSSObject ossObject = oss.getObject(targetBucket, targetFileName);
        // 使用缓冲流读取  设置编码格式
            return ossObject;


    }
    public List<String> getBookContent(int id) throws IOException {
        List<String> result=new ArrayList<>();
        for(int i=1;i<5;i++){
            String chapter=bookFormatService.chaptering(id,i);
            Chapter chapter1=bookMapper.getChapter(id,i);
            String title=chapter1.getChapterName();
            char[] a=chapter.toCharArray();
            int end=0;
            for(int j=0;j<a.length;j++){
                boolean isEnd=true;
                for(int k=0;k<title.length();k++){
                    if(a[j+k]!=title.charAt(k)){
                        isEnd=false;
                        break;
                    }
                }
                if(isEnd){
                    end=j+title.length()+1;
                }
            }
            if(chapter.length()>end+100) {
                result.add(chapter.substring(end, end + 100) + "...");
            }
            else{
                result.add(chapter.substring(end));
            }
        }
        return result;
    }
    @Override
    public Book getBook(int id) throws IOException {
        Book book=bookMapper.getBook(id);
        book.setContents(getBookContent(id));
        List<String> chapterList=bookMapper.getChapterList(id);
        book.setChapters(chapterList);
        return book;
    }
    public static void main(String[] args){
         BookTransferServiceImpl bookTransferService=new BookTransferServiceImpl();
         bookTransferService.uploadBook("D:\\十日谈.txt","weiyihui-ruangong2homework","十日谈.txt");
    }



}
