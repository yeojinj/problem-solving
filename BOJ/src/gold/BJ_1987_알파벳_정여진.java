package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1987_알파벳_정여진 {
	static int R, C;
	static int board[][];
	static boolean[] alphabet = new boolean[26];
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	// 세로
		C = Integer.parseInt(st.nextToken());	// 가로
		board = new int[R][C];
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j) - 'A';
			}
		}

		dfs(0, 0, 0);
		System.out.println(ans);	// 출력
	}
	
	static void dfs(int r, int c, int cnt) {
		if(alphabet[board[r][c]]) {		// 이미 나왔던 알파벳이면 dfs 종료
			ans = Math.max(ans, cnt);	// cnt: 말이 지나온 칸 수
			return;
		}
		
		alphabet[board[r][c]] = true;	// 나온 알파벳 체크
		for(int i = 0; i < 4; i++) {
			if(r + dr[i] >= 0 && c + dc[i] >= 0 && r + dr[i] < R && c + dc[i] < C) {
				dfs(r + dr[i], c + dc[i], cnt + 1);
			}
		}
		alphabet[board[r][c]] = false;	// 알파벳 체크 해제
	}
}
