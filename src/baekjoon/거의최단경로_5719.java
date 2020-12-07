package baekjoon;

import java.io.*;
import java.util.*;

public class 거의최단경로_5719 {

    static int N, M, S, D;
    static List<Edge>[] ADJ_LIST;
    static final int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            ADJ_LIST = new List[N];
            for (int i=0; i<N; i++) {
                ADJ_LIST[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                ADJ_LIST[a].add(new Edge(b, c));
            }

            Dijkstra dijkstra = dijkstra(S);
            List<Integer>[] prev = dijkstra.prev;

            boolean[] isVisited = new boolean[N];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(D);
            isVisited[D] = true;
            while (!queue.isEmpty()) {
                int from = queue.poll();

                for (int to : prev[from]) {
                    if (!isVisited[to]) {
                        queue.add(to);
                        isVisited[to] = true;
                    }

                    for (int i=0; i<ADJ_LIST[to].size(); i++) {
                        Edge e = ADJ_LIST[to].get(i);
                        if (e.to == from) {
                            ADJ_LIST[to].get(i).isBest = true;
                            break;
                        }
                    }
                }
            }

            Dijkstra dijkstra2 = dijkstra(S);

            int answer = dijkstra2.values[D];
            if (answer >= MAX) {
                bw.write("-1\n");
            } else {
                bw.write(answer + "\n");
            }
            bw.flush();
        }

        bw.close();
    }

    static Dijkstra dijkstra(int s) {
        int[] values = new int[N];
        Arrays.fill(values, MAX);

        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.value, o2.value);
            }
        });
        queue.add(new Edge(s, 0));
        values[s] = 0;

        List<Integer>[] prev = new List[N];
        for (int i=0; i<N; i++) {
            prev[i] = new ArrayList<>();
        }

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int from = edge.to;
            int value = edge.value;

            for (Edge e : ADJ_LIST[from]) {
                if (e.isBest) {
                    continue;
                }

                int to = e.to;
                int newValue = value + e.value;
                if (newValue < values[to]) {
                    queue.add(new Edge(to, newValue));
                    values[to] = newValue;
                    prev[to] = new ArrayList<>();
                    prev[to].add(from);

                } else if (newValue == values[to]) {
                    prev[to].add(from);
                }
            }
        }

        return new Dijkstra(values, prev);
    }

    static class Edge {
        int to, value;
        boolean isBest;

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

    static class Dijkstra {
        int[] values;
        List<Integer>[] prev;

        public Dijkstra(int[] values, List<Integer>[] prev) {
            this.values = values;
            this.prev = prev;
        }
    }

}
