package net.wrap_trap.collections.fsm.bench;

import java.io.UnsupportedEncodingException;

import com.sleepycat.je.DatabaseException;

public class Benchmark2 {
    public static void main(String[] args) throws DatabaseException, UnsupportedEncodingException {

        for (int i = 0; i < 5; i++) {
            Bench bench = new FileStoredMapBench();
            bench.start(35000);
            System.out.println("times: " + bench.getResult());
        }
    }

}
