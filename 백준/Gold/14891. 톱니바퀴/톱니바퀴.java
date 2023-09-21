import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int N = 4;		// 톱니바퀴 개수
	static final int M = 8;		// 톱니바퀴 한 개당 톱니의 개수
	static boolean[][] gear;
	static int[] left, right;
	
	public static void main(String[] args) throws IOException {
		gear = new boolean[N][M];		// gear[i][j] : i번 톱니바퀴의 j번째 톱니 (false: N극 true: S극)
		left = new int[N];			// left[i] = j : i번 톱니바퀴의 9시 방향 톱니는 j번째 톱니
		right = new int[N];			// right[i] = j : i번 톱니바퀴의 3시 방향 톱니는 j번째 톱니
		for (int i = 0; i < N; i++) {
			left[i] = 6;
			right[i] = 2;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				gear[i][j] = (input.charAt(j) == '1' ? true : false);
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());	// 회전시킨 톱니바퀴의 번호
			n--;										// 인덱스 번호에 맞게 1 감소
			int[] d = new int[N];
			d[n] = Integer.parseInt(st.nextToken());	// 회전시킨 방향(1 시계 방향, -1 반시계 방향)
			
			switch (n) {
			case 0:		// 0 -> 1 -> 2 -> 3
				d[1] = checkRight(0, d[0]);
				d[2] = checkRight(1, d[1]);
				d[3] = checkRight(2, d[2]);
				rotateGear(0, d[0]);
				rotateGear(1, d[1]);
				rotateGear(2, d[2]);
				rotateGear(3, d[3]);
				break;
			case 1:		// 0 <- 1 -> 2 -> 3
				d[0] = checkLeft(1, d[1]);
				d[2] = checkRight(1, d[1]);
				d[3] = checkRight(2, d[2]);
				rotateGear(1, d[1]);
				rotateGear(0, d[0]);
				rotateGear(2, d[2]);
				rotateGear(3, d[3]);
				break;
			case 2:		// 0 <- 1 <- 2 -> 3
				d[1] = checkLeft(2, d[2]);
				d[3] = checkRight(2, d[2]);
				d[0] = checkLeft(1, d[1]);
				rotateGear(2, d[2]);
				rotateGear(1, d[1]);
				rotateGear(3, d[3]);
				rotateGear(0, d[0]);
				break;
			case 3:		// 0 <- 1 <- 2 <- 3
				d[2] = checkLeft(3, d[3]);
				d[1] = checkLeft(2, d[2]);
				d[0] = checkLeft(1, d[1]);
				rotateGear(3, d[3]);
				rotateGear(2, d[2]);
				rotateGear(1, d[1]);
				rotateGear(0, d[0]);
				break;
			}
		}
		
		// 점수 계산
		int score = 0;
		for (int i = 0; i < N; i++) {
			int mid = (right[i] - 2 + M) % M;	// 12시 방향에 있는 톱니 번호
			if (gear[i][mid]) {		// S극이면
				score += (1 << i);
			}
		}
		
		System.out.println(score);
	}
	
	// n번 톱니바퀴를 d 방향으로 회전
	static void rotateGear (int n, int d) {
		if (d == 1) {			// 시계 방향
			left[n] = (left[n] - 1 + M) % M;
			right[n] = (right[n] - 1 + M) % M;
		} else if (d == -1) {	// 반시계 방향
			left[n] = (left[n] + 1) % M;
			right[n] = (right[n] + 1) % M;
		}
		// d == 0일 때는 아무것도 안함
	}
	
	// n번 톱니바퀴가 d 방향으로 회전한 후일 때, 왼쪽 톱니바퀴가 회전하는 방향
	static int checkLeft(int n, int d) {
		if (d == 0) return 0;
		
		// n의 왼쪽과 n-1의 오른쪽 확인
		if (gear[n][left[n]] == gear[n - 1][right[n - 1]]) {
			return 0;
		} else {
			return d * (-1);
		}
	}
	
	// n번 톱니바퀴가 d 방향으로 회전한 후 일때, 오른쪽 톱니바퀴가 회전하는 방향
	static int checkRight(int n, int d) {
		if (d == 0) return 0;
		
		// n의 오른쪽과 n+1의 왼쪽 확인
		if (gear[n][right[n]] == gear[n + 1][left[n + 1]]) {
			return 0;
		} else {
			return d * (-1);
		}
	}
}