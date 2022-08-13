package D3;
import java.util.Scanner;

class D3_2805_농작물수확하기_정여진 {
	public static void main(String args[]) throws Exception	{
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++)	{
			int N = sc.nextInt();
			sc.nextLine();
			int arr[][] = new int[N][N];
			
			// 입력 받기
			for (int i = 0; i < arr.length; i++) {
				String str = sc.nextLine();
				for (int j = 0; j < arr.length; j++) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}
			
			int mid = (arr.length - 1) / 2;		// 중간 index 값
			int sum = 0;						// 농작물의 가치 합
			
			// 상단 삼각형
			for (int i = 0; i <= mid; i++) {
				for (int j = mid - i; j <= mid + i; j++) {
					sum += arr[i][j];
				}
			}
			
			// 하단 삼각형
			for (int i = mid + 1; i < arr.length; i++) {
				for (int j = mid - (arr.length - i - 1); j <= mid + (arr.length - i - 1); j++) {
					sum += arr[i][j];
				}
			}
			
			System.out.println("#" + test_case + " " + sum);
		}
	}
}