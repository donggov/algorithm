package baekjoon;

import java.io.*;
import java.util.*;

public class 네트워크복구_2211 {

    private static int N, M;
    private static List<Edge>[] ADJ_LIST;
    private static int[] VALUES, PREV;
    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ADJ_LIST = new List[N+1];
        for (int i=0; i<N+1; i++) {
            ADJ_LIST[i] = new ArrayList<>();
        }

        PREV = new int[N+1];
        VALUES = new int[N+1];
        Arrays.fill(VALUES, MAX);

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            ADJ_LIST[a].add(new Edge(b, c));
            ADJ_LIST[b].add(new Edge(a, c));
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.value, o2.value);
            }
        });
        queue.add(new Edge(1, 0));
        VALUES[1] = 0;

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int from = edge.to;
            int value = edge.value;

            for (Edge e : ADJ_LIST[from]) {
                int to = e.to;
                int newValue = value + e.value;
                if (newValue < VALUES[to]) {
                    VALUES[to] = newValue;
                    PREV[to] = from;
                    queue.add(new Edge(to, newValue));
                }
            }
        }

//        System.out.println("VALUES : " + Arrays.toString(VALUES));
//        System.out.println("PREV : " + Arrays.toString(PREV));
        Set<String> answer = new HashSet<>();
        for (int i=2; i<N+1; i++) {
            int to = i;
            while (PREV[to] != 0) {
                answer.add(PREV[to] + " " + to);
                to = PREV[to];
            }
        }

        bw.write(answer.size() + "\n");
        for (String a : answer) {
            bw.write(a + "\n");
        }
        bw.flush();
        bw.close();
    }

    static class Edge {
        int to, value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "to=" + to +
                    ", value=" + value +
                    '}';
        }
    }

}
