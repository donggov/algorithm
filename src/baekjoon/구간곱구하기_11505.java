package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간곱구하기_11505 {

    private static int N, M, K, LEAF_START;
    private static long[] TREE;
    private static int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        TREE = new long[N*4];
        for (LEAF_START=1; LEAF_START<N; LEAF_START*=2);
//        System.out.println("LEAF_START : " + LEAF_START);

        int n = 0;
        for (int i=LEAF_START; i<TREE.length; i++) {
            if (n < N) {
                TREE[i] = Integer.parseInt(br.readLine());
                n++;
            } else {
                TREE[i] = 1;
            }
        }
//        System.out.println("TREE : " + Arrays.toString(TREE));

        for (int i=LEAF_START-1; i>0; i--) {
            TREE[i] = (TREE[i*2] * TREE[i*2+1]) % MOD;
        }
//        System.out.println("TREE : " + Arrays.toString(TREE));

        for (int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(b, c);
            } else if (a == 2) {
                if (b > c) {
                    int tempB = b;
                    b = c;
                    c = tempB;
                }
                System.out.println(find(b, c));
            }
        }

    }

    private static long find(int left, int right) {
        left += LEAF_START - 1;
        right += LEAF_START - 1;

        long result = 1;
        while (left <= right) {
            if (left % 2 == 1) {
                result = (result * TREE[left]) % MOD;
                left++;
            }
            if (right % 2 == 0) {
                result = (result * TREE[right]) % MOD;
                right--;
            }

            left /= 2;
            right /= 2;
        }
        return result;
    }

    private static void update(int node, long value) {
        node += LEAF_START - 1;
        TREE[node] = value;
        while ((node/=2) > 0) {
            TREE[node] = (TREE[node*2] * TREE[node*2+1]) % MOD;
        }
//        System.out.println("UPDATED : " + Arrays.toString(TREE));
    }

}
