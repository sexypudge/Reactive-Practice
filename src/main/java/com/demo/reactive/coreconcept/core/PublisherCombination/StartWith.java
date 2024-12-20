package com.demo.reactive.coreconcept.core.PublisherCombination;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class StartWith {
    public static void main(String[] args) {

    }

    private static Flux<Integer> producer() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }
}
