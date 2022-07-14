package org.sample;

import org.openjdk.jmh.annotations.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
public abstract class PutBenchmark {
    private Map<Integer, Integer> forPut;
    public static final int MAX_KEY = 10000000;
    private final Integer element = 1;

    @Setup(Level.Iteration)
    public void setup() {
        forPut = create();
    }

    @Benchmark
    public Object put() {
        int key = ThreadLocalRandom.current().nextInt(MAX_KEY);
        return forPut.put(key, element);
    }

    private Map<Integer, Integer> create() {
//        return new ConcurrentHashMap<>();
        return Collections.synchronizedMap(new HashMap<>());
    }
}
