package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 커피숍_1275 {

    private static int N, Q;
    private static long[] INPUTS;
    private static long[] TREE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        INPUTS = new long[N+1];
        TREE = new long[N*4];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            INPUTS[i+1] = Long.parseLong(st.nextToken());
        }

        makeTree(1, 1, N);
//        System.out.println(Arrays.toString(TREE));

        for (int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (x > y) {
                int temp = y;
                y = x;
                x = temp;
            }
            System.out.println(find(1, 1, N, x, y));
            update(1, 1, N, a ,b);
        }

    }

    private static long update(int node, int s, int e, int index, long value) {
        if (index < s || index > e) {
            return TREE[node];
        }
        if (s == e) {
            return TREE[node] = value;
        }

        int m = (s+e)/2;
        long a = update(node*2, s, m, index, value);
        long b = update(node*2+1, m+1, e, index, value);
        return TREE[node] = a + b;
    }

    private static long find(int node, int s, int e, int left, int right) {
        if (right < s || left > e) {
            return 0;
        }
        if (left <= s && right >= e) {
            return TREE[node];
        }

        int m = (s+e)/2;
        long a = find(node*2, s, m, left, right);
        long b = find(node*2+1, m+1, e, left, right);
        return a + b;
    }

    private static long makeTree(int node, int s, int e) {
        if (s == e) {
            return TREE[node] = INPUTS[s];
        }

        int m = (s+e)/2;
        long a = makeTree(node*2, s, m);
        long b = makeTree(node*2+1, m+1, e);
        return TREE[node] = a + b;
    }

}
