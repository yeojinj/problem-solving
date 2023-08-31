import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int cost;

    public Point(int x, int cost) {
        this.x = x;
        this.cost = cost;
    }
}

public class Main {
    static int N, K;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());   // 수빈이의 위치
        K = Integer.parseInt(st.nextToken());   // 동생의 위치
        visited = new boolean[100_001];

        sb.append(bfs());

        System.out.print(sb);
    }

    static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(N, 0));
        int cost = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();
            visited[p.x] = true;

            if (p.x == K) {
                cost = p.cost;
                break;
            }

            if (p.x * 2 <= 100_000 && !visited[p.x * 2]) {
                q.offer(new Point(p.x * 2, p.cost));
            }
            if (p.x - 1 >= 0 && !visited[p.x - 1]) {
                q.offer(new Point(p.x - 1, p.cost + 1));
            }
            if (p.x + 1 <= 100_000 && !visited[p.x + 1]) {
                q.offer(new Point(p.x + 1, p.cost + 1));
            }
        }

        return cost;
    }

}