package baekjoon;

import java.io.*;
import java.util.*;

public class 파티_1238 {

    private static int N, M, X;
    private static List<Point>[] POINTS_01;
    private static List<Point>[] POINTS_02;
    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        POINTS_01 = new List[N+1];
        POINTS_02 = new List[N+1];
        for (int i=0; i<N+1; i++) {
            POINTS_01[i] = new ArrayList<>();
            POINTS_02[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            POINTS_01[from].add(new Point(to, value));
            POINTS_02[to].add(new Point(from, value));
        }

        int[] values1 = dijkstra(X, POINTS_01);
        int[] values2 = dijkstra(X, POINTS_02);
//        System.out.println(Arrays.toString(values1));
//        System.out.println(Arrays.toString(values2));

        int answer = 0;
        for (int i=1; i<N+1; i++) {
            answer = Math.max(answer, values1[i] + values2[i]);
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    static int[] dijkstra(int start, List<Point>[] points) {
        int[] values = new int[N+1];
        int[] prevs = new int[N+1];
        for (int i=0; i<N+1; i++) {
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

            for (Point p2 : points[from]) {
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
