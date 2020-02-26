package team.weacsoft.common.utils;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.io.*;

/**
 * @Description 本类提供对推文地址的读写方法
 * @ClassName WeChatUtil
 * @Author 魔法はまだ解けない
 * @date 2020.02.26 14:13
 */
public class WeChatUtil {

    public static String getTweetsURL()   {
        FileReader fileReader= null;
        String s="";
        try {
            fileReader = new FileReader("Tweets.txt");
            int i,j=0;
            char a[]=new char[100];
            i=fileReader.read();
            while(i!=-1){
                a[j]=(char)i;
                j++;
                i=fileReader.read();
            }
            s=new String(a,0,j);
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return s;
        }
    }
    public static String setTweetsURL(String s) {
        FileWriter fileWriter= null;
        String s1="true";
        try {
            fileWriter = new FileWriter("Tweets.txt");
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return s1;
        }
    }
}
