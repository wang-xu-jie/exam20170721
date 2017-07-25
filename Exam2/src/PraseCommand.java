/**
 * Created by wxj on 2017/7/21.
 */
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class PraseCommand {
    private String Command;
    public PraseCommand(String Command){
        this.Command = Command;
    }

    public void prase(){
        String[] arr = Command.split("\\|");
        List<String> list = Arrays.asList(arr);
        String Result = null;

        for(String s : list){
            if(s.contains("cat")){
                String [] s1 = s.split(" ");
                List<String> list1 = Arrays.asList(s1);
                Result = printALLContent(list1.get(1));

            }else if(s.contains("grep")){
                String [] s2 = s.split(" ");
                List<String> list2 = Arrays.asList(s2);
                Result = printSpecialContent(list2.get(2),Result);

            }else if(s.contains("wc")){
                String [] s3 = s.split(" ");
                List<String> list3 = Arrays.asList(s3);
                Result = printLinesContents(Result) + "";
            }

        }


       System.out.print(Result);
    }

    public String printALLContent(String s){

        StringBuilder builder = new StringBuilder();
        BufferedReader bfr = null;
        try {
            bfr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(s))));
            String str = bfr.readLine();
            while (str != null) {
                builder.append(str + "\n");
                str = bfr.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

        return builder.toString();
    }

    public String printSpecialContent (String keyWord,String result){

        String [] arr =  result.split("\n");
        List<String> list = Arrays.asList(arr);
        StringBuilder stringBuilder = new StringBuilder();
        for(String str : list){
            if(str.contains(keyWord)){
                stringBuilder.append(str).append("\n");
            }

        }

        return  stringBuilder.toString();
    }

    public int printLinesContents(String result){
        String [] arr =  result.split("\n");
        return arr.length;
    }

    public static void main(String[] args) {
        PraseCommand pc = new PraseCommand("cat /Users/wxj/Exam2/src/xx.txt | grep xml | wc -l");
        pc.prase();
    }


}
