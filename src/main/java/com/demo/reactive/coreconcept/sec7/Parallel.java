package com.demo.reactive.coreconcept.sec7;

import com.demo.reactive.coreconcept.core.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Parallel {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .parallel(3)
                .runOn(Schedulers.parallel())
                .map(Parallel::process)
                .sequential()
                .map(i -> i + "a")
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);

    }

    private static int process(int i) {
        Util.sleepSeconds(1);
        return i * 2;
    }
}
