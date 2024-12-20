package com.demo.reactive.coreconcept.core;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class HotPublisherAutoConnect {
    public static void main(String[] args) {
//        var movieFlux = movieStream();
        var movieFlux = movieStream().publish().autoConnect(0);

        Util.sleepSeconds(5);

        movieFlux
                .take(4)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);

        movieFlux
                .take(3)
                .subscribe(Util.subscriber("mike"));


        Util.sleepSeconds(15);
    }

    private static Flux<String> movieStream() {
       return Flux.<String, Integer>generate(
               () -> {
                    log.info("generate begins");
                    return 1;
               },
                (state, fluxSink) -> {
                    var scene = "movie scene " + state;
                    log.info("playing {}", scene);
                    fluxSink.next(scene);
                    return ++state;
                }
               )
               .take(15)
               .delayElements(Duration.ofSeconds(1));
    }
}
