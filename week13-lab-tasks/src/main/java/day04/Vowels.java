package day04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vowels {
    private static final List<Character> vowelList = Arrays.asList('a','e','i','o','u','A','E','I','O','U');

    public Map<Character, Integer> countVowels(String text) {
        Map<Character, Integer> result = new HashMap<>();
        for (Character c : text.toCharArray()) {
            if (vowelList.contains(c)) {
                if (result.containsKey(c)) {
                    result.put(c,result.get(c)+1);
                }
                else {
                    result.put(c,1);
                }
            }
        }
        return result;
    }
}
