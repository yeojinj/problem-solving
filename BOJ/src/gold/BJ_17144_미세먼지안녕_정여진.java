package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17144_미세먼지안녕_정여진 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 공기청정기 위치 찾기
		int aR = 0;	// 공기청정기의 행 첫번째 좌표, 항상 1번 열에 설치되어 있음
		for(int i = 0; i < R; i++) {
			if(map[i][0] == -1) {	// 공기청정기 위치
				aR = i;
				break;
			}
		}
		
		int[] dr = {-1, 1, 0, 0};	// 네 방향
		int[] dc = {0, 0, -1, 1};
		
		
		for(int t = 1; t <= T; t++) {	// 시간 경과
			// newMap 초기화
			int[][] newMap = new int[R][C];
			newMap[aR][0] = -1;
			newMap[aR + 1][0] = -1;
			
			// 미세먼지 확산
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] > 0) {	// 미세먼지
						int count = 0;	// 확산된 방향의 개수
						for(int d = 0; d < 4; d++) {	// 네 방향으로 확산
							int nr = i + dr[d];
							int nc = j + dc[d];
							if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == -1) continue;	// 공기청정기가 있거나, 칸이 없으면 확산 X
							newMap[nr][nc] += map[i][j]/5;
							count++;
						}
						newMap[i][j] += map[i][j] - (map[i][j]/5*count);	// 남은 미세먼지의 양
					}
				}
			}
			
			// map 복사
			for(int i = 0; i < R; i++) {
				System.arraycopy(newMap[i], 0, map[i], 0, map[i].length);
			}
			
			// 공기청정기 작동
			//  위쪽 - 반시계방향 순환
			int temp1 = 0;				// temp1: 이전 칸의 값, 여기서는 공기청정기에서 정화되어 나오는 바람
			for(int c = 1; c < C; c++) {	// 오른쪽으로 밀기, c = 0은 공기청정기 위치
				int temp2 = map[aR][c];	// temp2: 현재 칸의 값
				map[aR][c] = temp1;
				temp1 = temp2;
			}
			for(int r = aR - 1; r >= 0; r--) {	// 위로 밀기
				int temp2 = map[r][C - 1];
				map[r][C - 1] = temp1;
				temp1 = temp2;
			}
			for(int c = C - 2; c >= 0; c--) {	// 왼쪽으로 밀기
				int temp2 = map[0][c];
				map[0][c] = temp1;
				temp1 = temp2;
			}
			for(int r = 1; r < aR; r++) {	// 아래로 밀기, r = aR은 공기청정기 위치
				int temp2 = map[r][0];
				map[r][0] = temp1;
				temp1 = temp2;
			}
			//  아래쪽 - 시계방향 순환
			temp1 = 0;
			for(int c = 1; c < C; c++) {	// 오른쪽으로 밀기, c = 0은 공기청정기 위치
				int temp2 = map[aR + 1][c];
				map[aR + 1][c] = temp1;
				temp1 = temp2;
			}
			for(int r = aR + 2; r < R; r++) {	// 아래로 밀기
				int temp2 = map[r][C - 1];
				map[r][C - 1] = temp1;
				temp1 = temp2;
			}
			for(int c = C - 2; c >= 0; c--) {	// 왼쪽으로 밀기
				int temp2 = map[R - 1][c];
				map[R - 1][c] = temp1;
				temp1 = temp2;
			}
			for(int r = R - 2; r > aR + 1; r--) {	// 위로 밀기
				int temp2 = map[r][0];
				map[r][0] = temp1;
				temp1 = temp2;
			}
			
		}
		
		// 방에 남아있는 미세먼지의 양
		int ans = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] > 0) {
					ans += map[i][j];
				}
			}
		}
		
		System.out.println(ans);
	}
}
