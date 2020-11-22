package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DarkRoads_6497 {

    private static int M, N;
    private static Point[] POINTS;
    private static int[] P;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            P = new int[M];
            Arrays.fill(P, -1);
            POINTS = new Point[N];

            int total = 0;
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                POINTS[i] = new Point(x, y, z);
                total += z;
            }

            Arrays.sort(POINTS, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.z - o2.z;
                }
            });
//            System.out.println(Arrays.toString(POINTS));

            int answer = 0;
            int count = 0;
            for (Point point : POINTS) {
                if (count >= M - 1) {
                    break;
                }

                int x = find(point.x);
                int y = find(point.y);
                if (x != y) {
                    answer += point.z;
                    union(point.x, point.y);
                    count++;
                }
            }

            System.out.println(total - answer);
        }

    }

    private static int find(int a) {
        if (P[a] < 0) {
            return a;
        }

        return P[a] = find(P[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }

        P[a] += P[b];
        P[b] = a;
    }

    static class Point {
        int x, y, z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }

}
