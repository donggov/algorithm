package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 모든순열_10974 {

    private static int N;
    private static int[] ARRAY;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ARRAY = new int[N];
        for (int i=0; i<N; i++) {
            ARRAY[i] = i+1;
        }

        perm(ARRAY, new int[N], new boolean[N], 0, N, N);

    }

    private static void perm(int[] input, int[] output, boolean[] isVisited, int depth, int n, int r) {
        if (depth == r) {
            print(output);
            return;
        }

        for (int i=0; i<n; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                output[depth] = input[i];
                perm(input, output, isVisited, depth + 1, n, r);
                isVisited[i] = false;
            }
        }
    }

    private static void print(int[] input) {
        for (int i : input) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
