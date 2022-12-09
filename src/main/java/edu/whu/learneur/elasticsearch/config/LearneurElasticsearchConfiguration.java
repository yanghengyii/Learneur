package edu.whu.learneur.elasticsearch.config;

import co.elastic.clients.transport.TransportUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;

import javax.net.ssl.SSLContext;

@Configuration
public class LearneurElasticsearchConfiguration extends AbstractElasticsearchConfiguration {
    @Value("${spring.elasticsearch.http-ca.sha256-fingerprint}")
    private String fingerprint;

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    @Value("${spring.elasticsearch.host-and-port}")
    private String hostAndPort;

    @Value("${spring.elasticsearch.path-prefix}")
    private String pathPrefix;

    @Override
    @Bean
    @NonNull
    public RestHighLevelClient elasticsearchClient() {
        HttpHeaders compatibilityHeaders = new HttpHeaders();
        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;"
                + "compatible-with=7");
        SSLContext sslContext = TransportUtils.sslContextFromCaFingerprint(fingerprint);
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                .usingSsl(sslContext)
                .withDefaultHeaders(compatibilityHeaders)
                .withBasicAuth(username, password)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    @Override
    @NonNull
    protected FieldNamingStrategy fieldNamingStrategy() {
        return new SnakeCaseFieldNamingStrategy();
    }
}
