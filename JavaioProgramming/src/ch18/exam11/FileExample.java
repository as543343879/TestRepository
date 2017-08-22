package ch18.exam11;
import java.io.File;
import java.io.IOException;
public class FileExample {
    public static void main(String[] args) throws IOException {
        
        File file = new File("src/ch18/exam11/FileExample.java");
        System.out.println(file.exists()); // 파일 존재여부 확인
        System.out.println(file.length());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        
        
        System.out.println("----------------------------");
        File dir = new File("C:/Windows");
        System.out.println(dir.exists()); // 파일 존재여부 확인
        System.out.println(dir.length());
        System.out.println(dir.isDirectory());
        System.out.println(dir.isFile());
        System.out.println("----------------------------");
        String[] contents1 = dir.list(); // 파일이나 디렉토리의 이름
        File[] contents2 = dir.listFiles(); //디렉토리 안에 있는 것을 file배열로 리턴
        
        for(File files :contents2){
            String info = "";
            info+=files.getName();
            info+="\t\t";
            info+=(files.isDirectory())?"<dir>":"";
            info+="\t\t";
            info+=files.length();
            System.out.println(info);
        }
        
        File file2 = new File("C:/Temp/test.txt");
        file2.createNewFile();
        
        File dir2 = new File("C:/Temp/dir2");
        dir2.mkdir();
        
        File dir3 = new File("C:/Temp/dir3/dir4/dir5");
        dir3.mkdirs();
        
        File fi= new File("C:/Temp/dir3/dir4/dir5/test.txt");
        fi.createNewFile();
        fi.delete();
        // 파일(디렉토리)의 생성 및 삭제
        
                
    }
    
}
