package com.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

public class Example {

    @Test
    public void stream() {
        Stream<Integer> stream = Stream.of(1)
                                        .map( i -> {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            return i*i;
                                        });

        stream.forEach(System.out::println);
    }

    @Test
    public void mono() {
        // Publisher
        Mono<Integer> mono = Mono.just(1);

        // Subscriber
        mono.subscribe(i -> System.out.println("Received: " + i));
    }

    @Test
    public void myFirstMonoSubscribe() {
        // Publisher
        Mono<Double> transaction = Mono.just(19000.00); // operation

        // Subscriber
        transaction.subscribe(
          credit -> System.out.println("Credit to account"),  // for every operation do this too
          error -> System.out.println("Bank will get back to you in 2-5 business days"), // do this when operation encounters an error
          () -> System.out.println("Send SMS") // do this when operation is complete
        );
    }
}
