package net.wrap_trap.collections.fsm.bench;

import java.io.IOException;

import net.wrap_trap.utils.FileStoredMap;

public class FileStoredMapBench extends Bench {

    private FileStoredMap<String> map;

    @Override
    protected void execute(int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= times; i++) {
            sb.append("a");
            map.put(String.valueOf(i), sb.toString());
        }
        try {
            map.close();
        } catch (IOException ignore) {
            System.err.println("failed to close at FileStoredMap");
        }
    }

    @Override
    protected void prepare(int times) {
        TestUtils.deleteDirectoryQuietly("tmp");
        map = new FileStoredMap<String>("tmp", times * 2);
    }

    @Override
    protected void cleanUp() {
        try {
            map.close();
        } catch (IOException ignore) {
            System.err.println("failed to close at FileStoredMap");
        }
    }
}
