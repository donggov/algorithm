package baekjoon;

import java.io.*;
import java.util.*;

public class 최단경로_1753 {

    private static int V, E, K;
    private static List<Point>[] POINTS;
    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        POINTS = new List[V+1];
        for (int i=0; i<V+1; i++) {
            POINTS[i] = new ArrayList<>();
        }

        K = Integer.parseInt(br.readLine().trim());

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            POINTS[from].add(new Point(to, value));
        }

        int[] values = dijkstra(K);

        for (int i=1; i<V+1; i++) {
            if (values[i] == MAX) {
                bw.write("INF" + "\n");
            } else {
                bw.write(values[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static int[] dijkstra(int start) {
        int[] values = new int[V+1];
        int[] prevs = new int[V+1];
        for (int i=0; i<V+1; i++) {
            values[i] = MAX;
            prevs[i] = -1;
        }

        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.value - o2.value;
            }
        });
        queue.add(new Point(start, 0));
        values[start] = 0;

        while (!queue.isEmpty()) {
            Point p1 = queue.poll();
            int from = p1.to;
            int value = p1.value;

            // values[from] 값이 from을 넣을 당시 경로보다 더 짧다면 from 무시
            if (value > values[from]) {
                continue;
            }

            for (Point p2 : POINTS[from]) {
                int to = p2.to;
                int newValue = value + p2.value;
                if (newValue < values[to]) {
                    values[to] = newValue;
                    prevs[to] = from;
                    queue.add(new Point(to, newValue));
                }
            }
        }

//        System.out.println("v : " + Arrays.toString(values));
//        System.out.println("prev : " + Arrays.toString(prevs));
//
//        int curr = 4;
//        while (curr > 0) {
//            System.out.println("> " + curr);
//            curr = prevs[curr];
//        }
        
        return values;
    }

    static class Point {
        int to, value;

        public Point(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "to=" + to +
                    ", value=" + value +
                    '}';
        }
    }

}
