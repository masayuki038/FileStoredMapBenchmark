package net.wrap_trap.collections.fsm.bench;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TestUtils {

    public static void deleteDirectoryQuietly(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException ignore) {
            System.err.println("failed to remove " + path + ".");
            System.err.println(ignore.getMessage());
        }
    }
}
