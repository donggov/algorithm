package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 특정한최단경로_1504 {

    private static int N, E;
    private static List<Point>[] POINTS;
    private static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        POINTS = new List[N+1];
        for (int i=0; i<N+1; i++) {
            POINTS[i] = new ArrayList<>();
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            POINTS[a].add(new Point(b, c));
            POINTS[b].add(new Point(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long[] value_1 = find(1);
        long[] value_v1 = find(v1);
        long[] value_v2 = find(v2);

        long one_v1 = value_1[v1];
        long v1_v2 = value_v1[v2];
        long v2_n = value_v2[N];
        long one_v2 = value_1[v2];
        long v2_v1 = value_v2[v1];
        long v1_n = value_v1[N];

        long a1 = one_v1 + v1_v2 + v2_n;
        long a2 = one_v2 + v2_v1 + v1_n;
        long answer = Math.min(a1, a2);

        if (answer >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static long[] find(int s) {
        long[] values = new long[N+1];
        Arrays.fill(values, INF);

        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return (int) (o1.value - o2.value);
            }
        });
        queue.add(new Point(s, 0));
        values[s] = 0;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int from = point.to;
            long value = point.value;
            if (value > values[from]) {
                continue;
            }

            for (Point p : POINTS[from]) {
                int to = p.to;
                long newValue = values[from] + p.value;
                if (newValue < values[to]) {
                    values[to] = newValue;
                    queue.add(new Point(to, newValue));
                }
            }
        }

        return values;
    }

    static class Point {
        int to;
        long value;

        public Point(int to, long value) {
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
