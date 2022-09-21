package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11497_통나무건너뛰기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스의 수

		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());	// 통나무의 수
			int logs[] = new int[N];	// 각 통나무의 높이
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				logs[i] = Integer.parseInt(st.nextToken());	// 각 통나무의 높이 입력
			}
			
			Arrays.sort(logs);
			
			int newLogs[] = new int[N];		// 난이도가 가장 낮은 통나무의 배열
			
			if(N % 2 == 1) {	// 홀수
				for(int i = 0; i <= N/2; i++) {
					newLogs[i] = logs[i*2];
				}
				for(int i = N/2 + 1; i < N; i++) {
					newLogs[i] = logs[(N-i)*2-1];
				
				}
			} else {			// 짝수
				for(int i = 0; i < N/2; i++) {
					newLogs[i] = logs[i*2];
				}
				for(int i = N/2; i < N; i++) {
					newLogs[i] = logs[(N-i)*2-1];
				
				}
			}
			
			// 난이도 구하기
			int maxDiff = -1;
			for(int i = 0; i < N - 1; i++) {
				int diff = Math.abs(newLogs[i] - newLogs[i+1]);
				maxDiff = Math.max(maxDiff, diff);
			}
			int diff = Math.abs(newLogs[0] - newLogs[N-1]);
			maxDiff = Math.max(maxDiff, diff);
			
			System.out.println(maxDiff);
		}
	}
}
