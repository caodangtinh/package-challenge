package com.tinhcao.packing;

import com.tinhcao.packing.exception.PackingException;
import com.tinhcao.packing.service.PackingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


/**
 * Unit test for Packing
 */
public class PackingTest {
    private List<String> inputLines;
    private List<String> inputLinesInvalid;
    private List<String> outputLines;

    @Before
    public void setup() {
        inputLines = readLinesFromResource("input-test.txt");
        inputLinesInvalid = readLinesFromResource("input-test-invalid.txt");
        outputLines = readLinesFromResource("output-expected.txt");
    }

    @After
    public void teardown() {
        inputLines = null;
        outputLines = null;
    }

    @Test
    public void testResources() {
        assertNotNull(inputLines);
        assertNotNull(outputLines);
        assertEquals(inputLines.size(), outputLines.size());
    }

    @Test
    public void testPacker() {
        for (int i = 0; i < inputLines.size(); i++) {
            try {
                assertEquals(outputLines.get(i), PackingService.compute(inputLines.get(i), i++));
            } catch (PackingException e) {
                fail(e.getMessage());
            }
        }
    }

    @Test(expected = PackingException.class)
    public void testInvalidMaxWeight() {
        for (int i = 0; i < inputLinesInvalid.size(); i++) {
            PackingService.compute(inputLinesInvalid.get(i), ++i);
        }
    }


    private List<String> readLinesFromResource(String filename) {
        ClassLoader loader = PackingTest.class.getClassLoader();
        List<String> lines;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(filename), "UTF-8"));
            lines = br.lines().parallel().map(Object::toString).collect(Collectors.toList());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            lines = null;
        }
        return lines;
    }
}
