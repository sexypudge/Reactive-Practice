package com.demo.reactive.coreconcept.sec7;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

@Slf4j
public class ExternalServiceClient {
    private static final String BASE_URL = "http://localhost:7070";
    private LoopResources loopResources = LoopResources.create("vins", 1, true);
    protected final HttpClient httpClient = HttpClient.create().runOn(loopResources).baseUrl(BASE_URL);

    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(m -> log.info("next: {}", m))
                .next()
                .publishOn(Schedulers.boundedElastic());
    }
}
