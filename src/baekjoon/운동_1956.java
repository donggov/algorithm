package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class 운동_1956 {

    private static int V, E;
    private static int[][] MAP;
    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        MAP = new int[V+1][V+1];
        for (int i=0; i<V+1; i++) {
            for (int j=0; j<V+1; j++) {
                MAP[i][j] = (i == j) ? 0 : MAX;
            }
        }


        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            MAP[a][b] = c;
        }

        for (int k=1; k<V+1; k++) {
            for (int i=1; i<V+1; i++) {
                for (int j=1; j<V+1; j++) {
                    MAP[i][j] = Math.min(MAP[i][j], MAP[i][k] + MAP[k][j]);
                }
            }
        }

        int answer = MAX;
        for (int i=1; i<V+1; i++) {
            for (int j=1; j<V+1; j++) {
                if (i == j) continue;

                answer = Math.min(answer, MAP[i][j] + MAP[j][i]);
            }
        }

        if (answer >= MAX) {
            bw.write("-1\n");
        } else {
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }

}
