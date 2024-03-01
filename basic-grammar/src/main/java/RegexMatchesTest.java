import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

/**
 * ClassName: RegexMatchesTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/2/29 9:15
 * @Version 1.0
 */
public class RegexMatchesTest {

    private static String REGEX = "a*b";
    private static String INPUT = "fFaabfooaabfooabfoobkkk";
    private static String REPLACE = "-";
    public static void main(String[] args) {

        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        System.out.println(m.replaceAll(REPLACE));

/*        StringBuffer sb = new StringBuffer();
        while(m.find()){
            m.appendReplacement(sb,REPLACE);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());*/
    }


/*    private static final String REGEX = "\\bcat\\b";
    private static final String INPUT = "cat cat cattie cat";

    public static void main(String[] args) {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern1 = "(\\D*)(\\d+)(.*)";
        String pattern2 = "[habc]";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern2);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);

        int count = 0;
        while (m.find( )) {
            count ++;
            *//*System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
            System.out.println(m.groupCount());*//*
            System.out.println("Match number "+count);
            System.out.println("start(): "+m.start());
            System.out.println("end(): "+m.end());
        }
    }*/
}
