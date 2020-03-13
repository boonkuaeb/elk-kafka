package se.magnus.microservices.core.review.reactive.interceptor;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;
import se.magnus.microservices.core.review.reactive.filter.ReactiveSpringLoggingFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

import static net.logstash.logback.argument.StructuredArguments.value;

public class RequestLoggingInterceptor extends ServerHttpRequestDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    private boolean logHeaders;

    @Value("${spring.application.name:-}")
    String name;

    public RequestLoggingInterceptor(ServerHttpRequest delegate, boolean logHeaders) {
        super(delegate);
        this.logHeaders = logHeaders;
    }

    @Override
    public Flux<DataBuffer> getBody() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        return super.getBody().doOnNext(dataBuffer -> {
            try {
                Channels.newChannel(baos).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                String body = IOUtils.toString(baos.toByteArray(), "UTF-8");
                if (logHeaders) {

                    /**
                     *  LOGGER.info("Response: time={} status={}, headers={}, payload={}, audit={}", value("X-Response-Time", System.currentTimeMillis() - startTime),
                     */
                    LOGGER.info("{} {} [{}] [{}] {} {} {} {} {}",
                            this.name,
                            "Request",
                            getDelegate().getHeaders(),
                            body,
                            value("audit", true),
                            "na",
                            "na",
                            getDelegate().getMethod(),
                            getDelegate().getPath()
                    );
                }else{
                    LOGGER.info("{} {} [{}] [{}] {} {} {} {} {}",
                            this.name,
                            "Request",
                            "na",
                            body,
                            value("audit", true),
                            "na",
                            "na",
                            getDelegate().getMethod(),
                            getDelegate().getPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
