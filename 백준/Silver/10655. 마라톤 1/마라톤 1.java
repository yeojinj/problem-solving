import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Point[] points;
//    static int[][] dp;          // dp[i][j] : i 에서 j 까지의 거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 체크포인트의 수
        points = new Point[N];
//        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        // 어느 체크포인트를 건너뛸지 정하기
        int maxGap = Integer.MIN_VALUE;
        for (int i = 1; i < N - 1; i++) {
            int gap = getGap(i - 1, i, i + 1);
            if (gap > maxGap) {
                maxGap = gap;
            }
        }

        // 최소 거리 구하기
        int sumDist = 0;
        for (int i = 0; i < N - 1; i++) {
            sumDist += getDist(i, i + 1);
        }

        System.out.println(sumDist - maxGap);

    }

    /**
     * a->b->c로 갔을 때와 b를 건너뛰고 a->c로 갔을 때의 거리 차이
     */
    public static int getGap(int a, int b, int c) {
        int ab = getDist(a, b);
        int bc = getDist(b, c);
        int ac = getDist(a, c);
        return ab + bc - ac;
    }

    /**
     * a번 체크포인트에서 b번 체크포인트까지의 거리
     */
    public static int getDist(int a, int b) {
//        if (dp[a][b] > 0) {
//            return dp[a][b];
//        } else {
//            return dp[a][b] = Math.abs(points[a].x - points[b].x) + Math.abs(points[a].y - points[b].y);
//        }
        return Math.abs(points[a].x - points[b].x) + Math.abs(points[a].y - points[b].y);
    }
}
