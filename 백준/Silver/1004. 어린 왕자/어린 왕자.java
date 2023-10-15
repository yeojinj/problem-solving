import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());    // 테스트 케이스의 개수

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());  // 출발점 (x1, y1)
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());  // 도착점 (x2, y2)
            int y2 = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(br.readLine());    // 행성계의 개수

            int ans = 0;    // 최소의 행성계 진입/이탈 횟수

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());  // 행성계의 중점
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());   // 반지름
                if (isIn(x1, y1, cx, cy, r)) {
                    if (!isIn(x2, y2, cx, cy, r)) {
                        ans++;
                    }
                } else {
                    if (isIn(x2, y2, cx, cy, r)) {
                        ans++;
                    }
                }
            }

            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }

    /**
     *  (x, y)가 중심이 (cx, cy)이고 반지름이 r인 원 내부에 있는지 여부
     */
    static boolean isIn(int x, int y, int cx, int cy, int r) {
        return (r * r) > Math.pow(x - cx, 2) + Math.pow(y - cy, 2);
    }
}