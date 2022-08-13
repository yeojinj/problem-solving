package D3;
import java.util.Scanner;
import java.io.FileInputStream;

class D3_1289_원재의메모리복구하기_정여진 {
	public static void main(String args[]) throws Exception	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		sc.nextLine();
		
		for (int test_case = 1; test_case <= T; test_case++)	{
			String str = sc.nextLine();
			
			char bit = '0';				// 이전 비트 저장, 초기값은 모든 비트가 0
			int count = 0;				// 비트가 0->1 또는 1->0으로 바뀌는 지점 카운트
			
			for (int i = 0; i < str.length(); i++) {
				if(str.charAt(i) != bit) {	// 현재 비트가 이전 비트와 다르면
					count++;
				}
				bit = str.charAt(i);		// 현재 비트 저장
			}
			
			// 출력
			System.out.printf("#%d %d\n", test_case, count);

		}
	}
}