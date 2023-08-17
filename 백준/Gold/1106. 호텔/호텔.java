import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());       // 최소한 늘려야 하는 고객 수
        int N = Integer.parseInt(st.nextToken());       // 도시의 개수
        int city[][] = new int[N + 1][2];

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            city[n][0] = Integer.parseInt(st.nextToken());  // 비용
            city[n][1] = Integer.parseInt(st.nextToken());  // 고객의 수
        }

        int dp[] = new int[C + 101];
        dp[0] = 0;
        for (int c = 1; c <= C + 100; c++) {
            dp[c] = Integer.MAX_VALUE;
        }

        for (int c = 1; c <= C + 100; c++) {        // 최대 고객 수
            for (int n = 1; n <= N; n++) {          // 도시 번호
                int cost = city[n][0];          // n번 도시 홍보할 때 드는 비용
                int cus = city[n][1];           // n번 도시 홍보할 때 얻을 수 있는 고객의 수
                if (c >= cus && dp[c - cus] != Integer.MAX_VALUE) {
                    dp[c] = Math.min(dp[c], dp[c - cus] + cost);
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int c = C; c <= C + 100; c++) {
            ans = Math.min(ans, dp[c]);
        }

        System.out.println(ans);

    }

}
