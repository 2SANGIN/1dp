package kr.bananaflavor.onedp.leetcode.weeklycontest.problem848;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Main {
    private static final Solution solution = new Solution();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readValue(String param) throws IOException {
        log.debug("Input: {}", param);
        return mapper.readValue(param, new TypeReference<T>() {
        });
    }

    public static void main(String[] args) throws IOException {
        log.debug("Output: {}", solution.shiftingLetters("abc", new int[]{3, 5, 9}));
        log.debug("Output: {}", solution.shiftingLetters("ruu", new int[]{26, 9, 17}));
    }
}

class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        shifts[shifts.length - 1] %= 26;
        for (int i = shifts.length - 2; i >= 0; i--) {
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
        }

        char[] arr = S.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (((arr[i] - 'a') + shifts[i]) % 26 + 'a');
        }
        return new String(arr);
    }
}