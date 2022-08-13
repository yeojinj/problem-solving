package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1010_다리놓기_정여진 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int dp[][] = new int[30][30];
		
		for(int i = 0; i < 30; i++) {
			dp[i][0] = 1;	// nC1 = 1
			dp[i][i] = 1;	// nCn = 1
		}
		
		for(int i = 1; i < 30; i++) {
			for(int j = 1; j < 30; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];	// nCk = n-1Ck-1 + n-1Ck
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[M][N]);	// M개 중 N개를 선택하는 조합의 수
		}
	}
}
