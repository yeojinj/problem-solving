package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_4485_녹색옷입은애가젤다지_정여진 {
	static class Edge implements Comparable<Edge>{
		int r;
		int c;
		int weight;
		public Edge(int r, int c, int weight) {
			this.r = r;
			this.c = c;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int N;
	static int[][] arr;
	static int[][] dist;
	static final int[] dc = {-1, 1, 0, 0};
	static final int[] dr = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 1;		// 몇 번째 테스트 케이스인지
		while(true) {
			N = Integer.parseInt(br.readLine());	// 동굴의 크기
			if(N == 0) break;

			arr = new int[N][N];
			dist = new int[N][N];	// (0,0)에서 해당 정점으로 가는 최단 거리 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());	// 도둑루피의 크기
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			// Dijkstra에 필요한 자료 구조
			PriorityQueue<Edge> fringe = new PriorityQueue<Edge>();
//			for(int i = 0; i < N; i++) {
//				Arrays.fill(dist[i], Integer.MAX_VALUE);
//			}
			fringe.add(new Edge(0, 0, arr[0][0]));		// 시작점 저장
			dist[0][0] = arr[0][0];
			
			while(!fringe.isEmpty()) {
				Edge v = fringe.poll();
				for(int d = 0; d < 4; d++) {
					int nr = v.r + dr[d];
					int nc = v.c + dc[d];
					if(isIn(nr, nc)) {
						if(dist[nr][nc] > dist[v.r][v.c] + arr[nr][nc]) {	// root~v > root~tree + tree~v
							dist[nr][nc] = dist[v.r][v.c] + arr[nr][nc];
							fringe.add(new Edge(nr, nc, dist[nr][nc]));
						}
					}
				}
//				System.out.println("====================");
//				for(int i = 0; i < N; i++) {
//					for(int j = 0; j < N; j++) {
//						System.out.print(dist[i][j] + " ");
//					}
//					System.out.println();
//				}
			}
			
			
			System.out.println("Problem " + T + ": " + dist[N - 1][N - 1]);
			T++;
		}
	}
	
	private static boolean isIn(int r, int c) {
		if(r >= 0 && c >= 0 && r < N && c < N) {
			return true;
		}
		return false;
	}
}

/*
3
5 5 4
3 9 1
3 2 7
5
3 7 2 0 1
2 8 0 9 1
1 2 1 8 1
9 8 9 2 0
3 6 5 1 5
7
9 0 5 1 1 5 3
4 1 2 1 6 5 3
0 7 6 1 6 8 5
1 1 7 8 3 2 3
9 4 0 7 6 4 1
5 8 3 2 4 8 3
7 4 8 4 8 3 4
0
*/

