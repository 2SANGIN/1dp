package test.kakao.problem3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);

    /*
     * Complete the hackerCards function below.
     */
    static int[] hackerCards(int[] collection, int d) {
        Arrays.sort(collection);
        List<Integer> leannesCards = new ArrayList<>();
        for (int card : collection) {
            leannesCards.add(card);
        }

        List<Integer> giftcards = new ArrayList<>();
        int leftover = d;
        for (int buy = 1; buy <= d; buy++) {
            if (leannesCards.contains(buy)) {
                leannesCards.remove((Object) buy);
                continue;
            }

            if (leftover - buy >= 0) {
                leftover -= buy;
                giftcards.add(buy);
            }
            else {
                break;
            }
        }

        int[] gift = new int[giftcards.size()];
        for (int i = 0; i < giftcards.size(); i++) {
            gift[i] = giftcards.get(i);
        }
        return gift;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int collectionCount = scanner.nextInt();

        int[] collection = new int[collectionCount];

        for (int collectionItr = 0; collectionItr < collectionCount; collectionItr++) {
            int collectionItem = scanner.nextInt();
            collection[collectionItr] = collectionItem;
        }

        int d = scanner.nextInt();

        int[] res = hackerCards(collection, d);

        for (int resItr = 0; resItr < res.length; resItr++) {
            bufferedWriter.write(String.valueOf(res[resItr]));

            if (resItr != res.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
