import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	static int N;	// 정점의 개수
	static int[][] adj;		// 인접 행렬
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		adj = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			bfs(i);			
		}
		
		printArr(adj);
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N];
		for (int j = 0; j < N; j++) {
			if (adj[start][j] == 1) {
				q.offer(j);
				visited[j] = true;
			}
		}
		
		while (!q.isEmpty()) {
			int i = q.poll();
			
			for (int j = 0; j < N; j++) {
				if (adj[i][j] == 1 && !visited[j]) {
					q.offer(j);
					visited[j] = true;
				}
			}
			
			if (adj[start][i] != 1) {
				adj[start][i] = 1;
			}
		}
	}
	
	static void printArr(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}