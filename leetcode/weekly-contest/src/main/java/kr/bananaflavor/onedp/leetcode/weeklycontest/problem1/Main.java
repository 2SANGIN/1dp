package kr.bananaflavor.onedp.leetcode.weeklycontest.problem1;

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
            log.debug("Output: {}", solution.twoSum(new int[]{2, 7, 11, 15}, 9));
        }
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target && target > 0) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] >= target && target > 0) continue;

                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}