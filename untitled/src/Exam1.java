
import java.io.*;
import java.util.*;
/**
 * Created by wxj on 2017/7/21.
 */
public class Exam1 {

      private ArrayList<File> list = new ArrayList();
      private HashMap<Object,Integer> map = new HashMap<Object,Integer>();
      private int charCode = 0 ;
      private int letterNumber = 0;
      private int digitNumber = 0;
      private int spaceNumber = 0;

    public ArrayList<File> getList() {
        return list;
    }

    public HashMap<Object, Integer> getMap() {
        return map;
    }

    public int getCharCode() {
        return charCode;
    }

    public int getLetterNumber() {
        return letterNumber;
    }

    public int getDigitNumber() {
        return digitNumber;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setList(ArrayList<File> list) {
        this.list = list;
    }

    public void setMap(HashMap<Object, Integer> map) {
        this.map = map;
    }

    public void setCharCode(int charCode) {
        this.charCode = charCode;
    }

    public void setLetterNumber(int letterNumber) {
        this.letterNumber = letterNumber;
    }

    public void setDigitNumber(int digitNumber) {
        this.digitNumber = digitNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }


    //通过递归操作得到指定路径下的所有文件
      public  void getFiles(String filepath) {

          File file = new File(filepath);
          if(file.isDirectory()){
              File[] files = file.listFiles();

              for(File fileIndex:files){
                  //如果这个文件是目录的话就继续进行递归搜索
                  if(fileIndex.isDirectory()){
                      getFiles(fileIndex.getPath());
                  }else{
                      list.add(fileIndex);
                  }
              }
          }else {
              list.add(file);
          }
      }


      public void get() {
          for(File file : list){
              InputStream in = null;
              try{
                  in = new FileInputStream(file);

                  while((charCode = in.read())!= -1) {
                      if (('a' <= charCode && charCode <= 'z') || 'A' <= charCode && charCode <= 'Z') {
                          if (!map.containsKey((char) charCode)) {
                              map.put((char) charCode, 1);
                          } else {
                              map.put((char) charCode, map.get((char) charCode) + 1);
                          }
                          letterNumber++;
                      } else if (('0' <= charCode) && charCode <= '9') {
                          if (!map.containsKey((char) charCode)) {
                              map.put((char) charCode, 1);
                          } else {
                              map.put((char) charCode, map.get((char) charCode) + 1);
                          }
                          digitNumber++;
                      } else if ((char) charCode == ' ') {
                          spaceNumber++;
                      }
                 }
              }catch(Exception e){
                  e.printStackTrace();
              }finally {
                  if(in!=null){
                      try{
                          in.close();
                      }catch(Exception e){
                          e.printStackTrace();
                      }
                  }
              }
          }

      }

      public void write(){
        File file = new File("/Users/wxj/untitled/characterType.txt");
        FileWriter write = null;
        try{

            write = new FileWriter(file.getName());
            String s = "数字 "+ digitNumber + " 个" + "\n";
            write.append(s);
            String s1 = "字母 "+ letterNumber + " 个" + "\n";
            write.append(s1);
            String s2 = "空格 "+ spaceNumber + " 个" + "\n";
            write.append(s2);
            String s3 = "数字0 " + map.get('0') + " 个" + "\n";
            write.append(s3);
            String s4 = "字母a " + map.get('a') + " 个" + "\n";
            write.append(s4);
            String s5 = "字母A " + map.get('A') + " 个" + "\n";
            write.append(s5);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(write!=null){
                try{
                    write.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }





      }

    public static void main(String[] args) {

        Exam1 exam1 = new Exam1();

        exam1.getFiles("/Users/wxj/untitled/src/java.txt");

        exam1.get();
        System.out.println(exam1.getDigitNumber());
        System.out.println(exam1.getLetterNumber());
        System.out.println(exam1.getSpaceNumber());
        exam1.write();


    }








}
