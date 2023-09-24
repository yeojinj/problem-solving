// 코드 참고
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] graph;
	static boolean[] visited;

	public static void dfs(int idx) {
		if (visited[idx] == true)
			return;
		else {
			visited[idx] = true;
			for (int i = 1; i <= N; i++) {
				if (graph[idx][i] == 1) {
					dfs(i);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		graph = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u][v] = graph[v][u] = 1;
		}

		int ans = 0;
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			if (visited[i] == false) {
				dfs(i);
				ans++;
			}
		}

		System.out.println(ans);
	}
}