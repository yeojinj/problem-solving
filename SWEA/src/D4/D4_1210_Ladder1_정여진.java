package D4;
import java.util.Scanner;

class D4_1210_Ladder1_정여진 {
	public static void main(String args[]) throws Exception	{
		
		Scanner sc = new Scanner(System.in);
		int T = 10;		// 총 10개의 테스트 케이스
		
		for (int test_case = 1; test_case <= T; test_case++)	{
			int tc = sc.nextInt();
			int size = 100;
			int arr[][] = new int[size][size];	// 100x100 크기
			
			// 입력 받기
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			int ans = -1;
			
			int x = 0;
			for (; x < size; x++) {
				if(arr[size - 1][x] == 2) {		// 도착 지점이면
					// 사다리 올라가기
					int tempX = x;
					int y = size - 1;
					for (; y >= 0; y--) {	
						if (tempX - 1 >= 0 && arr[y][tempX - 1] == 1) {				// 왼쪽으로 이동 가능하면
							// 왼쪽으로 이동
							while(tempX - 1 >= 0) {	
								tempX--;
								if (y - 1 >= 0 && arr[y - 1][tempX] == 1) {			// 위로 이동 가능하면
									break;
								}
							}
						} else if (tempX + 1 < size && arr[y][tempX + 1] == 1) {	// 오른쪽으로 이동 가능하면
							// 오른쪽으로 이동
							while(tempX + 1 < size) {
								tempX++;
								if (y - 1 >= 0 && arr[y - 1][tempX] == 1) {			// 위로 이동 가능하면
									break;
								}
							}
						}
						
						// 왼쪽이나 오른쪽으로 이동할 수 없으면 위로 이동
					}
					
					// 출발 지점인지 확인

					ans = tempX;
					break;

				}
			}
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
}

/*
1
1 0 0 0 1 0 1 0 0 1
1 0 0 0 1 0 1 1 1 1
1 0 0 0 1 0 1 0 0 1
1 0 0 0 1 1 1 0 0 1
1 0 0 0 1 0 1 0 0 1
1 1 1 1 1 0 1 1 1 1
1 0 0 0 1 0 1 0 0 1
1 1 1 1 1 0 1 0 0 1
1 0 0 0 1 1 1 0 0 1
1 0 0 0 1 0 1 0 0 2

#1 4
*/