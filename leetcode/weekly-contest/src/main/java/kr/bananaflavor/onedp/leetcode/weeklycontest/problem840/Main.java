package kr.bananaflavor.onedp.leetcode.weeklycontest.problem840;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
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
        log.debug("Output: {}", solution.numMagicSquaresInside(new int[][]{{4, 3, 8, 4}, {9, 5, 1, 9}, {2, 7, 6, 2}}));
        log.debug("Output: {}", solution.numMagicSquaresInside(new int[][]{{4, 1, 2}, {7, 9, 3}, {8, 6, 5}}));
    }
}

class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;
        for (int r = 1; r < grid.length - 1; r++) {
            for (int c = 1; c < grid[0].length - 1; c++) {
                if (isMagicSquare(getSubgrid(grid, r, c))) {
                    count++;
                }
            }
        }
        return count;
    }

    private int[] getSubgrid(int[][] grid, int cy, int cx) {
        if ((cx < 1) || (((grid[0].length - 1) <= cx) && (cy < 1)) || ((grid.length - 1) <= cy)) {
            return null;
        }
        int[] serialize = new int[9];
        int i = 0;
        for (int y = cy - 1; y <= cy + 1; y++) {
            for (int x = cx - 1; x <= cx + 1; x++) {
                serialize[i++] = grid[y][x];
            }
        }
        return serialize;
    }

    private boolean isMagicSquare(int[] subgrid) {
        if (IntStream.of(subgrid).distinct().count() < 9) {
            System.out.println("sub-grid has duplicated number");
            return false;
        }

        if (IntStream.of(subgrid).anyMatch(num -> num < 1 || 9 < num)) {
            System.out.println("sub-grid contains number out of range from 1 to 9");
            return false;
        }

        /*
         * indexes of arrays
         * 0 1 2
         * 3 4 5
         * 6 7 8
         */
        int[] sum = new int[8];
        for (int i = 0; i < 9; i++) {
            sum[i / 3] += subgrid[i]; // rows
            sum[i % 3 + 3] += subgrid[i]; // columns
            if (i % 4 == 0) {
                sum[6] += subgrid[i]; // diagonal \
            }
            if (i == 2 || i == 4 || i == 6) {
                sum[7] += subgrid[i]; // diagonal /
            }
        }
        Arrays.stream(sum).mapToObj(eachSum -> eachSum + ", ").forEach(System.out::print);
        System.out.println();
        if (IntStream.of(sum).anyMatch(eachSum -> eachSum != sum[0])) {
            return false;
        }
        return true;
    }
}