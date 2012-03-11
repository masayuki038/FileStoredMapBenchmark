package net.wrap_trap.collections.fsm.bench;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public class BerkeleyDbBench extends Bench {

    private Database db;
    private Environment env;

    @Override
    protected void prepare(int times) {
        TestUtils.deleteDirectoryQuietly("tmp");
        File tmpDir = new File("tmp");
        tmpDir.mkdir();
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        env = new Environment(new File("tmp"), envConfig);
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        db = env.openDatabase(null, "sampleDatabase", dbConfig);
    }

    @Override
    protected void cleanUp() {
        db.close();
        env.close();
    }

    @Override
    protected void execute(int times) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= times; i++) {
                sb.append("a");
                db.put(null, new DatabaseEntry(String.valueOf(i).getBytes("UTF-8")),
                       new DatabaseEntry(sb.toString().getBytes("UTF-8")));
            }
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
