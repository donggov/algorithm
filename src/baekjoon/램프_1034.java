package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 램프_1034 {

    static int N, M, K;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, Lamp> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            String str = br.readLine();
            String[] strs = str.split("");

            int zero = 0;
            for (String s : strs) {
                if ("0".equals(s)) {
                    zero++;
                }
            }

            if (map.containsKey(str)) {
                Lamp lamp = map.get(str);
                lamp.count++;
                map.put(str, lamp);

            } else {
                map.put(str, new Lamp(zero, 1));
            }
        }
//        System.out.println(map);

        K = Integer.parseInt(br.readLine());

        int answer = 0;
        for (String key : map.keySet()) {
            Lamp lamp = map.get(key);
            if (lamp.zero <= K && lamp.zero % 2 == K % 2) {
                answer = Math.max(answer, lamp.count);
            }
        }

        System.out.println(answer);
    }

    static class Lamp {
        int zero, count;

        public Lamp(int zero, int count) {
            this.zero = zero;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Lamp{" +
                    "zero=" + zero +
                    ", count=" + count +
                    '}';
        }
    }

}
