package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11660_구간합구하기5_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 수의 개수
		int M = Integer.parseInt(st.nextToken()); 	// 합을 구해야 하는 횟수
		
		int arr[][] = new int[N + 1][N + 1];		// 수 저장하는 배열			// 인덱스 0은 사용 X
		int dp[][] = new int[N + 1][N + 1];			// (1,1)~(i,j) 누적합 저장하는 배열
		
		for (int i = 1; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());	// 표에 채울 수 입력 받기
				dp[i][j] = arr[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];	// (1,1)~(i,j) 누적합 미리 계산
			}
		}
		
		for (int idx = 0; idx < M; idx++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			System.out.println(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1]); 	// x2y2 - (x1-1)y2 - x2(y1-1) + (x1-1)(y1-1)
		}
	}
}
