package silver;
import java.util.Scanner;

public class BJ_11659_구간합구하기4_정여진 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// 수의 개수
		int M = sc.nextInt(); 	// 합을 구해야 하는 횟수
		
		int arr[] = new int[N + 1];		// 수 저장하는 배열			// 인덱스 0은 사용 X
		int dp[] = new int[N + 1];		// 1~i 누적합 저장하는 배열
		arr[1] = sc.nextInt();
		dp[1] = arr[1];
		
		for (int i = 2; i <= N; i++) {
			arr[i] = sc.nextInt();		// N개의 수 입력받기
			dp[i] = dp[i-1] + arr[i];	// 1~i번째까지의 누적합 미리 계산
		}
		
		for (int idx = 0; idx < M; idx++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			System.out.println(dp[j] - dp[i - 1]); 	// 1~j번째까지의 합 - 1~(i-1)번째까지의 합 = i~j번째까지의 합
		}
	}
}
