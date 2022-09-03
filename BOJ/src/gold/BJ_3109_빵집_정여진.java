package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3109_빵집_정여진 {
	static int R;
	static int C;
	static char map[][];
	static boolean visited[][];
	static boolean flag;	// 첫째 열의 해당 행에서 출발했을 때 파이프 설치 완료했으면 true
	static int ans;			// 가스관과 빵집을 연결하는 파이프라인의 최대 개수
	
	public static void main(String[] args) throws IOException {
		input();	// 입력 받기
		
		for(int i = 0; i < R; i++) {
			dfs(i, 0);		// 첫째 열에서 dfs 시작
			flag = false;	// 플래그 초기화
		}
		
		System.out.println(ans);	// 출력
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
	}
	
	static void dfs(int r, int c) {
		if(visited[r][c] == true || map[r][c] == 'x') return;
		
		visited[r][c] = true;

		if(c == C - 1) {	// 원웅이의 빵집 도착
			ans++;
			flag = true;	// 파이프 설치 완료
			return;
		}

		if(r - 1 >= 0) dfs(r-1, c+1);			// 우상
		if(flag == true) return;
		dfs(r, c+1);							// 우
		if(flag == true) return;
		if(r + 1 < R) dfs(r+1, c+1);			// 우하
	}
}
