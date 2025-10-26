package gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author lloop
 * @Create 2025/10/26 18:39
 */
@ConfigurationProperties
public class UriConfiguration {

    private String httpbin = "http://httpbin.org:80";

    public String getHttpbin() {
        return httpbin;
    }

    public void setHttpbin(String httpbin) {
        this.httpbin = httpbin;
    }
}
