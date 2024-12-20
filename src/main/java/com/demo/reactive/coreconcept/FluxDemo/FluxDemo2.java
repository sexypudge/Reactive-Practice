package com.demo.reactive.coreconcept.FluxDemo;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FluxDemo2 {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        flux.subscribe(
                i -> System.out.println("Received : " + i),
                err -> System.out.println("Error : " + err),
                () -> System.out.println("Completed")
        );

    }

    public void FluxWithArray() {
        String[] arr = {"Hi", "Hello", "How are you"};

        Flux.fromArray(arr)
                .filter(s -> s.length() > 2)
                .subscribe(i -> System.out.println("Received : " + i));

        //Output
        //Received : Hello
        //Received : How are you
    }

    public void FluxWithIterable() {
        Flux.fromIterable(Arrays.asList("vins", "guru"))
                .map(String::toUpperCase);
    }

    public void FluxWithRange() {
        Flux.range(1, 10)
                .map(i -> i * 2)
                .subscribe(i -> System.out.println("Received : " + i));
    }

    public void FluxWithStream() {
        Flux<String> stringFlux = Flux.fromStream(Stream.of("vins", "guru"))
                .map(String::toUpperCase);

        //observer-1
        stringFlux
                .map(String::length)
                .subscribe(i -> System.out.println("Observer-1 :: " + i));
        //observer-2
        stringFlux
                .subscribe(i -> System.out.println("Observer-2 :: " + i));

        // Be careful with Streams!! Flux can have more than 1 observer.
        // But below code will throw error saying that the stream has been closed.

        //The above problem can be fixed by using Supplier<Stream>
        List<String> list = Arrays.asList("vins", "guru");
        Flux.fromStream(list::stream)
                .map(String::toUpperCase);

    }
}
