package net.wrap_trap.collections.fsm.bench;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReadWriteBenchTest {

    @Test
    public void testCreateValue() {
        FileStoredMapBench bench = new FileStoredMapBench(5);
        assertThat(bench.createValue("a", 5), is("aaaaa"));
        assertThat(bench.createValue("a", 10), is("aaaaaaaaaa"));
    }
}
