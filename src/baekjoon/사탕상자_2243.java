package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 사탕상자_2243 {

    private static int N, LEAF_START;
    private static long[] TREE;
    private static int MAX = 1000000;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine().trim());
        TREE = new long[MAX * 4];

        for (LEAF_START=1; LEAF_START<MAX; LEAF_START*=2);

        int a, b, c;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            a = Integer.parseInt(st.nextToken());

            if (a == 2) {
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                add(b, c);

            } else {
                b = Integer.parseInt(st.nextToken());
                int f = find(b) - LEAF_START + 1;
                bw.write(f + "\n");

                add(f, -1);
            }
        }

        bw.flush();
        bw.close();
    }

    private static int find(long value) {
        int index = 1;
        while (index < LEAF_START) {
//            if (TREE[index*2] >= value) {
//                index *= 2;
//            } else {
//                index = index*2 + 1;
//                value -= TREE[index*2];
//            }

            long left = TREE[index*2];
            if (left >= value) {
                index *= 2;
            } else {
                index = index*2 + 1;
                value -= left;
            }
        }

        return index;
    }

    private static void add(int index, long value) {
        index += LEAF_START - 1;
        TREE[index] += value;

        while ((index/=2) > 0) {
            TREE[index] = TREE[index*2] + TREE[index*2+1];
        }
//        System.out.println("ADDED : " + Arrays.toString(TREE));
    }

}
