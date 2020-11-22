package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 네트워트연결_1922 {

    private static int N, M;
    private static Point[] POINTS;
    private static int[] P;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        POINTS = new Point[M];
        P = new int[N+1];
        Arrays.fill(P, -1);

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            POINTS[i] = new Point(a, b, c);
        }
//        System.out.println("POINTS : " + Arrays.toString(POINTS));

        Arrays.sort(POINTS, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.value - o2.value;
            }
        });
//        System.out.println("POINTS : " + Arrays.toString(POINTS));

        int answer = 0;
        int count = 0;
        for (Point point : POINTS) {
            if (count >= N-1) {
                break;
            }

            int a = find(point.from);
            int b = find(point.next);
            if (a != b) {
                answer += point.value;
                union(point.from, point.next);
                count++;
            }
        }
        System.out.println(answer);
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
        int from, next, value;

        public Point(int from, int next, int value) {
            this.from = from;
            this.next = next;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "from=" + from +
                    ", next=" + next +
                    ", value=" + value +
                    '}';
        }
    }

}
