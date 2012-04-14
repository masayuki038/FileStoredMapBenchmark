package net.wrap_trap.collections.fsm.bench;

import java.io.IOException;

public abstract class ReadWriteBench implements Runnable {

    private int entries;

    public int getEntries() {
        return entries;
    }

    private String value;

    public ReadWriteBench(int entries, int entrySize) {
        super();
        this.entries = entries;
        this.value = createValue("a", entrySize);
    }

    public void run() {
        try {
            prepare();
            long startTime = System.currentTimeMillis();
            write();
            long writeTime = System.currentTimeMillis() - startTime;
            startTime = System.currentTimeMillis();
            read();
            long readTime = System.currentTimeMillis() - startTime;
            System.out.println(String.format("write: %d ms", writeTime));
            System.out.println(String.format("read: %d ms", readTime));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                cleanUp();
            } catch (IOException ignore) {}
        }
    }

    abstract protected void cleanUp() throws IOException;

    abstract protected void prepare() throws IOException;

    abstract protected void write() throws IOException;

    abstract protected void read() throws IOException;

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
