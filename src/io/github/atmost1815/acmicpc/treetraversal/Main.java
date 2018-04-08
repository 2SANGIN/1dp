package io.github.atmost1815.acmicpc.treetraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(final String[] args) {
        char[][] tree = new char[sc.nextInt() + 'A'][2];
        for (int i = 'A'; i < tree.length; i++) {
            char node = sc.next().charAt(0);
            tree[node][0] = sc.next().charAt(0);
            tree[node][1] = sc.next().charAt(0);
        }

        StringBuilder sb = new StringBuilder();

        Stack<Character> buf = new Stack<Character>() {
            @Override
            public Character push(Character item) {
                if (item != '.')
                    return super.push(item);
                return item;
            }

            @Override
            public synchronized boolean add(Character character) {
                if (character != '.')
                    return super.add(character);
                return false;
            }
        };

        // pre-order
        char node = 'A';
        while (true) {
            sb.append(node);
            buf.push(tree[node][1]);
            buf.push(tree[node][0]);

            if (buf.empty()) break;
            node = buf.pop();
        }
        sb.append('\n');

        // in-order
        node = 'A';
        while (true) {
            while (node != '.') {
                buf.push(node);
                node = tree[node][0];
            }

            if (buf.empty()) break;
            node = buf.pop();
            sb.append(node);
            node = tree[node][1];
        }
        sb.append('\n');

        // post-order
        node = 'A';
        int idx = sb.length();
        while (true) {
            while (node != '.') {
                sb.insert(idx, node);
                buf.push(tree[node][0]);
                node = tree[node][1];
            }

            if (buf.empty()) break;
            node = buf.pop();
        }

        System.out.println(sb);
    }

    static class Scanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        Scanner(final InputStream is) {
            this.reader = new BufferedReader(new InputStreamReader(is));
        }

        String next() {
            if (this.tokenizer == null || !this.tokenizer.hasMoreTokens()) {
                try {
                    this.tokenizer = new StringTokenizer(this.reader.readLine());
                } catch (final IOException e) {
                    throw new NoSuchElementException(e.getMessage());
                }
            }
            return this.tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

