package kr.bananaflavor.onedp.leetcode.weeklycontest.problem837;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    private static final Solution solution = new Solution();

    public static void main(String[] args) {
        {
            String dominoes = ".L.R...LR..L..";
            log.debug("{} ---> pushed? ---> {}", dominoes, solution.pushDominoes(dominoes));
        }
        {
            String dominoes = "RR.L";
            log.debug("{} ---> pushed? ---> {}", dominoes, solution.pushDominoes(dominoes));
        }
    }
}

class Solution {
    public String pushDominoes(String dominoes) {
        char[] fallenDominoes = dominoes.toCharArray();

        List<Integer> dirs = new ArrayList<>();
        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) != '.') {
                dirs.add(i); // enqueue
            }
        }

        if (dirs.isEmpty()) {
            return dominoes;
        }

        int left = -1;
        int right = dirs.remove(0); // deque
        for (int i = 0; i < fallenDominoes.length; i++) {
            if (dominoes.charAt(i) != '.') {
                left = right;
                if (!dirs.isEmpty()) {
                    right = dirs.remove(0);
                } else {
                    right = -1;
                }
            } else {
                if (left == -1) {
                    char rightCh = dominoes.charAt(right);
                    fallenDominoes[i] = rightCh == 'L' ? 'L' : '.';
                } else if (right == -1) {
                    char leftCh = dominoes.charAt(left);
                    fallenDominoes[i] = leftCh == 'R' ? 'R' : '.';
                } else if (dominoes.charAt(left) == dominoes.charAt(right)) {
                    fallenDominoes[i] = dominoes.charAt(left);
                } else {
                    int leftDistance = i - left;
                    int rightDistance = right - i;

                    if (leftDistance < rightDistance) {
                        char leftCh = dominoes.charAt(left);
                        fallenDominoes[i] = leftCh == 'R' ? 'R' : '.';
                    } else if (rightDistance < leftDistance) {
                        char rightCh = dominoes.charAt(right);
                        fallenDominoes[i] = rightCh == 'L' ? 'L' : '.';
                    }
                }
            }
        }
        return new String(fallenDominoes);
    }
}