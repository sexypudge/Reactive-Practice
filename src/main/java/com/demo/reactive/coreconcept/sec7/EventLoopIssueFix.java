package com.demo.reactive.coreconcept.sec7;

import com.demo.reactive.coreconcept.core.Util;

public class EventLoopIssueFix {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
//                    .map(EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(20);
    }

    private static String process(String input){
        System.out.println("Processing on thread: " + Thread.currentThread().getName());
//        Util.sleepSeconds(1);
        return input + "-processed";
    }
}
