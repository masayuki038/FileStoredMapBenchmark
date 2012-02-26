package net.wrap_trap.collections.fsm.bench;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TestUtils {

    public static void deleteFiles(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException ignore) {}
    }

}
