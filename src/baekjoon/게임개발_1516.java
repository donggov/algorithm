package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 게임개발_1516 {

    private static int N;
    private static List<Integer>[] ADJ_LIST;
    private static int[] INDEGREE, VALUES_01, VALUES_02;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        ADJ_LIST = new List[N+1];
        for (int i=0; i<N+1; i++) {
            ADJ_LIST[i] = new ArrayList<>();
        }
        INDEGREE = new int[N+1];
        VALUES_01 = new int[N+1];
        VALUES_02 = new int[N+1];

        int from;
        for (int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            VALUES_01[i] = Integer.parseInt(st.nextToken());

            while ((from = Integer.parseInt(st.nextToken())) != -1) {
                ADJ_LIST[from].add(i);
                INDEGREE[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i=1; i<N+1; i++) {
            if (INDEGREE[i] == 0) {
                queue.add(i);
                VALUES_02[i] = VALUES_01[i];
            }
        }

        while (!queue.isEmpty()) {
            from = queue.poll();

            for (int to : ADJ_LIST[from]) {
                INDEGREE[to]--;
                VALUES_02[to] = Math.max(VALUES_02[to], VALUES_02[from] + VALUES_01[to]);
                if (INDEGREE[to] == 0) {
                    queue.add(to);
                }
            }
        }

        for (int i=1; i<N+1; i++) {
            System.out.println(VALUES_02[i]);
        }
    }

}
