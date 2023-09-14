import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int dist;   // 이거 넣으면 메모리 초과함

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N, M;
    static int[][] dist;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로
        M = Integer.parseInt(st.nextToken());   // 가로

        dist = new int[N][M];       // 목표지점까지의 거리 기록
        int tx = 0, ty = 0;     // 목표지점의 좌표
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 2) {           // 목표지점 (2)
                    tx = i;
                    ty = j;
                } else if (info == 0) {    // 갈 수 없는 땅 (0)
                    dist[i][j] = 0;
                } else {                        // 갈 수 있는 땅 (1)
                    dist[i][j] = -1;            // 도달할 수 없는 위치로 초기화 (도달할 수 있는 위치만 체크해나가기 위해)
                                                // dist[][]가 -1, 0이 아니면 방문한 곳임 (visited 배열 따로 필요 없음)
                }
            }
        }

        bfs(tx, ty);

        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));        // 목표지점에서 출발해서 역추적
        dist[x][y] = 0;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {       // 사방탐색
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if (isIn(nx, ny) && dist[nx][ny] == -1) {       // 범위 + visited + 갈 수 있는 땅 체크
                    q.offer(new Point(nx, ny));
                    dist[nx][ny] = dist[p.x][p.y] + 1;
                }
            }
        }
    }

    static boolean isIn(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) {
            return false;
        } else {
            return true;
        }
    }
}
