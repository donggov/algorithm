package baekjoon;

import java.io.*;
import java.util.*;

public class 최종순위_3665 {

    static int T, N, M;
    static int[] INPUTS, INDEGREE;
    static List<Integer>[] ADJ_LIST;
    static boolean[][] IS_CHANGED;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            INPUTS = new int[N];
            INDEGREE = new int[N+1];
            ADJ_LIST = new List[N+1];
            for (int i=0; i<N+1; i++) {
                ADJ_LIST[i] = new ArrayList<>();
            }
            IS_CHANGED = new boolean[N+1][N+1];

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++) {
                INPUTS[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                IS_CHANGED[a][b] = true;
                IS_CHANGED[b][a] = true;
            }

            for (int i=0; i<N; i++) {
                for (int j=i+1; j<N; j++) {
                    int from = INPUTS[i];
                    int to = INPUTS[j];

                    if (IS_CHANGED[from][to]) {
                        int tempFrom = from;
                        from = to;
                        to = tempFrom;
                    }

                    ADJ_LIST[from].add(to);
                    INDEGREE[to]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i=1; i<N+1; i++) {
                if (INDEGREE[i] == 0) {
                    queue.add(i);
                }
            }

            boolean isKnow = true;
            List<Integer> results = new ArrayList<>();
            while (!queue.isEmpty()) {
                if (queue.size() >= 2) {
                    isKnow = false;
                    break;
                }

                int from = queue.poll();
                results.add(from);

                for (int to : ADJ_LIST[from]) {
                    INDEGREE[to]--;
                    if (INDEGREE[to] == 0) {
                        queue.add(to);
                    }
                }
            }

            if (!isKnow) {
                bw.write("?\n");

            } else if (results.size() == N) {
                StringBuilder sb = new StringBuilder();
                for (int i : results) {
                    sb.append(i).append(" ");
                }
                bw.write(sb.toString() + "\n");

            } else {
                bw.write("IMPOSSIBLE\n");
            }

            bw.flush();
        }

        bw.close();
    }

}
