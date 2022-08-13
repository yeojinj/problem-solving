package D3;
import java.util.Arrays;
import java.util.Scanner;

class D3_1208_Flatten_정여진 {
	public static void main(String args[]) throws Exception	{
		
		Scanner sc = new Scanner(System.in);
		int T = 10;	// 총 10개의 테스트 케이스
		
		for (int test_case = 1; test_case <= T; test_case++)	{
			int dump = sc.nextInt();	// 덤프 횟수
			int size = 100;				// 가로 길이
			int[] arr = new int[size];
			
			// 입력 받기
			for (int i = 0; i < 100; i++) {
				arr[i] = sc.nextInt();
			}
			
			Arrays.sort(arr);			// 정렬
			
			for (int i = 0; i < dump; i++) {
				arr[0]++;				// 가장 낮은 곳에서 +1
				arr[arr.length - 1]--;	// 가장 높은 곳에서 -1
				Arrays.sort(arr);		// 재정렬
			}
			
			int gap = arr[arr.length - 1] - arr[0];		// 가장 높은 곳과 낮은 곳의 차
			System.out.println("#" + test_case + " " + gap);	// 출력
		}
	}
}