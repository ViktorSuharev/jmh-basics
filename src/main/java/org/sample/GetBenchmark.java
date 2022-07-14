package org.sample;

import org.openjdk.jmh.annotations.*;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@State(Scope.Benchmark)
public class GetBenchmark {
    private final Map<Integer, Integer> forGet;
    private final Integer element = 1;

    public GetBenchmark() {
        forGet = create();
        Random random = new Random();
        final int maxKey = 10000;
        for (int i = 0; i < 1000; i++) {
            forGet.put(random.nextInt(maxKey), element);
        }
        forGet.put(100, element);
    }

    @Benchmark
    @Threads(24)
    public Object testMethod() {
        return forGet.get(100);
    }

    private Map<Integer, Integer> create() {
        return new ConcurrentHashMap<>();
//        return Collections.synchronizedMap(new HashMap<>());
    }
}
