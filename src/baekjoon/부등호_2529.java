package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 부등호_2529 {

    private static int K;
    private static int[] INDEGREE_01, INDEGREE_02;
    private static List<Integer>[] POINTS_01, POINTS_02;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        INDEGREE_01 = new int[K+1];
        INDEGREE_02 = new int[K+1];

        POINTS_01 = new List[K+1];
        POINTS_02 = new List[K+1];
        for (int i=0; i<K+1; i++) {
            POINTS_01[i] = new ArrayList<>();
            POINTS_02[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int from = 0;
        for (int to=1; to<K+1; to++) {
            String str = st.nextToken();
            if ("<".equals(str)) {
                POINTS_01[from].add(to);
                INDEGREE_01[to]++;

                POINTS_02[to].add(from);
                INDEGREE_02[from]++;

            } else if (">".equals(str)) {
                POINTS_01[to].add(from);
                INDEGREE_01[from]++;

                POINTS_02[from].add(to);
                INDEGREE_02[to]++;
            }
            from = to;
        }
//        System.out.println(Arrays.toString(POINTS));
//        System.out.println(Arrays.toString(INDEGREE));

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i=0; i<K+1; i++) {
            if (INDEGREE_01[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> minResults = new ArrayList<>();
        while (!queue.isEmpty()) {
            int q = queue.poll();
            minResults.add(q);

            for (int to : POINTS_01[q]) {
                if (--INDEGREE_01[to] == 0) {
                    queue.add(to);
                }
            }
        }

        queue.clear();
        for (int i=0; i<K+1; i++) {
            if (INDEGREE_02[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> maxResults = new ArrayList<>();
        while (!queue.isEmpty()) {
            int q = queue.poll();
            maxResults.add(q);

            for (int to : POINTS_02[q]) {
                if (--INDEGREE_02[to] == 0) {
                    queue.add(to);
                }
            }
        }

        for (int i : maxResults) {
            System.out.print(9-i);
        }
        System.out.println();

        for (int i : minResults) {
            System.out.print(i);
        }
        System.out.println();
    }

}
