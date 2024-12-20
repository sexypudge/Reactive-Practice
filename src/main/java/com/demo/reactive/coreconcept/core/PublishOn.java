package com.demo.reactive.coreconcept.core;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class PublishOn {
    public static void main(String[] args) {

        Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"))
                .subscribe(Util.subscriber());

//
//        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));
//
//        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(2);
    }
}
