import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 101;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 유저의 수
        int M = Integer.parseInt(st.nextToken());   // 친구 관계의 수
        int dp[][] = new int[N + 1][N + 1];
        // 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = MAX;
            }
            dp[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            dp[A][B] = 1;
            dp[B][A] = 1;
        }

        // 플로이드 워셜
        for (int k = 1; k <= N; k++) {      // [주의] k부터 반복문 돌려야 함
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // i -> j 거리 구하기
                    // k를 거쳐갈 때를 고려해서 dp 배열 업데이트
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        // 케빈 베이컨의 수(kbn)가 가장 작은 사람 구하기
        int minKbn = MAX * N + 1;
        int minI = 0;
        for (int i = 1; i <= N; i++) {
            int kbn = 0;
            for (int j = 1; j <= N; j++) {
                kbn += dp[i][j];
            }
            if (minKbn > kbn) {     // kbn 이 같을 경우에는 번호가 가장 작은 사람을 구해야 하므로
                minKbn = kbn;
                minI = i;
            }
        }

        System.out.println(minI);
    }
}