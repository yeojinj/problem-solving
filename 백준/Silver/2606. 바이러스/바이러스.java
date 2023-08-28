import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int arr[][];
    static boolean visit[];
    static Queue<Integer> q = new LinkedList<>();
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    // 컴퓨터의 수
        M = Integer.parseInt(br.readLine());    // 컴퓨터 쌍의 수

        visit = new boolean[N + 1];
        arr = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            arr[y][x] = 1;
            arr[x][y] = 1;
        }

        bfs();

        System.out.println(ans);
    }

    public static void bfs() {
        q.offer(1);
        visit[1] = true;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int i = 1; i < N + 1; i++) {
                if (arr[node][i] != 1 || visit[i] == true) {
                    continue;
                }
                q.offer(i);
                visit[i] = true;
                ans++;
            }
        }

    }
}