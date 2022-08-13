package silver;
import java.util.Scanner;

public class BJ_1244_스위치켜고끄기_정여진 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// [입력] 스위치 개수
		int N = sc.nextInt();
		
		int[] arr = new int[N + 1];
		
		// [입력] 각 스위치의 상태
		for (int i = 1; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		// [입력] 학생수
		int studentNum = sc.nextInt();
		
		for(int n = 0; n < studentNum; n++) {
			// [입력] 학생의 성별. 남학생 : 1, 여학생 : 2
			int gender = sc.nextInt();
			if (gender == 1) {
				// [입력] 남학생이 받은 수
				int maleNum = sc.nextInt();
				
				// 남학생이 스위치 상태 바꿈
				for (int i = maleNum; i < arr.length; i += maleNum) {
					arr[i] = (arr[i] + 1) % 2;
				}
			} else if (gender == 2) {
				// [입력] 여학생이 받은 수
				int femaleNum = sc.nextInt();
				
				// 여학생이 스위치 상태 바꿈
				// - 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간(start~end) 찾기
				int start = femaleNum, end = femaleNum;
				while(true) {
					if((start-1) >= 1 && (end+1) < arr.length) {
						if(arr[start-1] == arr[end+1]) {
							start--;
							end++;
						} else {
							break;
						}
					} else {
						break;
					}
				}
				
				// - 해당 구간(start~end) 스위치 상태 바꿈
				for (int i = start; i <= end; i++) {
					arr[i] = (arr[i] + 1) % 2;
				}
			}
		}
		
		// 출력
		for (int i = 1; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
			if(i % 20 == 0) {
				System.out.println();
			}
		}
	}

}
