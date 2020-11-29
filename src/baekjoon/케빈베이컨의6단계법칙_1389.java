package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class 케빈베이컨의6단계법칙_1389 {

    private static int N, M;
    private static int[][] MAP;
    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new int[N+1][N+1];

        for (int i=0; i<N+1; i++) {
            for (int j=0; j<N+1; j++) {
                if (i == j) {
                    MAP[i][j] = 0;
                } else {
                    MAP[i][j] = MAX;
                }
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            MAP[a][b] = 1;
            MAP[b][a] = 1;
        }
//        System.out.println(Arrays.deepToString(MAP));

        for (int k=1; k<N+1; k++) {
            for (int i=1; i<N+1; i++) {
                for (int j=1; j<N+1; j++) {
                    MAP[i][j] = Math.min(MAP[i][j], MAP[i][k] + MAP[k][j]);
                }
            }
        }
//        System.out.println(Arrays.deepToString(MAP));

        int min = MAX;
        int answer = 0;
        for (int i=1; i<N+1; i++) {
            int sum = 0;
            for (int j=1; j<N+1; j++) {
                sum += MAP[i][j];
            }

            if (sum < min) {
                min = sum;
                answer = i;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

}
