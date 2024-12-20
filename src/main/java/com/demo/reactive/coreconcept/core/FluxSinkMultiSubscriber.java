package com.demo.reactive.coreconcept.core;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class FluxSinkMultiSubscriber {
    public static void main(String[] args) {
//        var generator = new NameGenerator();
//        var flux = Flux.create(generator).share();
//        flux.subscribe(Util.subscriber("sub1"));
//        flux.subscribe(Util.subscriber("sub2"));
//
//        // somewhere else!
//        for (int i = 0; i < 10; i++) {
//            generator.generate();
//        }

        Flux<Object> numbers = Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).share();

        numbers.subscribe(System.out::println);
        numbers.subscribe(System.out::println);
    }
}

class NameGenerator implements Consumer<FluxSink<String>> {
    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.sink = stringFluxSink;
    }

    public void generate(){
        this.sink.next(Util.faker().name().firstName());
    }
}

