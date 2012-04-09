package net.wrap_trap.collections.fsm.bench;

import java.io.IOException;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import bb.util.Benchmark;
import bb.util.Benchmark.Params;

public class Benchmark1 extends Benchmark {
    @Option(name = "-h", aliases = "--help", usage = "print usage message and exit")
    private boolean usage;

    @Option(required = true, name = "-c", aliases = "--container", usage = "target the container to bench. 1:FileStroedHash, 2:BerkeleyDB(JE)")
    private int target;

    @Option(name = "-l", aliases = "--loop", usage = "loop count")
    private int loop = 5;

    @Option(name = "-e", aliases = "--entries", usage = "an amount of entries putting the container")
    private int entries = 30000;

    public static void main(String[] args) throws CmdLineException, IOException {
        Benchmark1 b = new Benchmark1();
        CmdLineParser parser = new CmdLineParser(b);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            parser.printUsage(System.err);
            return;
        }

        if (b.usage) {
            parser.printUsage(System.out);
            return;
        }

        ReadWriteBench task = null;
        switch (b.target) {
        case 1:
            task = new FileStoredMapBench(b.entries);
            break;
        case 2:
            task = new BerkeleyDbBench(b.entries);
            break;
        default:
            parser.printUsage(System.err);
            return;
        }

        Params params = new Params();
        params.setManyExecutions(true);
        params.setNumberMeasurements(b.loop);
        params.setMeasureCpuTime(false);

        Benchmark bb = new Benchmark(task, params);
        System.out.println(bb.toStringFull());
    }
}
