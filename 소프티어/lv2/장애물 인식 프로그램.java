import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] map;

    static PriorityQueue<Integer> blocks;    // 각 블록 내 장애물의 수
    static int[][] visited;                  // 좌표 방문 여부 (방문 시 1)

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        blocks = new PriorityQueue<Integer>();
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(blocks.size());
        while (!blocks.isEmpty()) {
            System.out.println(blocks.poll());
        }
    }

    public static void bfs(int r, int c) {
        Queue<Point> q = new LinkedList<Point>();
        q.add(new Point(r, c));
        visited[r][c] = 1;
        int blockSize = 1;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (isIn(nr, nc)) {
                    if (map[nr][nc] == 1 && visited[nr][nc] == 0) {
                        q.add(new Point(nr, nc));
                        visited[nr][nc] = 1;
                        blockSize++;
                    }
                }
            }
        }

        blocks.add(blockSize);
    }

    public static boolean isIn(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= N) {
            return false;
        } else {
            return true;
        }

    }

}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}