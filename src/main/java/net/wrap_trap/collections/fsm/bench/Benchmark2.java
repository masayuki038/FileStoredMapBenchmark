package net.wrap_trap.collections.fsm.bench;

import java.util.HashMap;
import java.util.Map;

public class Benchmark2 {
    public static void main(String[] args) {
        TestUtils.deleteFiles("tmp/bench1");
        Map<String, String> map = new HashMap<String, String>(2936012);
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
