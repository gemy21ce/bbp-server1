package com.bbpl.server1.config;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class HttpConfig {
    private final int maxTotalConnections;
    private final int maxConnectionsPerRoute;
    private final int readTimeoutMilliseconds;
    private final int connectionTimeoutMilliseconds;

    public HttpConfig(@Value("${http.maxTotalConnections}") int maxTotalConnections,
            @Value("${http.maxConnectionsPerRoute}") int maxConnectionsPerRoute,
            @Value("${http.readTimeoutMilliseconds}") int readTimeoutMilliseconds,
            @Value("${http.connectionTimeoutMilliseconds}") int connectionTimeoutMilliseconds) {
        this.maxTotalConnections = maxTotalConnections;
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
        this.readTimeoutMilliseconds = readTimeoutMilliseconds;
        this.connectionTimeoutMilliseconds = connectionTimeoutMilliseconds;
    }

    @Bean
    public RestTemplate restTemplate() {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager())
                .setDefaultRequestConfig(defaultRequestConfig())
                .build();
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        restTemplate.setInterceptors(Collections.singletonList(new RequestErrorsLoggingInterceptor()));
        return restTemplate;
    }

    private HttpClientConnectionManager connectionManager() {
        final PoolingHttpClientConnectionManager connectionManager;
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);
        return connectionManager;
    }

    private RequestConfig defaultRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(connectionTimeoutMilliseconds)
                .setSocketTimeout(readTimeoutMilliseconds)
                .setRedirectsEnabled(true)
                .setRelativeRedirectsAllowed(true)
                .setContentCompressionEnabled(true)
                .build();
    }
}
