package in.softsolutionzone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import in.softsolutionzone.interceptor.GlobalInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	GlobalInterceptor globalInterceptor;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("https://msmartpay.in","http://msmartpay.in")
		.allowedMethods("GET", "POST","PUT","DELETE","TRACE","OPTIONS","PATCH","HEAD")
		.allowCredentials(true)
		.allowedHeaders("User-Agent","Authorization","X-UserID","Content-Type","Accept","Origin","X-Requested-With")
		.exposedHeaders("Authorization","X-Custom-header","X-Secret");
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/swagger-ui.html");
	}

}
