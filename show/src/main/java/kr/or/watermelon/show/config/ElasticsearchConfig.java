package kr.or.watermelon.show.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    static private RestHighLevelClient _client = null;

    @Bean
    static public RestHighLevelClient client(@Value("${elasticsearch.host}") String host, @Value("${elasticsearch.port}") int port) {
        if (_client == null) {
            _client = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, "http")));
        }
        return _client;
    }
}
