package fileIO;

import java.io.*;

public class OutputTest {
    public static void main(String[] args)  {
        try {
            // BufferedWriter 本身不提供处理字符流的功能，它只用来缓存字符流
            // OutputStreamWriter 用于处理字节流，将其转换为字符流
            // OutputStream 是字符流本身
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./FileInputOutput-learning/test.txt")));
            out.write("Hello World!\n");
            out.close();
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./FileInputOutput-learning/test.txt", true)));
            out.write("Hello World!");
            out.close();
            System.out.println("文件创建成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}