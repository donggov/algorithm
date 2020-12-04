package baekjoon;

import java.io.*;
import java.util.*;

public class 도로포장_1162 {

    private static int N, M, K;
    private static List<Edge>[] ADJ_LIST;
    private static long[][] VALUES;
    private static long MAX = 987654321987654321L;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ADJ_LIST = new List[N+1];
        for (int i=0; i<N+1; i++) {
            ADJ_LIST[i] = new ArrayList<>();
        }

        VALUES = new long[N+1][K+2];
        for (int i=0; i<N+1; i++) {
            for (int k=0; k<K+2; k++) {
                VALUES[i][k] = MAX;
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ADJ_LIST[a].add(new Edge(b, c, 0));
            ADJ_LIST[b].add(new Edge(a, c, 0));
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Long.compare(o1.value, o2.value);
            }
        });
        queue.add(new Edge(1, 0, 0));
        VALUES[1][0] = 0;

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int from = edge.to;
            long value = edge.value;
            int k = edge.k;

            if (value > VALUES[from][k]) {
                continue;
            }

            for (Edge e : ADJ_LIST[from]) {
                int to = e.to;

                if (k+1 <= K) {
                    if (value < VALUES[to][k+1]) {
                        VALUES[to][k+1] = VALUES[from][k];
                        queue.add(new Edge(to, VALUES[from][k], k+1));
                    }
                }

                long newValue = VALUES[from][k] + e.value;
                if (newValue < VALUES[to][k]) {
                    VALUES[to][k] = newValue;
                    queue.add(new Edge(to, newValue, k));
                }
            }
        }

//        for (int i=0; i<N+1; i++) {
//            System.out.println(Arrays.toString(VALUES[i]));
//        }

        long answer = MAX;
        for (int i=0; i<K+1; i++) {
            answer = Math.min(answer, VALUES[N][i]);
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    static class Edge {
        int to;
        long value;
        int k;

        public Edge(int to, long value, int k) {
            this.to = to;
            this.value = value;
            this.k = k;
        }
    }

}
