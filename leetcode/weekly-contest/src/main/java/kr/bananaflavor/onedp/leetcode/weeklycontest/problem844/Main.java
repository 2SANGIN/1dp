package kr.bananaflavor.onedp.leetcode.weeklycontest.problem844;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
            log.debug("Output: {}", solution.backspaceCompare("ab#c", "ad#c"));
            log.debug("Output: {}", solution.backspaceCompare("ab##", "c#d#"));
            log.debug("Output: {}", solution.backspaceCompare("a##c", "#a#c"));
            log.debug("Output: {}", solution.backspaceCompare("a#c", "b"));
        }
    }
}

class Solution {
    public boolean backspaceCompare(String S, String T) {
        return getTypedString(S).equals(getTypedString(T));
    }

    private List getTypedString(String origin) {
        LinkedList<Character> buffer = new LinkedList<>();
        for (char c : origin.toCharArray()) {
            if (c != '#') {
                buffer.push(c);
            } else if (!buffer.isEmpty()) {
                buffer.pop();
            }
        }
        System.out.println(buffer.toString());
        return buffer;
    }
}