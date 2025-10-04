package com.bluetrident.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Value("${app.cors.allowed-origins}")
	private String allowedOrigins;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String[] origins = getAllowedOrigins();
				registry.addMapping("/**").allowedOriginPatterns(origins) // <- use allowedOriginPatterns
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
						.allowCredentials(true).maxAge(3600);
			}

			private String[] getAllowedOrigins() {
				if (allowedOrigins == null || allowedOrigins.isEmpty()) {
					return new String[] { "http://localhost:4550" }; // default
				}
				String[] split = allowedOrigins.split(",");
				for (int i = 0; i < split.length; i++) {
					split[i] = split[i].trim();
				}
				return split;
			}
		};
	}
}
