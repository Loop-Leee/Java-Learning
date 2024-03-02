import java.util.Properties;

/**
 * ClassName: SendEmail
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/3/2 19:55
 * @Version 1.0
 */
public class SendEmail {
    public static void main(String[] args) {
        String to = "905644311@qq.com";
        String from = "574101095@qq.com";
        // 发送邮件的主机设置为 QQ 邮件服务器
        String host = "smtp.qq.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        // 没看完这部分，用到的时候再看
    }
}
