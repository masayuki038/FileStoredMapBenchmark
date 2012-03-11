package net.wrap_trap.collections.fsm.bench;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Benchmark1 {
    @Option(name = "-h", aliases = "--help", usage = "print usage message and exit")
    private boolean usage;

    @Option(required = true, name = "-c", aliases = "--container", usage = "target the container to bench. 1:FileStroedHash, 2:BerkeleyDB(JE)")
    private int target;

    @Option(name = "-l", aliases = "--loop", usage = "loop count")
    private int loop = 5;

    @Option(name = "-e", aliases = "--entries", usage = "an amount of entries putting the container")
    private int entries = 30000;

    public static void main(String[] args) throws CmdLineException {
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

        Bench bench = null;
        switch (b.target) {
        case 1:
            bench = new FileStoredMapBench();
            break;
        case 2:
            bench = new BerkeleyDbBench();
            break;
        default:
            parser.printUsage(System.err);
            return;
        }

        for (int i = 0; i < b.loop; i++) {
            bench.start(b.entries);
            System.out.println("times: " + bench.getResult());
        }
    }
}
