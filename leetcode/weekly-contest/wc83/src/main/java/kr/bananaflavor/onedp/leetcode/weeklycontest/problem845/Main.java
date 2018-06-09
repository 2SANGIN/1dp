package kr.bananaflavor.onedp.leetcode.weeklycontest.problem845;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

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
        {
            log.debug("Output: {}", solution.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
            log.debug("Output: {}", solution.longestMountain(new int[]{2, 2, 2}));
            log.debug("Output: {}", solution.longestMountain(new int[]{1, 2, 3}));
            log.debug("Output: {}", solution.longestMountain(new int[]{1, 2, 2, 1, 3, 1}));
            log.debug("Output: {}", solution.longestMountain(new int[]{1, 2, 2, 2, 3, 1, 1}));
            log.debug("Output: {}", solution.longestMountain(new int[]{1, 2, 2, 1, 3}));
            log.debug("Output: {}", solution.longestMountain(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
            log.debug("Output: {}", solution.longestMountain(new int[]{2, 3, 3, 2, 0, 2}));
        }
    }
}

class Solution {
    public int longestMountain(int[] A) {
        int sp = 0;
        int maxLen = 0;

        while (sp < A.length) {
            int ep = sp;
            boolean risingEdge = false;
            boolean fallingEdge = false;

            while (ep + 1 < A.length && A[ep] < A[ep + 1]) {
                ep++;
                risingEdge = true;
            }
            while (ep + 1 < A.length && A[ep] > A[ep + 1]) {
                ep++;
                fallingEdge = true;
            }
            if (risingEdge && fallingEdge) {
                maxLen = Math.max(maxLen, ep - sp + 1);
            }
            sp = Math.max(ep, sp + 1);
        }
        return maxLen;
    }
}