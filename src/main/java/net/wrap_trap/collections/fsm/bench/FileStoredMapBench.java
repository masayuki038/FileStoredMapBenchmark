package net.wrap_trap.collections.fsm.bench;

import java.io.IOException;

import net.wrap_trap.collections.fsm.Configuration;
import net.wrap_trap.collections.fsm.FileStoredMap;

public class FileStoredMapBench extends ReadWriteBench {

    private FileStoredMap<String> map;

    public FileStoredMapBench(int entries, int entrySize) {
        super(entries, entrySize);
    }

    @Override
    protected void prepare() throws IOException {
        TestUtils.deleteDirectoryQuietly("tmp");
        Configuration config = new Configuration();
        config.setDirPath("tmp");
        config.setBucketSize(getEntries() * 2);
        map = new FileStoredMap<String>(config);
    }

    @Override
    protected void write() throws IOException {
        String value = getValue();
        for (int i = 1; i <= getEntries(); i++) {
            map.put(String.valueOf(i), value);
        }
    }

    @Override
    protected void read() throws IOException {
        for (int i = 1; i <= getEntries(); i++) {
            String obj = map.get(String.valueOf(i));
            if (obj == null) {
                throw new RuntimeException("obj == null");
            }
        }
    }

    @Override
    protected void cleanUp() throws IOException {
        map.close();
    }
}
