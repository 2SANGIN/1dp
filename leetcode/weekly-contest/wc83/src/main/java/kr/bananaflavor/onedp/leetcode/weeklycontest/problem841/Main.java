package kr.bananaflavor.onedp.leetcode.weeklycontest.problem841;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

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
            log.debug("Output: {}", solution.canVisitAllRooms(readValue("[[1],[2],[3],[]]")));
            log.debug("Output: {}", solution.canVisitAllRooms(readValue("[[1,3],[3,0,1],[2],[0]]")));
            log.debug("Output: {}", solution.canVisitAllRooms(readValue("[[1],[2],[],[3]]")));
            log.debug("Output: {}", solution.canVisitAllRooms(readValue("[[2],[],[1]]")));
            log.debug("Output: {}", solution.canVisitAllRooms(readValue("[[],[1,1],[2,2]]")));
            log.debug("Output: {}", solution.canVisitAllRooms(readValue("[[4],[3],[],[2,5,7],[1],[],[8,9],[],[],[6]]")));
        }
    }
}

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        List<Integer> keys = new ArrayList<>();
        keys.add(0);
        Set<Integer> seen = new HashSet<>();

        while (!keys.isEmpty()) {
            final Integer key = keys.remove(0);
            rooms.get(key).stream().distinct().filter(k -> !seen.contains(k)).forEach(keys::add);
            seen.add(key);
        }
        return seen.size() == rooms.size();
    }
}