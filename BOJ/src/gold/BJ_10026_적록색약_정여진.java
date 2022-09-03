package gold;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_10026_적록색약_정여진 {
	static int N;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 그림의 크기 NxN
		char[][] rgb = new char[N][N];	// 적록색약이 아닌 사람이 본 그림
		char[][] rrb = new char[N][N];	// 적록색약인 사람이 본 그림
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				rgb[i][j] = str.charAt(j);
				rrb[i][j] = rgb[i][j];
				if(rrb[i][j] == 'G') rrb[i][j] = 'R';
			}
		}
		// 적록색약이 아닌 경우
		System.out.print(getArea(rgb) + " ");
		// 적록색약인 경우
		System.out.println(getArea(rrb));
	}
	
	/** 구역의 개수 return */
	private static int getArea(char[][] drawing) {
		int area = 0;
		
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				q.add(new Point(i, j));
				visited[i][j] = true;
				area++;
				
				while(!q.isEmpty()) {
					Point p = q.poll();
					for(int dir = 0; dir < 4; dir++) {
						int nr = p.x + dr[dir];
						int nc = p.y + dc[dir];
						if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
						if(drawing[p.x][p.y] == drawing[nr][nc]) {
							q.add(new Point(nr, nc));
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		
		return area;
	}
}
