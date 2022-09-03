package gold;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16236_아기상어_정여진 {
	static int N;
	static int r, c;			// 아기 상어의 위치
	static int fishCnt = 0;		// 아기 상어가 먹은 물고기 수
	static int time = 0;		// 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹은 시간
	static int arr[][];
	static boolean visited[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// NxN 크기 공간
		arr = new int[N][N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());	// 공간의 상태
				if(arr[i][j] == 9) {
					r = i; c = j;	// 아기 상어의 위치
				}
			}
		}
		
		babyShark();
		
		System.out.println(time);
	}
	
	/**
	 * 아기 상어가 자신보다 작은 물고기를 먹으면서 크기를 키움
	 */
	private static void babyShark() {
		int sharkSize = 2;
		
		while(true) {
			visited = new boolean[N][N];
			int dist = bfs(sharkSize);
			if(dist != 0) {	// 물고기를 먹었으면
				fishCnt++;	// 먹은 물고기 수 증가
				time += dist;
				if(fishCnt == sharkSize) {
					sharkSize++;	// 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
					fishCnt = 0;
				}
			}
			else break;		// 물고기를 못 먹었으면
		}
	}
	
	/**
	 * 아기 상어가 bfs로 공간을 탐색하면서 먹을 수 있는 물고기가 있으면 먹음
	 * @param sharkSize 아기 상어의 크기
	 * @return 먹을 수 없으면 0, 먹을 수 있으면 거리
	 */
	private static int bfs(int sharkSize) {
		final int dx[] = {-1, 0, 0, 1};
		final int dy[] = {0, -1, 1, 0};
		
		int dist = 0;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		boolean flag = false;	// 물고기 먹었으면 true;
		int minDist = Integer.MAX_VALUE;		// 아기 상어가 먹을 수 있는 물고기 중 가장 가까운 물고기와의 거리
		int minX = N, minY = N;	// 그 물고기의 위치
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int j = 0; j < qSize; j++) {
				Point shark = q.poll();		// 아기 상어의 현재 위치
				visited[shark.x][shark.y] = true;
				for(int i = 0; i < 4; i++) {	// 상하좌우 탐색
					int sx = shark.x + dx[i];	// 아기 상어가 갈 위치(sx, sy)
					int sy = shark.y + dy[i];
					if(isIn(sx, sy) && !visited[sx][sy] && arr[sx][sy] <= sharkSize) {		// 이동할 수 있는 곳이면
						visited[sx][sy] = true;
						if(arr[sx][sy] != 0 && arr[sx][sy] < sharkSize) {	// 먹을 수 있는 물고기이면
							if(flag == true) {	// 먹을 수 있는 물고기가 여러 마리이면
								if(minDist > dist) {		// 아기 상어와 해당 물고기의 거리가 기존의 거리보다 가까우면
									minDist = dist;
									minX = sx; minY = sy;
								} else if(minDist == dist) {
									if(minX > sx) {		// 더 위에 있는 물고기라면
										minDist = dist;
										minX = sx; minY = sy;
									} else if(minX == sx) {	// 가장 위에 있는 물고기가 여러 마리라면
										if(minY > sy) {	// 가장 왼쪽에 있는 물고기라면
											minDist = dist;
											minX = sx; minY = sy;
										}
									}
								}
							} else {
								minDist = dist;
								minX = sx; minY = sy;
								flag = true;
							}
						}
						q.add(new Point(sx, sy));
					}	// 이동 종료
				}	// 상하좌우 탐색 종료
			}	// 큐의 현재 level(dist)에 있는 좌표 탐색 종료
			dist++;
			if(flag) {
				minDist++;
				arr[r][c] = 0;		// 공간의 상태 정보 update
				r = minX; c = minY;		// 아기 상어 이동
				arr[r][c] = 9;		// 공간의 상태 정보 update
				break;
			}
		}
		
		if(!flag) return 0;
		return minDist;
	}
	
	/**
	 * 좌표 (x, y)가 공간 안에 있는지 확인
	 * @param x
	 * @param y
	 * @return 공간 안에 있으면 true, 아니면 false
	 */
	private static boolean isIn(int x, int y) {
		if(x >= 0 && y >= 0 && x < N && y < N) {
			return true;
		} else return false;
	}
}
