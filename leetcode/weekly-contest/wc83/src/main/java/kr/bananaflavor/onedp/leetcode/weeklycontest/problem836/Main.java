package kr.bananaflavor.onedp.leetcode.weeklycontest.problem836;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    private static final Solution solution = new Solution();

    public static void main(String[] args) {
        {
            int[] rec1 = {0, 0, 2, 2};
            int[] rec2 = {1, 1, 3, 3};
            log.debug("{}, {} ---> overlap? ---> {}", rec1, rec2, solution.isRectangleOverlap(rec1, rec2));
        }
        {
            int[] rec1 = {0, 0, 1, 1};
            int[] rec2 = {1, 0, 2, 1};
            log.debug("{}, {} ---> overlap? ---> {}", rec1, rec2, solution.isRectangleOverlap(rec1, rec2));
        }
    }
}

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = Math.max(rec1[0], rec2[0]);
        int y1 = Math.max(rec1[1], rec2[1]);
        int x2 = Math.min(rec1[2], rec2[2]);
        int y2 = Math.min(rec1[3], rec2[3]);
        return x1 < x2 && y1 < y2;
    }
}