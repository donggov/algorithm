package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class 궁금한민호_1507 {

    private static int N;
    private static int[][] MAP_01, MAP_02;
    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        MAP_01 = new int[N+1][N+1];
        MAP_02 = new int[N+1][N+1];
        for (int i=0; i<N+1; i++) {
            for (int j=0; j<N+1; j++) {
                MAP_01[i][j] = (i == j) ? 0 : MAX;
                MAP_02[i][j] = (i == j) ? 0 : MAX;
            }
        }

        for (int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<N+1; j++) {
                int num = Integer.parseInt(st.nextToken());
                MAP_01[i][j] = num;
                MAP_02[i][j] = num;
            }
        }

        for (int k=1; k<N+1; k++) {
            for (int i=1; i<N+1; i++) {
                for (int j=1; j<N+1; j++) {
                    if (i == j || i == k || j == k) {
                        continue;
                    }
                    if (MAP_01[i][j] > MAP_01[i][k] + MAP_01[k][j]) {
                        bw.write("-1\n");
                        bw.flush();
                        bw.close();
                        return;

                    } else if (MAP_01[i][j] == MAP_01[i][k] + MAP_01[k][j]) {
                        MAP_02[i][j] = 0;
                    }
                }
            }
        }

        boolean[][] isAdded = new boolean[N+1][N+1];
        int answer = 0;
        for (int i=1; i<N+1; i++) {
            for (int j=1; j<N+1; j++) {
                if (MAP_02[i][j] < MAX && !isAdded[i][j] && !isAdded[j][i]) {
                    answer += MAP_02[i][j];
                    isAdded[i][j] = true;
                    isAdded[j][i] = true;
                }
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

}
