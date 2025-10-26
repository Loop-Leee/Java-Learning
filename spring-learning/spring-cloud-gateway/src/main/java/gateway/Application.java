package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 配置路由, 添加过滤器, 将本地接收到的请求转发给 httpbin.org
	 */
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
		String httpUri = uriConfiguration.getHttpbin();
		return builder.routes()
				// 添加过滤器和请求头后, 将请求转发给 httpbin.org
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri(httpUri))
				// 添加熔断器, 将请求转发给 httpbin.org
				.route(p -> p
						.host("*.circuitbreaker.com")
						.filters(f -> f.circuitBreaker(config -> config
								.setName("mycmd")
								.setFallbackUri("forward:/fallback")))
						.uri(httpUri))
				.build();
	}

	/**
	 * 熔断处理, 用于服务不可用或响应慢时进行降级处理
	 */
	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}

}
