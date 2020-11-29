package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 역사_1613 {

    private static int N, K, S;
    private static int[][] MAP;
    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        MAP = new int[N+1][N+1];
        for (int i=0; i<N+1; i++) {
            for (int j=0; j<N+1; j++) {
                MAP[i][j] = (i == j) ? 0 : MAX;
            }
        }

        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            MAP[a][b] = 1;
        }

        for (int k=1; k<N+1; k++) {
            for (int i=1; i<N+1; i++) {
                for (int j=1; j<N+1; j++) {
                    MAP[i][j] = Math.min(MAP[i][j], MAP[i][k] + MAP[k][j]);
                }
            }
        }

//        for (int i=0; i<N+1; i++) {
//            System.out.println(Arrays.toString(MAP[i]));
//        }

        S = Integer.parseInt(br.readLine().trim());
        for (int i=0; i<S; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (MAP[a][b] > 0 && MAP[a][b] < MAX) {
                bw.write("-1" + "\n");
            } else if (MAP[b][a] > 0 && MAP[b][a] < MAX) {
                bw.write("1" + "\n");
            } else {
                bw.write("0" + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

}
