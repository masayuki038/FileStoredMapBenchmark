package net.wrap_trap.collections.fsm.bench;

import java.io.IOException;

import net.wrap_trap.collections.fsm.Configuration;
import net.wrap_trap.collections.fsm.FileStoredMap;

public class FileStoredMapBench extends ReadWriteBench {

    private FileStoredMap<String> map;

    @Override
    protected void write(int times) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= times; i++) {
            sb.append("a");
            map.put(String.valueOf(i), sb.toString());
        }
    }

    @Override
    protected void read(int times) throws IOException {
        for (int i = 1; i <= times; i++) {
            String obj = map.get(String.valueOf(i));
            if (obj == null) {
                throw new RuntimeException("obj == null");
            }
        }
    }

    @Override
    protected void prepare(int times) throws IOException {
        TestUtils.deleteDirectoryQuietly("tmp");
        Configuration config = new Configuration();
        config.setDirPath("tmp");
        config.setBucketSize(times * 2);
        map = new FileStoredMap<String>(config);
    }

    @Override
    protected void cleanUp() throws IOException {
        map.close();
    }
}
