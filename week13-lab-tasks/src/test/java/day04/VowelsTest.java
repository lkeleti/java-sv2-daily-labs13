package day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VowelsTest {

    @Test
    void countVowels() {
        Vowels vowels = new Vowels();

        assertEquals(2, vowels.countVowels("yesterday").get('e'));
        assertEquals(1, vowels.countVowels("yesterday").get('a'));
        assertEquals(null, vowels.countVowels("yesterday").get('o'));
    }
}