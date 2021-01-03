package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ACMCraft_1005 {

    private static int T, N, K, W;
    private static List<Integer>[] ADJ_LIST;
    private static int[] TIMES, INDEGREE, VALUES;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            ADJ_LIST = new List[N+1];
            for (int i=0; i<N+1; i++) {
                ADJ_LIST[i] = new ArrayList<>();
            }
            TIMES = new int[N+1];
            INDEGREE = new int[N+1];
            VALUES = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i=1; i<N+1; i++) {
                TIMES[i] = Integer.parseInt(st.nextToken());
            }

            int a, b;
            for (int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                ADJ_LIST[a].add(b);
                INDEGREE[b]++;
            }

            W = Integer.parseInt(br.readLine());

            Queue<Integer> queue = new LinkedList<>();
            for (int i=1; i<N+1; i++) {
                if (INDEGREE[i] == 0) {
                    queue.add(i);
                    VALUES[i] = TIMES[i];
                }
            }

            int from;
            while (!queue.isEmpty()) {
                from = queue.poll();
                if (from == W) {
                    break;
                }

                for (int to : ADJ_LIST[from]) {
                    VALUES[to] = Math.max(VALUES[to], VALUES[from] + TIMES[to]);

                    INDEGREE[to]--;
                    if (INDEGREE[to] == 0) {
                        queue.add(to);
                    }
                }
            }

            System.out.println(VALUES[W]);
        }
    }

}
