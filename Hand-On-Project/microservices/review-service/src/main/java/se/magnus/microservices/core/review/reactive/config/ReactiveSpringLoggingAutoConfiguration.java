package se.magnus.microservices.core.review.reactive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.magnus.microservices.core.review.reactive.filter.ReactiveSpringLoggingFilter;
import se.magnus.microservices.core.review.reactive.util.UniqueIDGenerator;


@Configuration
public class ReactiveSpringLoggingAutoConfiguration {

	private static final String LOGSTASH_APPENDER_NAME = "LOGSTASH";

	private String url = "localhost:8500";
	private String ignorePatterns;

	private String trustStoreLocation;
	private String trustStorePassword;
	@Value("${spring.application.name:-}")
	String name;

	@Bean
	public UniqueIDGenerator generator() {
		return new UniqueIDGenerator();
	}

	@Bean
	public ReactiveSpringLoggingFilter reactiveSpringLoggingFilter() {
		boolean logHeaders = true;
		boolean useContentLength = true;
		return new ReactiveSpringLoggingFilter(generator(), ignorePatterns, logHeaders, useContentLength);
	}

}
