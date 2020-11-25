package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 디지털비디오디스크_9345 {

    private static int T, N, M, LEAF_START;
    private static int[] TREE, DVD_INDEX;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine().trim());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int nm = N + M;
            for (LEAF_START=1; LEAF_START<nm; LEAF_START*=2);

            TREE = new int[nm*4];
            for (int i=LEAF_START+M; i<LEAF_START+nm; i++) {
                TREE[i] = 1;
            }

            DVD_INDEX = new int[nm+1];
            for (int i=1; i<N+1; i++) {
                DVD_INDEX[i] = M + i;
            }
//            System.out.println("DVD_INDEX : " + Arrays.toString(DVD_INDEX));

            for (int i=LEAF_START-1; i>0; i--) {
                TREE[i] = TREE[i*2] + TREE[i*2+1];
            }

            st = new StringTokenizer(br.readLine().trim());
            int m = M;
            for (int i=0; i<M; i++) {
                int dvd = Integer.parseInt(st.nextToken());
                int dvdIndex = DVD_INDEX[dvd];
//                System.out.println("dvd index : " + dvdIndex);
                int sum = find(1, dvdIndex-1);

                update(dvdIndex, 0);
                DVD_INDEX[dvd] = m;
                update(m, 1);
                m--;

                bw.write(sum + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static void update(int index, int value) {
        index += LEAF_START - 1;
        TREE[index] = value;
        while ((index/=2) > 0 ) {
            TREE[index] = TREE[index*2] + TREE[index*2+1];
        }
    }

    private static int find(int left, int right) {
        left += LEAF_START - 1;
        right += LEAF_START - 1;

        int result = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                result += TREE[left];
                left++;
            }
            if (right % 2 == 0) {
                result += TREE[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return result;
    }

}
