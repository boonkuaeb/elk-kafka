package se.magnus.microservices.core.review.reactive.interceptor;

import org.apache.commons.io.IOUtils;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

import static net.logstash.logback.argument.StructuredArguments.value;

public class ResponseLoggingInterceptor extends ServerHttpResponseDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseLoggingInterceptor.class);

    @Value("${spring.application.name:-}")
	String name;
    private long startTime;
    private boolean logHeaders;

    public ResponseLoggingInterceptor(ServerHttpResponse delegate, long startTime, boolean logHeaders) {
        super(delegate);
        this.startTime = startTime;
        this.logHeaders = logHeaders;
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        Flux<DataBuffer> buffer = Flux.from(body);
        return super.writeWith(buffer.doOnNext(dataBuffer -> {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                Channels.newChannel(baos).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                String bodyRes = IOUtils.toString(baos.toByteArray(), "UTF-8");

                    LOGGER.info("{} {} [{}] [{}] {} {} {} {} {}",
							name,
							"Response",
							getHeaders(),
							bodyRes,
							value("audit", true),
							value("X-Response-Time", System.currentTimeMillis() - startTime),
							value("X-Response-Status", getStatusCode().value()),
							"na",
							"na"
					);


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
