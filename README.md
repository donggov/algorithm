# 알고리즘

## 다익스트라
prev를 비열을 사용하면 최단경로를 추적도 가능하다.
````java
static int[] dijkstra(int start) {
    int[] values = new int[V+1];
    int[] prevs = new int[V+1];
    for (int i=0; i<V+1; i++) {
        values[i] = MAX;
        prevs[i] = -1;
    }

    PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.value - o2.value;
        }
    });
    queue.add(new Point(start, 0));
    values[start] = 0;

    while (!queue.isEmpty()) {
        Point p1 = queue.poll();
        int from = p1.to;
        int value = p1.value;

        // values[from] 값이 from을 넣을 당시 경로보다 더 짧다면 from 무시
        if (value > values[from]) {
            continue;
        }

        for (Point p2 : POINTS[from]) {
            int to = p2.to;
            int newValue = value + p2.value;
            if (newValue < values[to]) {
                values[to] = newValue;
                prevs[to] = from;
                queue.add(new Point(to, newValue));
            }
        }
    }

//        System.out.println("v : " + Arrays.toString(values));
//        System.out.println("prev : " + Arrays.toString(prevs));
//
//        int curr = 4;
//        while (curr > 0) {
//            System.out.println("> " + curr);
//            curr = prevs[curr];
//        }
    
    return values;
}
````