package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 도시분할걔획_1647 {

    private static int N, M;
    private static Point[] POINTS;
    private static int[] P;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        POINTS = new Point[M];
        P = new int[N+1];
        Arrays.fill(P, -1);

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            POINTS[i] = new Point(a, b, c);
        }

        Arrays.sort(POINTS, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.value - o2.value;
            }
        });
//        System.out.println("POINTS : " + Arrays.toString(POINTS));

        int count = 0;
        int sum = 0;
        for (Point point : POINTS) {
            if (count >= N - 2) {
                break;
            }

            int a = find(point.from);
            int b = find(point.to);
            if (a != b) {
                union(point.from, point.to);
                sum += point.value;
                count++;
            }
        }
        System.out.println(sum);
    }

    static int find(int a) {
        if (P[a] < 0) {
            return a;
        }

        return P[a] = find(P[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }

        P[a] += P[b];
        P[b] = a;
    }

    static class Point {
        int from, to, value;

        public Point(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "from=" + from +
                    ", to=" + to +
                    ", value=" + value +
                    '}';
        }
    }

}
