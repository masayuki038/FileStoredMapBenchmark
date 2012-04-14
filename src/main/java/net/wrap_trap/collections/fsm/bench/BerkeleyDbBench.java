package net.wrap_trap.collections.fsm.bench;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class BerkeleyDbBench extends ReadWriteBench {

    private Database db;
    private Environment env;

    public BerkeleyDbBench(int entries, int entrySize) {
        super(entries, entrySize);
    }

    @Override
    protected void prepare() {
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
    protected void write() {
        try {
            String value = getValue();
            for (int i = 1; i <= getEntries(); i++) {
                db.put(null, new DatabaseEntry(String.valueOf(i).getBytes("UTF-8")),
                       new DatabaseEntry(value.getBytes("UTF-8")));
            }
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void read() {
        try {
            for (int i = 1; i <= getEntries(); i++) {
                OperationStatus status = db.get(null, new DatabaseEntry(String.valueOf(i).getBytes("UTF-8")),
                                                new DatabaseEntry(), LockMode.READ_UNCOMMITTED);
                if (status != OperationStatus.SUCCESS) {
                    throw new RuntimeException("status != OperationStatus.SUCCESS");
                }
            }
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
