package kr.bananaflavor.onedp.leetcode.weeklycontest.problem852;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    private static final Solution solution = new Solution();

    public static void main(String[] args) {
        log.debug("Output: {}", solution.peakIndexInMountainArray(new int[]{0, 1, 0}));
        log.debug("Output: {}", solution.peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
    }
}

class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int answer = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > A[answer]) {
                answer = i;
            } else if (i > 0 && A[i - 1] > A[i]) {
                break;
            }
        }
        return answer;
    }
}