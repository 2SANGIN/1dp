package kr.bananaflavor.onedp.leetcode.weeklycontest.problem849;

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
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{0, 0, 0, 1, 0, 1, 0, 1}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{1, 0, 0, 0}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{0, 1, 0, 0, 0}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{0, 0, 0, 1, 0}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{0, 0, 0, 1, 0, 0, 0, 0}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{1, 0}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{0, 1}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{1, 0, 0, 1, 0, 0}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{0, 0, 1, 0, 0, 1}));
        log.debug("Output: {}", solution.maxDistToClosest(new int[]{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1}));
    }
}

class Solution {
    public int maxDistToClosest(int[] seats) {
        int answer = 0;
        int left = 0, right = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                right = i;
            } else if (seats[i] == 1) {
                if (left == 0 || right == seats.length - 1) {
                    answer = Math.max(answer, right - left + 1);
                } else {
                    answer = Math.max(answer, (right - left) / 2 + 1);
                }
                left = i + 1;
            }
        }
        if (right == seats.length - 1) {
            answer = Math.max(answer, right - left + 1);
        } else {
            answer = Math.max(answer, (right - left) / 2 + 1);
        }
        return answer;
    }
}