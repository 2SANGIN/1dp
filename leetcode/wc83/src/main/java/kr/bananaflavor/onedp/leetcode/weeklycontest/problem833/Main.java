package kr.bananaflavor.onedp.leetcode.weeklycontest.problem833;

public class Main {
    private static final Solution solution = new Solution();

    public static void main(String[] args) {
        solution.findReplaceString("vyeqmeyggv", new int[]{4, 0, 7}, new String[]{"mey", "vye", "ggv"}, new String[]{"bq", "aa", "aqq"});
    }
}

class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        // sort
        for (int i = 0; i < indexes.length - 1; i++) {
            for (int j = i + 1; j < indexes.length; j++) {
                if (indexes[i] > indexes[j]) {
                    int temp = indexes[i];
                    indexes[i] = indexes[j];
                    indexes[j] = temp;

                    String tmpStr = sources[i];
                    sources[i] = sources[j];
                    sources[j] = tmpStr;

                    tmpStr = targets[i];
                    targets[i] = targets[j];
                    targets[j] = tmpStr;
                }
            }
        }

        int increasedIndex = 0;
        for (int i = 0; i < indexes.length; i++) {
            int offset = increasedIndex + indexes[i];
            String originalWord = S.substring(offset, offset + sources[i].length());

            if (originalWord.equals(sources[i])) {
                String leading = offset > 0 ? S.substring(0, offset) : "";
                S = leading + targets[i] + S.substring(offset + sources[i].length());
                increasedIndex = increasedIndex + (targets[i].length() - sources[i].length());
            }
        }
        return S;
    }
}