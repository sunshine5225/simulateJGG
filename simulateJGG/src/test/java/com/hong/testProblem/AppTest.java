package com.hong.testProblem;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testConvertProgram() {
        int[] testInput = {1, 2, 34};
        SimulateJGGForm simulateJGGForm = new SimulateJGGForm(99);
//        String output = simulateJGGForm.convertSingleNum(34);
        String output = simulateJGGForm.convertListNum(testInput);
        System.out.println(output);
    }
}
