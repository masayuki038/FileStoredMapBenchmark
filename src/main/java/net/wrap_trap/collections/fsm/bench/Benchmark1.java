package net.wrap_trap.collections.fsm.bench;

import net.wrap_trap.utils.FileStoredMap;

public class Benchmark1 {
    public static void main(String[] args) {
        TestUtils.deleteFiles("tmp/bench1");
        //FileStoredMap<String> map = new FileStoredMap<String>("tmp/bench1", 2097152);
        FileStoredMap<String> map = new FileStoredMap<String>("tmp/bench1", 70000);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 35000; i++) {
            sb.append("a");
            map.put(String.valueOf(i), sb.toString());
            if (i % 100 == 0) {
                System.out.println(i);
            }
        }
    }
}
