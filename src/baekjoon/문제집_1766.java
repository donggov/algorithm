package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제집_1766 {

    static int N, M;
    static List<Integer>[] ADJ_LIST;
    static int[] INDEGREE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ADJ_LIST = new List[N+1];
        for (int i=0; i<N+1; i++) {
            ADJ_LIST[i] = new ArrayList<>();
        }
        INDEGREE = new int[N+1];

        int a, b;
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            ADJ_LIST[a].add(b);
            INDEGREE[b]++;
        }


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=1; i<N+1; i++) {
            if (INDEGREE[i] == 0) {
                pq.add(i);
            }
        }

        List<Integer> results = new ArrayList<>();
        while (!pq.isEmpty()) {
            int from = pq.poll();
            results.add(from);

            for (int to : ADJ_LIST[from]) {
                INDEGREE[to]--;
                if (INDEGREE[to] == 0) {
                    pq.add(to);
                }
            }
        }

        for (int i : results) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
