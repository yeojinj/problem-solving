import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int z;
    int y;
    int x;

    Point(int z, int y, int x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int M, N, H;
    static int tomato[][][];
    static Queue<Point> q;
    static int notRipen = 0, days = 0;
    static int[] rx = {-1, 1, 0, 0, 0, 0};
    static int[] ry = {0, 0, -1, 1, 0, 0};
    static int[] rz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[H][N][M];
        q = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomato[i][j][k] == 0)
                        notRipen++;
                    else if (tomato[i][j][k] == 1)
                        q.add(new Point(i, j, k));
                }
            }
        }

        bfs();

        System.out.println(notRipen == 0 ? days : -1);
    }

    private static void bfs() {
        while (notRipen > 0 && !q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point temp = q.poll();
                for (int j = 0; j < 6; j++) {
                    int nz = temp.z + rz[j];
                    int ny = temp.y + ry[j];
                    int nx = temp.x + rx[j];

                    if (nz < 0 || ny < 0 || nx < 0 || nz >= H || ny >= N || nx >= M)
                        continue;
                    else if (tomato[nz][ny][nx] != 0)
                        continue;

                    tomato[nz][ny][nx] = 1;
                    q.add(new Point(nz, ny, nx));
                    notRipen--;
                }
            }
            days++;
        }
    }
}