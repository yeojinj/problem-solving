import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());		// 테스트 케이스의 수
		
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());	// 동전의 가지 수
			int[] coin = new int[N];					// 각 동전의 금액(오름차순)
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());	// 만들어야 할 금액
			int[] dp = new int[M + 1];
			dp[0] = 1;
			
			for (int i = 0; i < N; i++) {				// 동전 종류별로 하나씩
				for (int m = coin[i]; m <= M; m++) {		// 총 무게
					dp[m] += dp[m - coin[i]];
				}
			}
			
			System.out.println(dp[M]);
		}
		
	}
}