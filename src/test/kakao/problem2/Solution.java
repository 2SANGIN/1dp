package test.kakao.problem2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Solution {
    private static final Scanner scan = new Scanner(System.in);


    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
        filename = scan.nextLine();

        int count = 0;
        long sum = 0L;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(" ");
                int numOfBytes = Integer.parseInt(strings[strings.length - 1]);
                if (numOfBytes > 5000) {
                    count++;
                    sum += numOfBytes;
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bytes_" + filename))) {
            writer.write(Integer.toString(count));
            writer.newLine();
            writer.write(Long.toString(sum));
            writer.flush();
        }
    }
}
