package kr.bananaflavor.onedp.leetcode.weeklycontest.problem831;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.maskPII("LeetCode@LeetCode.com"));
        System.out.println(solution.maskPII("AB@qq.com"));
        System.out.println(solution.maskPII("1(234)567-890"));
        System.out.println(solution.maskPII("86-(10)12345678"));
    }
}

class Solution {
    public String maskPII(String S) {
        String mask = maskEmail(S.toLowerCase());
        if (mask != null) {
            return mask;
        } else {
            return maskPhoneNumber(S);
        }
    }

    private String maskPhoneNumber(String input) {
        String onlyDigits = input.replaceAll("[^0-9]", "");
        char[] charArray = onlyDigits.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = charArray.length - 1; i >= 0; i--) {
            char digit = charArray[i];
            if (result.length() == 4 || result.length() == 8 || result.length() == 12) {
                result.insert(0, '-');
            }
            if (result.length() < 4) {
                result.insert(0, digit);
            } else {
                result.insert(0, '*');
            }
        }
        if (result.length() > 12) {
            result.insert(0, '+');
        }
        return result.toString();
    }

    private String maskEmail(String input) {
        String[] mail = input.split("@", 2);
        if (mail.length < 2) {
            return null;
        }
        String[] split = mail[1].split("\\.", 2);

        List<String> names = new ArrayList<>();
        names.add(mail[0]);
        names.add(split[0]);
        names.add(split[1]);

        for (String name : names) {
            if (name.length() < 2) {
                return null;
            }
            for (char ch : name.toCharArray()) {
                if (!Character.isAlphabetic(ch)) {
                    return null;
                }
            }
        }
        return mail[0].charAt(0) + "*****" + mail[0].charAt(mail[0].length() - 1) + '@' + mail[1];
    }
}
