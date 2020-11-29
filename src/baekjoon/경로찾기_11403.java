package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 경로찾기_11403 {

    private static int N;
    private static int[][] MAP;
    private static int MAX = 999;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine().trim());
        MAP = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0; j<N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) {
                    MAP[i][j] = MAX;
                } else {
                    MAP[i][j] = num;
                }
            }
        }
//        System.out.println(Arrays.deepToString(MAP));

        for (int k=0; k<N; k++) {
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    MAP[i][j] = Math.min(MAP[i][j], MAP[i][k] + MAP[k][j]);
                }
            }
        }
//        System.out.println(Arrays.deepToString(MAP));

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (MAP[i][j] >= MAX) {
                    bw.write("0 ");
                } else {
                    bw.write("1 ");
                }
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

}
