package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 친구비_16562 {

    private static int N, M, K;
    private static int[] P, MONEY;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        P = new int[N+1];
        MONEY = new int[N+1];
        Arrays.fill(P, -1);

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<N+1; i++) {
            MONEY[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
//        System.out.println(Arrays.toString(P));

        boolean isOkay = true;
        int sum = 0;
        for (int i=1; i<N+1; i++) {
            if (P[i] < 0) {
                sum += MONEY[i];
                if (sum > K) {
                    isOkay = false;
                    break;
                }
            }
        }

        if (isOkay) {
            System.out.println(sum);
        } else {
            System.out.println("Oh no");
        }

    }

    private static int find(int n) {
        if (P[n] < 0) {
            return n;
        }

        return P[n] = find(P[n]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        if (MONEY[a] < MONEY[b]) {
            P[a] += P[b];
            P[b] = a;
        } else {
            P[b] += P[a];
            P[a] = b;
        }
    }

}
