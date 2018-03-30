package io.github.atmost1815.leetcode.splitarraywithsameaverage;

import java.util.LinkedList;
import java.util.List;

public class Solution {
  public boolean splitArraySameAverage(final int[] A) {
    if (A.length == 1) {
      return false;
    } else if (A.length == 2) { return A[0] == A[1]; }

    // 각각 A[i]에 대해서 B, C에 넣었을 때의 결과를 따져본다
    final List<Integer> B = new LinkedList<>();
    final List<Integer> C = new LinkedList<>();
    B.add(A[0]);
    for (int i = 1; i < A.length; i++) {

    }

    return false;
  }
}
