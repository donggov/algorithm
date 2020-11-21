package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 임계경로_1948 {

    private static int N, M;
    private static int[] INDEGREE_01, INDEGREE_02;
    private static int[] VALUES;
    private static List<Point>[] POINTS_01, POINTS_02;
    private static int MIN = -987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        INDEGREE_01 = new int[N+1];
        INDEGREE_02 = new int[N+1];
        POINTS_01 = new List[N+1];
        POINTS_02 = new List[N+1];
        for (int i=0; i<N+1; i++) {
            POINTS_01[i] = new ArrayList<>();
            POINTS_02[i] = new ArrayList<>();
        }

        VALUES = new int[N+1];
        Arrays.fill(VALUES, MIN);

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            POINTS_01[from].add(new Point(from, to, time));
            INDEGREE_01[to]++;

            POINTS_02[to].add(new Point(to, from, time));
            INDEGREE_02[from]++;
        }
//        System.out.println(Arrays.toString(INDEGREE_01));

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        VALUES[s] = 0;

        List<Integer> orders = new ArrayList<>();
        while (!queue.isEmpty()) {
            int from = queue.poll();
            orders.add(from);

            for (Point point : POINTS_01[from]) {
                int to = point.to;
                VALUES[to] = Math.max(VALUES[to], VALUES[from] + point.value);

                if (--INDEGREE_01[to] == 0) {
                    queue.add(to);
                }
            }
        }
//        System.out.println("orders : " + orders);
//        System.out.println(Arrays.toString(VALUES));

        int count = 0;
        boolean[] isVisited = new boolean[N+1];
        queue.clear();
        queue.add(e);
        isVisited[e] = true;

        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (Point point : POINTS_02[from]) {
                int to = point.to;
                INDEGREE_02[to]--;

                if (VALUES[from] - point.value == VALUES[to]) {
//                    System.out.println(from + " -> " + to);
                    count++;
                    if (!isVisited[to]) {
                        isVisited[to] = true;
                        queue.add(to);
                    }
                }
            }
        }
//        System.out.println("count : " + count);

        System.out.println(VALUES[e]);
        System.out.println(count);
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
