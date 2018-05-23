package kr.bananaflavor.onedp.leetcode.weeklycontest.problem834;

public class Main {
    private static final Solution solution = new Solution();

    public static void main(String[] args) {
    }
}

class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int width = A[0].length;
        int len = A.length * width;

        int max = 0;
        for (int i = 0; i < len; i++) {
            int cnt = 0;
            for (int j = 0; j < len; j++) {
                int idx = (j + i) % len;
                if (B[idx / width][idx % width] == 1 && A[j / width][j % width] == B[idx / width][idx % width]) {
                    cnt++;
                }
            }
            max = Math.max(cnt, max);
        }
        return max;
    }
}