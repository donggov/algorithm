package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 플로이드_11404 {

    private static int N, M;
    private static long[][] BUS;
    private static long INF = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        BUS = new long[N+1][N+1];

        for (int i=0; i<N+1; i++) {
            for (int j=0; j<N+1; j++) {
                if (i == j) {
                    continue;
                }

                BUS[i][j] = INF;
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            BUS[a][b] = Math.min(BUS[a][b], c);
        }

        for (int k=1; k<N+1; k++) {
            for (int i=1; i<N+1; i++) {
                for (int j=1; j<N+1; j++) {
                    BUS[i][j] = Math.min(BUS[i][j], BUS[i][k] + BUS[k][j]);
                }
            }
        }

//        System.out.println(Arrays.deepToString(BUS));
        for (int i=1; i<N+1; i++) {
            for (int j=1; j<N+1; j++) {
                if (BUS[i][j] == INF) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(BUS[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

}
