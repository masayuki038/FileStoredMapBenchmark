package net.wrap_trap.collections.fsm.bench;

import java.io.IOException;

import com.sleepycat.je.DatabaseException;

public class Benchmark2 {
    public static void main(String[] args) throws DatabaseException, IOException {

        for (int i = 0; i < 5; i++) {
            ReadWriteBench bench = new FileStoredMapBench(35000, 35000);
            bench.run();
            System.out.println(String.format("write: %s sec", bench.getWriteResult()));
            System.out.println(String.format("read: %s sec", bench.getReadResult()));
        }
    }

}
