package net.wrap_trap.collections.fsm.bench;

import java.io.IOException;

public abstract class ReadWriteBench implements Runnable {

    private long writeResult;
    private long readResult;
    private int times;
    private String value;

    public ReadWriteBench(int times) {
        super();
        this.times = times;
        this.value = createValue("a", times);
    }

    public long getWriteResult() {
        return writeResult;
    }

    public long getReadResult() {
        return readResult;
    }

    public void run() {
        try {
            prepare(times);
            long startTime = System.currentTimeMillis();
            write(times);
            writeResult = System.currentTimeMillis() - startTime;
            startTime = System.currentTimeMillis();
            read(times);
            readResult = System.currentTimeMillis() - startTime;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                cleanUp();
            } catch (IOException ignore) {}
        }
    }

    abstract protected void cleanUp() throws IOException;

    abstract protected void prepare(int times) throws IOException;

    abstract protected void write(int times) throws IOException;

    abstract protected void read(int times) throws IOException;

    protected String getValue() {
        return this.value;
    }

    protected String createValue(String str, int n) {
        String ret = "";
        for (; n > 0; n >>>= 1, str += str) {
            if ((n & 1) > 0) {
                ret += str;
            }
        }
        return ret;
    }
}
