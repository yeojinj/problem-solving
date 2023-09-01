import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());   // N자리 이친수
        long dp[][] = new long[N + 1][2];             // dp[i][j] : i자리이고 j로 끝나는 이친수의 개수
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = 0;
            }
        }
        dp[1][1] = 1;
        if (N > 1) {
            dp[2][0] = 1;
        }

        for (int i = 3; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        sb.append(dp[N][0] + dp[N][1]);

        System.out.print(sb);
    }

}