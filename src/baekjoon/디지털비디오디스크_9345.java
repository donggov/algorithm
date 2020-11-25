package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 디지털비디오디스크_9345 {

    private static int T, N, K, LEAF_START;
    private static Tree[] TREE;
    private static int MIN = -987654321;
    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        T = Integer.parseInt(st.nextToken());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            TREE = new Tree[N*4];
            for (int i=0; i<N*4; i++) {
                TREE[i] = new Tree();
            }

            for (LEAF_START=1; LEAF_START<N; LEAF_START*=2);

            int num = 0;
            for (int i=LEAF_START; i<LEAF_START+N; i++) {
                TREE[i].min = num;
                TREE[i].max = num;
                num++;
            }

            for (int i=LEAF_START-1; i>0; i--) {
                TREE[i].min = Math.min(TREE[i*2].min, TREE[i*2+1].min);
                TREE[i].max = Math.max(TREE[i*2].max, TREE[i*2+1].max);
            }
//            System.out.println(Arrays.toString(TREE));

            for (int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int q = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (q == 0) {
                    int bb = TREE[LEAF_START+b].min;
                    int aa = TREE[LEAF_START+a].min;
                    update(a+1, bb);
                    update(b+1, aa);
//                    System.out.println("UPDATED : " + Arrays.toString(TREE));

                } else if (q == 1) {
                    Tree tree = find(a+1, b+1);
                    if (a == tree.min && b == tree.max) {
                        bw.write("YES" + "\n");
                    } else {
                        bw.write("NO" + "\n");
                    }
//                    System.out.println(tree.toString());
                }
            }

        }

        bw.flush();
        bw.close();
    }

    private static Tree find(int left, int right) {
        left += LEAF_START - 1;
        right += LEAF_START - 1;

        Tree result = new Tree();
        while (left <= right) {
            if (left % 2 == 1) {
                result.min = Math.min(result.min, TREE[left].min);
                result.max = Math.max(result.max, TREE[left].max);
                left++;
            }
            if (right % 2 == 0) {
                result.min = Math.min(result.min, TREE[right].min);
                result.max = Math.max(result.max, TREE[right].max);
                right--;
            }

            left /= 2;
            right /= 2;
        }
        return result;
    }

    private static void update(int index, int value) {
        index += LEAF_START - 1;
        TREE[index].min = value;
        TREE[index].max = value;

        while ((index/=2) > 0) {
            TREE[index].min = Math.min(TREE[index*2].min, TREE[index*2+1].min);
            TREE[index].max = Math.max(TREE[index*2].max, TREE[index*2+1].max);
        }
    }

    static class Tree {
        int min, max;

        public Tree() {
            this.min = MAX;
            this.max = MIN;
        }

        public Tree(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

}
