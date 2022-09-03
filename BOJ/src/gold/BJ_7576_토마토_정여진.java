package gold;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576_토마토_정여진 {
	static int M, N;
	static int arr[][];
	static int days = 0, notRipen = 0;	// 경과 날짜, 안 익은 토마토의 개수
	static Queue<Point> q = new LinkedList<>();
	static int dr[] = {-1, 1, 0, 0};	// 상하좌우
	static int dc[] = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 상자의 가로
		N = Integer.parseInt(st.nextToken());	// 상자의 세로
		
		arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) notRipen++;	// 안 익은 토마토 개수 세기
				else if(arr[i][j] == 1) q.add(new Point(i, j));	// 익은 토마토 queue에 저장
			}
		}
		
		if(notRipen == 0) {
			System.out.println(0);	// 저장될 때부터 모두 익어있는 상태
			return;
		}
		
		bfs();
		
		if(notRipen == 0) System.out.println(days);	// 모두 익을 때까지의 최소 날짜
		else System.out.println(-1);	// 모두 익지는 못하는 상황
	}
	
	private static void bfs() {
		while(!q.isEmpty() && notRipen != 0) {
			int size = q.size();
			for(int i = 0; i < size; i++) {
				Point temp = q.poll();	// 익은 토마토
				for(int j = 0; j < 4; j++) {
					int nr = temp.x + dr[j];
					int nc = temp.y + dc[j];
					if(nr >= 0 && nc >= 0 && nr < N && nc < M && arr[nr][nc] == 0) {
						arr[nr][nc] = 1;
						q.add(new Point(nr, nc));
						notRipen--;
					}
				}
			}
			days++;
		}
	}
}
