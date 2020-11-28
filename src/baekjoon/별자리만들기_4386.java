package baekjoon;

import java.io.*;
import java.util.*;

public class 별자리만들기_4386 {

    private static int N;
    private static int[] P;
    private static Point[] POINTS;
    private static List<Line> LINES;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine().trim());
        P = new int[N+1];
        Arrays.fill(P, -1);
        POINTS = new Point[N];
        LINES = new ArrayList<>();

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            POINTS[i] = new Point(x, y);
        }

        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                LINES.add(new Line(i, j, getDist(POINTS[i], POINTS[j])));
            }
        }
        Collections.sort(LINES, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                if (o1.value - o2.value > 0) return 1;
                else if (o1.value - o2.value < 0) return -1;
                else return 0;
            }
        });

        double dist = 0;
        int count = 0;
        for (Line line : LINES) {
            if (count >= N-1) {
                break;
            }

            int a = find(line.a);
            int b = find(line.b);
            if (a != b) {
                dist += line.value;
                union(a, b);
                count++;
            }
        }

        bw.write(dist + "\n");
        bw.flush();
        bw.close();
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

    static int find(int a) {
        if (P[a] < 0) {
            return a;
        }

        return P[a] = find(P[a]);
    }

    static double getDist(Point a, Point b) {
        double x = (a.x - b.x) * (a.x - b.x);
        double y = (a.y - b.y) * (a.y - b.y);
        return Math.sqrt(x + y);
    }

    static class Line {
        int a, b;
        double value;

        public Line(int a, int b, double value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "a=" + a +
                    ", b=" + b +
                    ", value=" + value +
                    '}';
        }
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
