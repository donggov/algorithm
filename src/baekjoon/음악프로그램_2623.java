package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 음악프로그램_2623 {

    private static int N, M;
    private static int[] INDEGREE;
    private static List<Integer>[] POINT_LIST;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        INDEGREE = new int[N+1];
        POINT_LIST = new List[N+1];
        for (int i=0; i<N+1; i++) {
            POINT_LIST[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for (int j=1; j<count; j++) {
                int to = Integer.parseInt(st.nextToken());
                POINT_LIST[from].add(to);
                INDEGREE[to]++;
                from = to;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i=1; i<N+1; i++) {
            if (INDEGREE[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int q = queue.poll();
            result.add(q);
            for (int next : POINT_LIST[q]) {
                if (--INDEGREE[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if (result.size() < N) {
            System.out.println(0);
        } else {
            for (int i : result) {
                System.out.println(i);
            }
        }
    }

}
