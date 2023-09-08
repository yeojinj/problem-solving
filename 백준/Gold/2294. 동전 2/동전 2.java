import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 동전 가지 수
        int k = Integer.parseInt(st.nextToken());   // 동전 가치의 합 k원
        int coin[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int dp[] = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            dp[i] = 100_001;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }

        if (dp[k] == 100_001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }

    }
}
