package gold;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17135_캐슬디펜스_정여진 {
	static int N, M, D, ans;
	static int[][] map, originMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());	// 행
        M = Integer.parseInt(st.nextToken());	// 열
        D = Integer.parseInt(st.nextToken());	// 궁수의 공격 거리 제한
 
        map = new int[N][M];
        originMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        ans = Integer.MIN_VALUE;	// 궁수의 공격으로 제거할 수 있는 적의 최대 수
        int[] anchers = new int[3];
        comb(0, 0, anchers);
		
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void initMap() {
		for(int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(originMap[i], M);
		}
	}
	
	static void comb(int s, int r, int[] archers) {
		if(r == 3) {	// 3칸 선택 조합 완성
			shoot(archers);
			return;
		}
		
		for(int i = s; i < M; i++) {
			archers[r] = i;
			comb(i+1, r+1, archers);	// 재귀 호출
		}
	}
	
	static void shoot(int[] archers) {
		initMap();		// map을 originMap으로 초기화
		
		int kills = 0;	// 제거한 적의 수
		
		for(int n = N - 1; n >= 0; n--) {	// map을 한 줄씩 움직이면서
			boolean targeted[][] = new boolean[n + 1][M];
			for(int a = 0; a < 3; a++) {	// 궁수 3명이 공격함
//				System.out.println((a + 1) + "번 궁수 위치: " + archers[a]);
				int d = D + 1;			// 현재 궁수의 공격 거리(최소)
				int tempR = N, tempC = M;	// 현재 궁수의 공격 좌표
				
				// map 전체 탐색
				for(int i = 0; i <= n; i++) {	// n: 턴마다 -1 (적이 한 줄씩 아래로 이동)
					for(int j = 0; j < M; j++) {
						if(map[i][j] == 1) {	// 적 발견
							int distance = Math.abs(i - (n + 1)) + Math.abs(j - archers[a]);
							if(d > distance) {	
								d = distance;
								tempR = i; tempC = j;
							} else if(d == distance) {
								if(tempC > j) {		// 가장 왼쪽의 적인지 확인
									tempR = i; tempC = j;
								}
							}
						}
					}
				} // end searching map (by one archer)
				
				if(d <= D) targeted[tempR][tempC] = true;
			} // end all archers shooting
			
			for(int i = 0; i <= n; i++) {
				for(int j = 0; j < M; j++) {
					if(targeted[i][j] == true) {
						map[i][j] = 0;
						kills++;
					}
				}
			}
			
		}	// end all turns
		ans = Math.max(ans, kills);		// 궁수의 공격으로 제거할 수 있는 적의 최대 수 저장
	}
}
