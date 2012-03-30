package net.wrap_trap.collections.fsm.bench;

import java.io.IOException;

public abstract class ReadWriteBench {

    private long writeResult;
    private long readResult;

    public ReadWriteBench() {
        super();
    }

    public long getWriteResult() {
        return writeResult;
    }

    public long getReadResult() {
        return readResult;
    }

    public void start(int times) throws IOException {
        prepare(times);
        long startTime = System.currentTimeMillis();
        write(times);
        writeResult = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        read(times);
        readResult = System.currentTimeMillis() - startTime;
        cleanUp();
    }

    abstract protected void cleanUp() throws IOException;

    abstract protected void prepare(int times) throws IOException;

    abstract protected void write(int times) throws IOException;

    abstract protected void read(int times) throws IOException;
}
