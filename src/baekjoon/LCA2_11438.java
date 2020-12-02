package baekjoon;

import java.io.*;
import java.util.*;

public class LCA2_11438 {

    private static int N, M;
    private static int MAX_DEPTH = 17; // 2^16=65,536 < 100,000 < 2^17=131,072
    private static int[] D;
    private static int[][] P;
    private static List<Integer>[] ADJ_LIST;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        D = new int[N+1];
        P = new int[MAX_DEPTH+1][N+1];
        ADJ_LIST = new List[N+1];
        for (int i=0; i<N+1; i++) {
            ADJ_LIST[i] = new ArrayList<>();
        }

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ADJ_LIST[a].add(b);
            ADJ_LIST[b].add(a);
        }

        // depth
        makeDepth(1, 0);

        // parent
        makeParent();

//        for (int i=0; i<N+1; i++) {
//            System.out.println(Arrays.toString(D));
//        }

        M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(lca(a, b) + "\n");
            bw.flush();
        }

        bw.close();
    }

    static int lca(int a, int b) {
        if (D[a] > D[b]) {
            int tempA = a;
            a = b;
            b = tempA;
        }

        for (int i=MAX_DEPTH; i>=0; i--) {
            if (D[b] - D[a] >= (1 << i)) {
                b = P[i][b];
            }
        }

        if (a == b) {
            return a;
        }

        for (int i=MAX_DEPTH; i>=0; i--) {
            if (P[i][a] != P[i][b]) {
                a = P[i][a];
                b = P[i][b];
            }
        }

        return P[0][a];
    }

    static void makeParent() {
        for (int k=1; k<MAX_DEPTH+1; k++) {
            for (int v=1; v<N+1; v++) {
                int pv = P[k-1][v];
                P[k][v] = P[k-1][pv];
            }
        }
    }

    static void makeDepth(int root, int depth) {
        boolean[] isVisited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        D[root] = depth;
        P[0][root] = depth;
        isVisited[root] = true;

        while (!queue.isEmpty()) {
            int parent = queue.poll();

            for (int node : ADJ_LIST[parent]) {
                if (!isVisited[node]) {
                    D[node] = D[parent] + 1;
                    P[0][node] = parent;
                    isVisited[node] = true;
                    queue.add(node);
                }
            }
        }
    }

}
