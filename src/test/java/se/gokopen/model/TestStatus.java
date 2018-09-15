package se.gokopen.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestStatus {

    @Test
    public void shouldReturnAllPossibleStatus() {
        assertEquals(Status.values().length, (4));
    }

}
