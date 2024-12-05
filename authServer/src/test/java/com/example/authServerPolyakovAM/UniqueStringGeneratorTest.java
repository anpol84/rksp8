package com.example.authServerPolyakovAM;

import com.example.authServerPolyakovAM.service.UniqueStringGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UniqueStringGeneratorTest {

    private final UniqueStringGenerator generator = new UniqueStringGenerator();

    @Test
    public void testGenerateUniqueString() {
        String input = "testInput";
        String result = generator.generateUniqueString(input);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        String[] parts = result.split("_");
        assertEquals(2, parts.length);
        assertTrue(parts[0].matches("[0-9a-f]+"));
        assertTrue(parts[1].matches("\\d+"));
    }
    @Test
    public void testGenerateUniqueStringWithDifferentInputs() {
        String input1 = "input1";
        String input2 = "input2";
        String result1 = generator.generateUniqueString(input1);
        String result2 = generator.generateUniqueString(input2);
        assertNotEquals(result1, result2);
    }
    @Test
    public void testGenerateUniqueStringWithSameInput() {
        String input = "sameInput";
        String result1 = generator.generateUniqueString(input);
        String result2 = generator.generateUniqueString(input);
        assertNotEquals(result1, result2);
        String hash1 = result1.split("_")[0];
        String hash2 = result2.split("_")[0];
        assertEquals(hash1, hash2);
    }
}
