package D4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1861_정사각형방_정여진 {

	static int N;
	static int rooms[][];
	static final int dx[] = {-1, 0, 1, 0};	// 상 좌 하 우
	static final int dy[] = {0, -1, 0, 1};
	static int max = 1;	// (i,j)를 기준으로 최대 몇 개의 방을 이동할 수 있는지
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 1;
			int ans = 1;		// 최대 몇 개의 방을 이동할 수 있는지
			int ansI = 0, ansJ = 0;	// 처음에 출발해야 하는 방 좌표
			int ansRoom = N*N + 1;	// 처음에 출발해야 하는 방 번호
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					DFS(i, j);
					if(ans < max || (ans == max && ansRoom > rooms[i][j])) {		// 이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 선택
						ans = max;
						ansI = i; ansJ = j;
						ansRoom = rooms[ansI][ansJ];
					}
					max = 1;
				}
			}
			System.out.println("#" + tc + " " + ansRoom + " " + ans);
		}
	}
	
	static void DFS(int i, int j) {
		int num = rooms[i][j];
		
		for(int dir = 0; dir < 4; dir++) {
			int ii = i + dx[dir];
			int jj = j + dy[dir];
					
			if(ii >= 0 && ii < N && jj >= 0 && jj < N) {	// 범위 체크
				if(num + 1 == rooms[ii][jj]) {			// 이동할 곳이 현재 방에 적힌 숫자보다 1 크면
					max++;
					DFS(ii, jj);
				}
			}
		}
		return;
	}
}
/*
2
2
1 2
3 4
3
9 3 4
6 1 5
7 8 2

#1 1 2
#2 3 3

*/