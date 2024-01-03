import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Gem {
    int w;  // 무게
    int v;  // 가치

    public Gem() {
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 물건 수
        int K = Integer.parseInt(st.nextToken());   // 최대 무게

        Gem[] gems = new Gem[N + 1];    // (1 ~ N)

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            gems[i] = new Gem();
            gems[i].w = Integer.parseInt(st.nextToken());   // i번 물건의 무게
            gems[i].v = Integer.parseInt(st.nextToken());   // i번 물건의 가치
        }

        int[][] dp = new int[N + 1][K + 1];        // (1 ~ N)

        for (int k = 1; k <= K; k++) {// for k 무게
            for (int i = 1; i <= N; i++) {// for i 물건 번호
                dp[i][k] = dp[i - 1][k];
                // if 물건 넣을 수 있으면 (-> k - gems[i].w >= 0)
                if (k - gems[i].w >= 0) {
                    dp[i][k] = Math.max(dp[i][k], gems[i].v + dp[i - 1][k - gems[i].w]);    // i번 물건을 넣음
                }
            }
        }

        // 답 N K
        System.out.println(dp[N][K]);

    }
}