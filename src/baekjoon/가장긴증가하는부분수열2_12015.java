package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열2_12015 {

    private static int N, LEAF_START;
    private static int[] TREE;
    private static Point[] NUMBERS;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine().trim());
        TREE = new int[N*4];
        NUMBERS = new Point[N];

        for (LEAF_START=1; LEAF_START<N; LEAF_START*=2);

        st = new StringTokenizer(br.readLine().trim());

        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            NUMBERS[i] = new Point(num, i+1);
        }
//        System.out.println(Arrays.toString(TREE));
//        System.out.println(Arrays.toString(NUMBERS));

        Arrays.sort(NUMBERS, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.num == o2.num) {
                    return o2.index - o1.index;
                } else {
                    return o1.num - o2.num;
                }
            }
        });
//        System.out.println("SORTED : " + Arrays.toString(NUMBERS));

        int answer = 0;
        for (Point point : NUMBERS) {
            int max = find(1, point.index) + 1;
            update(point.index, max);
            answer = Math.max(answer, max);
        }
//        System.out.println(Arrays.toString(TREE));

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    static void update(int index, int value) {
        index += LEAF_START - 1;
        TREE[index] = value;
        while ((index/=2) > 0) {
            TREE[index] = Math.max(TREE[index*2], TREE[index*2+1]);
        }
    }

    static int find(int left, int right) {
        left += LEAF_START - 1;
        right += LEAF_START - 1;

        int max = -1;
        while (left <= right) {
            if (left % 2 == 1) {
                max = Math.max(max, TREE[left]);
                left++;
            }
            if (right % 2 == 0) {
                max = Math.max(max, TREE[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }
        return max;
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
