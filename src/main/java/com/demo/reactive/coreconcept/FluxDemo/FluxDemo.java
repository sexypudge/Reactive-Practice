package com.demo.reactive.coreconcept.FluxDemo;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class FluxDemo {
    public static void main(String[] args) throws InterruptedException, IllegalArgumentException{
        Flux.empty()
                .subscribe(i -> System.out.println("Received : " + i));

        // Subscribe method accepts a Consumer<T> where we define what we do with the emitted element.
        Flux.just(1)
                .subscribe(i -> System.out.println("Received : " + i));

//      Unlike stream, Flux can have any number of Observers connected to the pipeline.
//      We can also write like this if we need to connect more than 1 observers to a source.
        Flux<Integer> flux = Flux.just(1);
        //Observer 1
        flux.subscribe(i -> System.out.println("Observer-1 : " + i));
        //Observer 2
        flux.subscribe(i -> System.out.println("Observer-2 : " + i));


        // We can have multiple observer and each observer will the process the emitted elements independently.
        // They might take their own time. Everything happens asynchronously
        Flux<Character> fluxChar = Flux.just('a', 'b', 'c', 'd')
                .log() // log
                .delayElements(Duration.ofSeconds(1)); //flux emits one element per second

        fluxChar.map(Character::toUpperCase)
                .subscribe( i -> {
                    try {
                        sleep(500); //Observer 1 - takes 500ms to process each element
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Observer-1 : " + i);
        });

        //Observer 2 - process immediately all elements
        fluxChar.subscribe(i -> System.out.println("Observer-2 : " + i));

        //Just to block the execution - otherwise the program will end only with start and end messages
        Thread.sleep(10000); // Keep main thread to be active to be able print out all results
    }
}
