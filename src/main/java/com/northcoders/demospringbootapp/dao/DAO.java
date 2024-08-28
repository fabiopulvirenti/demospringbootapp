package com.northcoders.demospringbootapp.dao;

import org.springframework.web.reactive.function.client.WebClient;

public abstract class DAO {
    private WebClient webClient;

    protected DAO(String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    protected WebClient getWebClient() {
        return this.webClient;
    }
}
