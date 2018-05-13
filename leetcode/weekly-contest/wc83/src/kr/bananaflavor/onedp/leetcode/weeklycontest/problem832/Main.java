package kr.bananaflavor.onedp.leetcode.weeklycontest.problem832;

public class Main {
    private static final Solution solution = new Solution();

    public static void main(String[] args) {
    }
}

class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] img = new int[A.length][];

        for (int y = 0; y < A.length; y++) {
            img[y] = new int[A[y].length];

            for (int x = 0; x < A[y].length; x++) {
                img[y][x] = (A[y][A[y].length - x - 1] - 1) * -1; // flip horizontally and invert
            }
        }
        return img;
    }
}
