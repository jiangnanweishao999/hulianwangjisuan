package com.example.bookreader.blImpl.book;

import com.aliyun.oss.model.OSSObject;
import com.example.bookreader.bl.book.BookFormatService;
import com.example.bookreader.bl.book.BookTransferService;
import com.example.bookreader.data.book.BookMapper;
import com.example.bookreader.vo.Chapter;
import com.example.bookreader.po.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookFormatServiceImpl implements BookFormatService {
   @Autowired
    BookMapper bookMapper;

    @Autowired
    BookTransferService bookTransferService;
    @Override
    public List<Page> paging(String content, int lineLimit,int wordLimit,double offset) {
        List<Page> list=new ArrayList<>();
        ByteArrayInputStream stream=new ByteArrayInputStream(content.getBytes());
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(stream));
        int m=0;
        int beginId=1;
        double nowOffset=0;
        for(int i=1;m<2;i++){
            Page page=new Page();
            page.setLineLimit(lineLimit);
            page.setWordLimit(wordLimit);
            page.setId(i);

            //读取每个page
            StringBuilder pageContent= new StringBuilder();
            for(int j=0;j<lineLimit;j++){
                char[] lineChars=new char[wordLimit];
                for(int k=0;k<wordLimit;k++){
                    try {
                        char text = (char)bufferedReader.read();
                        if(text=='\n'){
                            if(String.valueOf(lineChars).length()!=0){
                                for(int n=0;n<lineChars.length;n++){
                                    if(lineChars[n]==0){
                                        lineChars[n]=' ';
                                    }
                                }
                            }
                            break;
                        }
                        lineChars[k]=text;
                    }
                    catch(IOException e){
                        System.out.println(e.getMessage());
                    }
                }
                if(lineChars[0]!=0&&lineChars[0]!=65535) {
                    for(int k=0;k<wordLimit;k++){
                        if(lineChars[k]!=' '){
                            nowOffset++;
                        }
                    }
                    if(nowOffset>=offset){
                        beginId=i;
                    }
                    pageContent.append(String.valueOf(lineChars));
                    pageContent.append('\n');
                    m = 0;
                }
                else{
                    m++;
                }

            }
            page.setContent(pageContent.toString());
            list.add(page);
            //添加页
        }
        for (Page page : list) {
            page.setBeginId(beginId);
        }
        return list;
    }

    @Override
    public String chaptering(int bookId,int ChapterNo) throws IOException {

        Chapter chapter=bookMapper.getChapter(bookId,ChapterNo);//获取章节
        String bookUrl=bookMapper.getBookUrl(bookId);//获取书的url
        double BeginLocation=chapter.getBeginLocation();
        double EndLocation=chapter.getEndLocation();
        OSSObject ossObject=bookTransferService.downloadBook(bookUrl);
        BufferedReader bufferedReader =new BufferedReader( new InputStreamReader(ossObject.getObjectContent(), StandardCharsets.UTF_8));
        String line;
        StringBuilder book= new StringBuilder();
        int i=1;
        while ((line = bufferedReader.readLine()) != null) {

            if(BeginLocation<=i&&i<=EndLocation) {
                book.append(line).append("\n");
            }
            if(i>EndLocation){
                break;
            }
            i++;
        }
        ossObject.close();

       return book.toString();
    }

    @Override

    public double getBookChapters(String sourcePath,int bookId){
        File file=new File(sourcePath);
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line;
            StringBuilder book= new StringBuilder();
            int i=1;
            int j;
            line=bufferedReader.readLine();
            for(j=1; line!= null; j++){
                Chapter chapter=new Chapter();
                chapter.setChapterName(line);
                System.out.println(chapter.getChapterName());
                chapter.setChapterNo(j);
                chapter.setBeginLocation(i + 1);
                chapter.setBookId(bookId);

                while((line=bufferedReader.readLine())!=null){
                    if(line.length()==0){
                        line=bufferedReader.readLine();
                        if(line.length()==0) {
                            chapter.setEndLocation(i);
                            i++;
                            i++;
                            break;
                        }
                        else{
                            book.append(line).append("\n");
                            i++;
                            i++;
                        }
                    }
                    else{

                        book.append(line).append("\n");
                        i++;
                    }
                }
                bookMapper.addChapter(chapter);

                i++;
                line=bufferedReader.readLine();
            }
            System.out.println(book);
            return j-1;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public void updateBook(int id,String bookAuthor,String bookDescription,String label){
        bookMapper.updateBook(id,bookAuthor,bookDescription,label);
    }


    public static void main(String[] args){
        BookFormatServiceImpl bookFormatServiceImpl=new BookFormatServiceImpl();
        try {
            String chapter=bookFormatServiceImpl.chaptering(16, 100);
            List<Page> pages=bookFormatServiceImpl.paging(chapter,10,10,0);
            for (Page page : pages) {
                System.out.print(page.getContent());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


   
}
