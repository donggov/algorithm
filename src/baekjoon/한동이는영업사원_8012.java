package baekjoon;

import java.io.*;
import java.util.*;

public class 한동이는영업사원_8012 {

    static int N, M;
    static int[] DEPTH;
    static int[][] PARENT;
    static int MAX_DEPTH = 15; // 30,000 < 2^15 = 32768
    static List<Integer>[] ADJ_LIST;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        DEPTH = new int[N+1];
        PARENT = new int[MAX_DEPTH +1][N+1];

        ADJ_LIST = new List[N+1];
        for (int i=0; i<N+1; i++) {
            ADJ_LIST[i] = new ArrayList<>();
        }

        int a, b;
        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            ADJ_LIST[a].add(b);
            ADJ_LIST[b].add(a);
        }

        depth(1);
        parent();

        M = Integer.parseInt(br.readLine());
        int answer = 0;
        int prev = 1;
        for (int i=0; i<M; i++) {
            int to = Integer.parseInt(br.readLine());
            int lca = lca(prev, to);
            answer += ((DEPTH[prev] - DEPTH[lca]) + (DEPTH[to] - DEPTH[lca]));
            prev = to;
        }

        System.out.println(answer);
    }

    static int lca(int a, int b) {
        if (DEPTH[a] > DEPTH[b]) {
            int tempA = a;
            a = b;
            b = tempA;
        }

        for (int i=MAX_DEPTH; i>=0; i--) {
            if (DEPTH[b] - DEPTH[a] >= (1 << i)) {
                b = PARENT[i][b];
            }
        }

        if (a == b) {
            return a;
        }

        for (int i=MAX_DEPTH; i>=0 ; i--) {
            if (PARENT[i][a] != PARENT[i][b]) {
                a = PARENT[i][a];
                b = PARENT[i][b];
            }
        }

        return PARENT[0][a];
    }

    static void parent() {
        for (int k=1; k< MAX_DEPTH+1; k++) {
            for (int v=1; v<N+1; v++) {
                int pv = PARENT[k-1][v];
                PARENT[k][v] = PARENT[k-1][pv];
            }
        }
    }

    static void depth(int root) {
        boolean[] isVisited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        DEPTH[root] = 0;
        PARENT[0][root] = 0;
        isVisited[root] = true;

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            for (int node : ADJ_LIST[parent]) {
                if (!isVisited[node]) {
                    DEPTH[node] = DEPTH[parent] + 1;
                    PARENT[0][node] = parent;
                    isVisited[node] = true;
                    queue.add(node);
                }
            }
        }
    }

}
