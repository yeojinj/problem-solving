package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10971_외판원순회2_정여진 {
	static int N;
	static int[][] map;
	static int[][] dp;	// i 도시에 있고, j 비트에 포함된 도시들을 방문했을 때 남은 도시들을 방문하는데 드는 최소 비용 저장
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 도시의 수
		map = new int[N][N];		// 각 도시 간에 이동하는데 드는 비용
		dp = new int[N][(1 << N)];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < (1 << N); j++) {
				dp[i][j] = INF;
			}
		}
		
		System.out.println(dfs(0, 1));
	}
	
	/**
	 * @param n		현재 도시 위치
	 * @param bit	지금까지 방문한 도시들	
	 */
	private static int dfs(int n, int bit) {
		if(bit == (1 << N) - 1) {	// N개의 도시를 모두 방문했을 경우
			if(map[n][0] == 0) return INF;
			else return map[n][0];
		}
		
		if(dp[n][bit] != INF) return dp[n][bit];

		for(int i = 0; i < N; i++) {
			if(map[n][i] == 0 || (bit & (1 << i)) != 0) continue;
			dp[n][bit] = Math.min(dp[n][bit], map[n][i] + dfs(i, bit | (1 << i)));
		}
		
		return dp[n][bit];
	}
}