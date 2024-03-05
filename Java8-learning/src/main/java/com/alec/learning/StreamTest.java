package com.alec.learning;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: StreamTest
 * Package: com.alec.learning
 * Description:
 *
 * @Author Alec
 * @Create 2024/3/4 17:44
 * @Version 1.0
 */
public class StreamTest {
    public static void main(String[] args) {
        System.out.println("使用 java 7");

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println("列表: " +strings);

        // java7 统计空字符串
        long count = getCountEmptyStringUsingJava7(strings);
        System.out.println("java7 统计的空字符数量为: " + count);

        // java8 统计空字符串
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("java8 统计的空字符数量为: " + count);


        count = getCountLength3UsingJava7(strings);
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表: " + filtered);

        String mergedString = getMergedStringUsingJava7(strings, ",");
        System.out.println("java7 合并字符串: " + mergedString);

        mergedString = strings.parallelStream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.joining(","));
        System.out.println("java8 合并字符串: " + mergedString);


        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);


        // 输出 10 个随机数
        Random random = new Random();
        System.out.println("java7 输出随机数");
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }

        System.out.println("java8 输出随机数");
        random.ints().limit(10).sorted().forEach(System.out::println);


    }

    private static String getMergedStringUsingJava7(List<String> strings, String s) {
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings){
            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(s);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-1);
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<>();
        for(String string:strings){
            if(!string.isEmpty()){
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static int getCountLength3UsingJava7(List<String> strings) {
        int count = 0;
        for(String string:strings){
            if(string.length()==3){
                count ++;
            }
        }
        return count;
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        int count = 0;
        for(String string:strings){
            if(string.isEmpty()){
                count++;
            }
        }
        return count;
    }
}
