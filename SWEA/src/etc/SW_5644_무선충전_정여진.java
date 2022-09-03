package etc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class TC {
	int X;
	int Y;
	int C;	// 충전기의 충전 범위
	int P;	// 충전기의 성능
	
	public TC(int x, int y, int c, int p) {
		X = x;
		Y = y;
		C = c;
		P = p;
	}
}

class Location{
	int X;
	int Y;
	
	public Location(int x, int y) {
		X = x;
		Y = y;
	}
}

public class SW_5644_무선충전_정여진 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int M, A;	// 총 이동 시간, 충전기의 개수
	static int moveA[];	// 사용자 A의 이동 정보
	static int moveB[];	// 사용자 B의 이동 정보
	static TC tc[];		// 충전기의 정보
	
	static final int[][] dir = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};	// 이동 방향
	static Location a = new Location(1, 1);		// 사용자 A의 현재 위치
	static Location b = new Location(10, 10);	// 사용자 B의 현재 위치
	static int ans = 0;		// 모든 사용자의 충전량의 합의 최대값
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			// 입력
			input();
			
			// 사용자 이동
			move();
			
			// 출력
			System.out.println("#" + tc + " " + ans);
			
			// 값 초기화
			a = new Location(1, 1);	
			b = new Location(10, 10);
			ans = 0;
		}
	}
	
	/** 테스트 케이스마다 정보 입력 받기 */
	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 총 이동 시간
		A = Integer.parseInt(st.nextToken());	// 충전기의 개수
		st = new StringTokenizer(br.readLine());

		moveA = new int[M + 1];
		for(int i = 1; i <= M; i++) {
			moveA[i] = Integer.parseInt(st.nextToken());	// 사용자 A의 이동 정보
		}
		st = new StringTokenizer(br.readLine());

		moveB = new int[M + 1];
		for(int i = 1; i <= M; i++) {
			moveB[i] = Integer.parseInt(st.nextToken());	// 사용자 B의 이동 정보
		}
		
		tc = new TC[A];
		for(int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());	// 충전기의 좌표(X, Y)
			int Y = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());	// 충전기의 충전 범위
			int P = Integer.parseInt(st.nextToken());	// 충전기의 성능
			tc[i] = new TC(X, Y, C, P);
		}
	}
	
	/** 사용자 A, B 이동 */
	public static void move() {
		for(int m = 0; m <= M; m++) {	// 이동 시간 경과
			// 이동
			a.X = a.X + dir[moveA[m]][0];
			a.Y = a.Y + dir[moveA[m]][1];
			b.X = b.X + dir[moveB[m]][0];
			b.Y = b.Y + dir[moveB[m]][1];
			
			// 사용자 현재 위치에서 충전 가능한 충전기들의 리스트 만들기
			List<Integer> chargeA = new ArrayList<>();
			List<Integer> chargeB = new ArrayList<>();
			for(int ia = 0; ia < A; ia++) {		// 모든 충전기에 대해 반복
				if(Math.abs(a.X - tc[ia].X) + Math.abs(a.Y - tc[ia].Y) <= tc[ia].C) {
					chargeA.add(ia);	// 사용자 A 현재 위치가 충전 범위에 속하면 충전기 번호 저장
				}
				if(Math.abs(b.X - tc[ia].X) + Math.abs(b.Y - tc[ia].Y) <= tc[ia].C) {
					chargeB.add(ia);	// 사용자 B 현재 위치가 충전 범위에 속하면 충전기 번호 저장
				}
			}
			
			int max = Integer.MIN_VALUE;	// 해당 시간(m)에 모든 사용자의 충전량의 합의 최대값
			
			if(chargeA.size() == 0) {
				if(chargeB.size() == 0) max = 0;	// 1) A, B 둘 다 충전 X
				else {
					for (int j = 0; j < chargeB.size(); j++) {	// 2) B만 충전
						max = Math.max(max, tc[chargeB.get(j)].P);
					}
				}
			} else if(chargeB.size() == 0) {
				for (int i = 0; i < chargeA.size(); i++) {	// 3) A만 충전
					max = Math.max(max, tc[chargeA.get(i)].P);
				}
			} else {	// 4) 둘 다 충전
				for (int i = 0; i < chargeA.size(); i++) {
					for (int j = 0; j < chargeB.size(); j++) {
						int temp = 0;		// 현재 충전 조합으로 계산했을 때 사용자 A와 B의 충전량의 합
						if(chargeA.get(i) == chargeB.get(j)) {	// 한 충전기에 두 명이 접속하면
							temp += tc[chargeA.get(i)].P;	// 두 명이 충전량 나눠가짐
						} else {
							temp += tc[chargeA.get(i)].P;
							temp += tc[chargeB.get(j)].P;
						}
						max = Math.max(max, temp);
					}
				}
			}
			ans += max;
		}
	}
}
