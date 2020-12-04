package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class 터보소트_3006 {

    static int N, LEAF_START;
    static int[] TREE;
    static Point[] INPUTS;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        INPUTS = new Point[N];
        TREE = new int[N*4];
        for (LEAF_START=1; LEAF_START<N; LEAF_START*=2);
//        System.out.println("LEAF_START : " + LEAF_START);

        for (int i=0; i<N; i++) {
            INPUTS[i] = new Point(Integer.parseInt(br.readLine()), i+1);
            TREE[LEAF_START + i] = 1;
        }

        for (int i=LEAF_START-1; i>0; i--) {
            TREE[i] = TREE[i*2] + TREE[i*2+1];
        }
//        System.out.println("TREE : " + Arrays.toString(TREE));

//        System.out.println("INPUTS1 : " + Arrays.toString(INPUTS));
        Arrays.sort(INPUTS, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });
//        System.out.println("INPUTS2 : " + Arrays.toString(INPUTS));

        int s = 0;
        int e = N-1;
        int count = 1;
        while (s <= e) {
            int answer = 0;
            if (count % 2 == 1) { // 홀수번째
                answer = find(1, INPUTS[s].index-1);
                update(INPUTS[s].index, 0);
                s++;

            } else { // 짝수번째
                answer = find(INPUTS[e].index+1, N);
                update(INPUTS[e].index, 0);
                e--;
            }

            bw.write(answer + "\n");
            count++;
        }

        bw.flush();
        bw.close();
    }

    static int find(int left, int right) {
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

    static void update(int node, int value) {
        node += LEAF_START - 1;
        TREE[node] = value;

        while ((node /= 2) > 0) {
            TREE[node] = TREE[node*2] + TREE[node*2+1];
        }
    }

    static class Point {
        int num, index;

        public Point(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "num=" + num +
                    ", index=" + index +
                    '}';
        }
    }

}
