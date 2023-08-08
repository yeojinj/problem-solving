import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1) 무조건 최솟값 구하는 게 먼저가 아님
 * 최솟값이기 이전에 N번은 N-1번, 1번 색과 달라야 하는게 우선임
 * 처음 풀이한 방식은 일단 무조건 최솟값이 구해지도록 1부터 N까지 타고 가되,
 * 비트마스킹(...)으로 1번의 상태를 저장하면서 가려고 했음
 * -> 1번 집의 색이 무엇인지에 따라 3가지 경우로 나눠서 각각 N까지 갔을 때의 최솟값을 구하는 식으로 고침
 *
 * 2) 40, 41번째 줄처럼 1번 집의 색이 아닌 것은 충분히 큰 값(1000 * 1000)으로 초기화해서 이후에도 선택되지 않게 함
 * Integer.MAX_VALUE 로 초기화하면 오버플로우 때문에 음수가 되기 때문에 주의!!
 */
public class Main {
    static final int R = 0, G = 1, B = 2;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());    // 집의 수
        int cost[][] = new int[N + 1][3];           // 각 집을 빨강, 초록, 파랑으로 칠하는 비용
                                                    // cost[a][b]에서 a는 집 번호, b는 빨강(0), 초록(1), 파랑(2)

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int c = R; c <= B; c++) {
                cost[i][c] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;            // 모든 집을 칠하는 비용의 최솟값
        int dp[][];         // dp[a][R]: a번 집이 빨강(0)일 때, 1번 ~ a번 집을 모두 칠하는 비용의 최솟값

        // start: 1번 집의 색
        for (int start = 0; start < 3; start++) {
            dp = new int[N + 1][3];
            dp[1][start] = cost[1][start];
            dp[1][(start + 1) % 3] = 1000 * 1000 + 1;
            dp[1][(start + 2) % 3] = 1000 * 1000 + 1;

            for (int i = 2; i <= N; i++) {
                for (int c = R; c <= B; c++) {
                    dp[i][c] = Math.min(dp[i - 1][(c + 1) % 3], dp[i - 1][(c + 2) % 3])
                            + cost[i][c];
                }
            }

            if (dp[N][(start + 1) % 3] < dp[N][(start + 2) % 3]) {
                ans = Math.min(ans, dp[N][(start + 1) % 3]);
            } else {
                ans = Math.min(ans, dp[N][(start + 2) % 3]);
            }
        }

        System.out.println(ans);
    }
}
