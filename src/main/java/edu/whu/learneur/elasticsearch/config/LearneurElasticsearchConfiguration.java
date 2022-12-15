package edu.whu.learneur.elasticsearch.config;

import edu.whu.learneur.elasticsearch.utils.TransportUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;

import javax.net.ssl.SSLContext;

@Configuration
public class LearneurElasticsearchConfiguration extends ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    @Value("${spring.elasticsearch.host-and-port}")
    private String hostAndPort;

    @Override
    @NonNull
    public ClientConfiguration clientConfiguration() {
//        HttpHeaders compatibilityHeaders = new HttpHeaders();
//        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
//        compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;"
//                + "compatible-with=7");
        return ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                // .withDefaultHeaders(compatibilityHeaders)
                .withBasicAuth(username, password)
                .build();
    }

    @Override
    public RefreshPolicy refreshPolicy() {
        return RefreshPolicy.IMMEDIATE;
    }


}
