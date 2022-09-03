package gold;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3055_탈출_정여진 {
	static int R, C;
	static char[][] map;
	static int[][] time;	// 시간 저장 & visited check
	static List<Point> water;	// 물이 차있는 지역 좌표
	static boolean[][] waterVisited;
	static final int[] dr = {-1, 1, 0, 0};	// 네 방향
	static final int[] dc = {0, 0, -1, 1};
	static int ans = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	// 지도 크기
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];	// 지도
		time = new int[R][C];	// 시간 저장 & visited check
		for(int i = 0; i < R; i++) {
			Arrays.fill(time[i], -1);
		}
		int hR = R, hC = C;			// 고슴도치 초기 좌표
		water = new ArrayList<Point>();	// 물이 차있는 지역 좌표
		waterVisited = new boolean[R][C];	
		
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') {
					hR = i; hC = j;
				}
				if(map[i][j] == '*') {
					water.add(new Point(i, j));
					waterVisited[i][j] = true;
				}
			}
		}
		
		bfs(hR, hC);
		
		if(ans > 0) {
			System.out.println(ans);
		} else System.out.println("KAKTUS");	// 안전하게 비버의 굴로 이동할 수 없다면
	}
	
	private static void bfs(int hR, int hC) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(hR, hC));
		time[hR][hC] = 0;
		
		while(!q.isEmpty()) {
			// 물이 참
			int size = water.size();
			for(int i = 0; i < size; i++) {	// 모든 물이 차있는 지역에 대해
				for(int d = 0; d < 4; d++) {	// 사방으로 
					int nr = water.get(0).x + dr[d];
					int nc = water.get(0).y + dc[d];
					if(isIn(nr, nc) && map[nr][nc] != 'X' && map[nr][nc] != 'D' && map[nr][nc] != '*' && !waterVisited[nr][nc]) {	// 돌, 비버의 굴로 물이 이동 X
						map[nr][nc] = '*';
						water.add(new Point(nr, nc));
						waterVisited[nr][nc] = true;
					}
				}
				water.remove(0);
			}
			
			// 고슴도치 이동 (물, 돌 X)
			int qSize = q.size();
			for(int i = 0; i < qSize; i++) {
				Point temp = q.poll();	// 고슴도치의 위치
				if(map[temp.x][temp.y] == 'D') {	// 비버의 굴에 도착하면
					ans = time[temp.x][temp.y];		// 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간 저장
					break;
				}
				map[temp.x][temp.y] = 'S';	// 고슴도치 이동
				
				for(int d = 0; d < 4; d++) {
					int nr = temp.x + dr[d];
					int nc = temp.y + dc[d];
					if(isIn(nr, nc) && map[nr][nc] != 'X' && map[nr][nc] != '*') {	// 돌, 물로 고슴도치가 이동 X
						if(time[nr][nc] == -1) {	// not visited
							q.add(new Point(nr, nc));
							// 시간 경과
							time[nr][nc] = time[temp.x][temp.y] + 1;
						}
					}
				}
			}
		}
	}
	
	private static boolean isIn(int r, int c) {
		if(r >= 0 && c >= 0 && r < R && c < C) {
			return true;
		}
		return false;
	}
}
