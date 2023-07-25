import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int map[][];
	static int dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[M][N];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(dfs(0, 0));
	}
	
	static int dfs(int r, int c) {
		if(r == M - 1 && c == N - 1) {
			return 1;
		}
		
		if(dp[r][c] == -1) {
			dp[r][c] = 0;
			if(r+1 < M && map[r][c] > map[r+1][c]) {
				dp[r][c] += dfs(r+1, c);
			}
			if(c+1 < N && map[r][c] > map[r][c+1]) {
				dp[r][c] += dfs(r, c+1);
			}
			if(r-1 >= 0 && map[r][c] > map[r-1][c]) {
				dp[r][c] += dfs(r-1, c);
			}
			if(c-1 >= 0 && map[r][c] > map[r][c-1]) {
				dp[r][c] += dfs(r, c-1);
			}
		}
		return dp[r][c];
	}

}
