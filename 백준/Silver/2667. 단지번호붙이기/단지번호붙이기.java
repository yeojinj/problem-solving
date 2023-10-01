import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Point {
	int r;
	int c;
	
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		List<Integer> ans = new ArrayList<>();
		int num = 1;	// 단지 번호
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (canGo(i, j)) {
					ans.add(bfs(i, j, num));
					num++;
				}
			}
		}
		
		Collections.sort(ans);
		
		StringBuffer sb = new StringBuffer();
		for (int x : ans) {
			sb.append(x).append('\n');
		}
		
		System.out.println(num - 1);	// 총 단지수
		System.out.println(sb);			// 각 단지내 집의 수
	}
	
	/**
	 * @param sr, sc 탐색 시작 좌표
	 * @param num 단지 번호
	 * @return 각 단지내 집의 수
	 */
	static int bfs(int sr, int sc, int num) {
		int count = 0;	// 각 단지내 집의 수
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(sr, sc));
		visited[sr][sc] = true;
		map[sr][sc] = num;
		count++;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (canGo(nr, nc)) {
					q.offer(new Point(nr, nc));
					visited[nr][nc] = true;
					map[nr][nc] = num;
					count++;
				}
			}
		}
		
		return count;
	}
	
	static boolean canGo(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N && !visited[r][c] && map[r][c] != 0;
	}
}