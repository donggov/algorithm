package baekjoon;

import java.io.*;
import java.util.*;

public class 도로네트워크_3176 {

    private static int N, M;
    private static int MAX_DEPTH = 17;
    private static int[] D;
    private static int[][] P, MINS, MAXS;
    private static final int MIN = -987654321;
    private static final int MAX = 987654321;
    private static List<Edge>[] ADJ_LIST;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        D = new int[N+1];
        P = new int[MAX_DEPTH+1][N+1];
        MINS = new int[MAX_DEPTH+1][N+1];
        MAXS = new int[MAX_DEPTH+1][N+1];
        for (int i=0; i<MAX_DEPTH+1; i++) {
            MINS[i][0] = MAX;
            MAXS[i][0] = MIN;
        }

        ADJ_LIST = new List[N+1];
        for (int i=0; i<N+1; i++) {
            ADJ_LIST[i] = new ArrayList<>();
        }

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            ADJ_LIST[a].add(new Edge(b, c));
            ADJ_LIST[b].add(new Edge(a, c));
        }

        depth(1, 0);
        parent();

        M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Node lca = lca(a, b);
            bw.write(lca.min + " " + lca.max + "\n");
            bw.flush();
        }

        bw.close();
    }

    static Node lca(int a, int b) {
        if (D[a] > D[b]) {
            int tempA = a;
            a = b;
            b = tempA;
        }

        int min = MAX;
        int max = MIN;
        for (int i=MAX_DEPTH; i>=0; i--) {
            if (D[b] - D[a] >= (1 << i)) {
                min = Math.min(min, MINS[i][b]);
                max = Math.max(max, MAXS[i][b]);
                b = P[i][b];
            }
        }

        if (a == b) {
            return new Node(min, max);
        }

        for (int i=MAX_DEPTH; i>=0; i--) {
            if (P[i][a] != P[i][b]) {
                min = Math.min(min, Math.min(MINS[i][a], MINS[i][b]));
                max = Math.max(max, Math.max(MAXS[i][a], MAXS[i][b]));
                a = P[i][a];
                b = P[i][b];
            }
        }

        min = Math.min(min, Math.min(MINS[0][a], MINS[0][b]));
        max = Math.max(max, Math.max(MAXS[0][a], MAXS[0][b]));

        return new Node(min, max);
    }

    static void parent() {
        for (int k=1; k<MAX_DEPTH+1; k++) {
            for (int v=1; v<N+1; v++) {
                int pv = P[k-1][v];
                P[k][v] = P[k-1][pv];

                MINS[k][v] = Math.min(MINS[k-1][v], MINS[k-1][pv]);
                MAXS[k][v] = Math.max(MAXS[k-1][v], MAXS[k-1][pv]);
            }
        }
    }

    static void depth(int root, int depth) {
        boolean[] isVisited = new boolean[N+1];
        Queue<Edge> queue = new LinkedList<>();
        queue.add(new Edge(root, 0));
        D[root] = depth;
        P[0][root] = depth;

        isVisited[root] = true;

        while (!queue.isEmpty()) {
            Edge parent = queue.poll();
            int from = parent.to;
            for (Edge edge : ADJ_LIST[from]) {
                int node = edge.to;
                int value = edge.value;
                if (!isVisited[node]) {
                    D[node] = D[from] + 1;
                    P[0][node] = from;
                    MINS[0][node] = value;
                    MAXS[0][node] = value;
                    isVisited[node] = true;
                    queue.add(new Edge(node, value));
                }
            }
        }
    }

    static class Node {
        int min, max;

        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

    static class Edge {
        int to, value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

}
