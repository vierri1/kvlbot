package ru.poponinav.kvlbot.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    private static final int RETRY_ATTEMPTS_INTERNAL_SERVER_ERROR_COUNT = 3;

    @Bean
    public TransportClient transportClient() {
        return new HttpTransportClient();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean
    public VkApiClient vkApiClient(TransportClient transportClient, Gson gson) {
        return new VkApiClient(transportClient, gson, RETRY_ATTEMPTS_INTERNAL_SERVER_ERROR_COUNT);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
