package com.alec.learning;

import java.io.File;

/**
 * ClassName: DeleteTest
 * Package: com.alec.learning
 * Description:
 *
 * @Author Alec
 * @Create 2024/3/4 14:16
 * @Version 1.0
 */
public class DeleteTest {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Github-repo\\learning\\FileInputOutput-learning\\test.txt");
            if(file.delete()){
                System.out.println(file.getName() + "已删除！");
            }else{
                System.out.println("删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
