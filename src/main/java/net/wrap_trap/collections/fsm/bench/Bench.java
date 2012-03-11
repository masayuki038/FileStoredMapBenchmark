package net.wrap_trap.collections.fsm.bench;

public abstract class Bench {

    private long result;

    public Bench() {
        super();
    }

    public long getResult() {
        return result;
    }

    public void start(int times) {
        prepare(times);
        long startTime = System.currentTimeMillis();
        execute(times);
        result = System.currentTimeMillis() - startTime;
        cleanUp();
    }

    abstract protected void cleanUp();

    abstract protected void prepare(int times);

    abstract protected void execute(int times);
}
