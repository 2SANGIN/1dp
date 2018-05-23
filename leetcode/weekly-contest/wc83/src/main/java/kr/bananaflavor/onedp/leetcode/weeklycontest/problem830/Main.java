package kr.bananaflavor.onedp.leetcode.weeklycontest.problem830;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.largeGroupPositions("aaa"));
    }
}

class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> counts = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            char next = i + 1 < S.length() ? S.charAt(i + 1) : ' ';

            if (ch != next) {
                if ((i - start + 1) >= 3) {
                    List<Integer> range = new ArrayList<>();
                    range.add(start);
                    range.add(i);
                    counts.add(range);
                }
                start = i + 1;
            }
        }

        return counts;
    }
}
