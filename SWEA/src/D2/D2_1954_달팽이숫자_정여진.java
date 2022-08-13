package D2;
import java.util.Scanner;

public class D2_1954_달팽이숫자_정여진 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++)	{
			int N = sc.nextInt();
			int arr[][] = new int[N][N];
			
			int count = 1;
			int frame = 0;
			while (true) {
				// 위쪽에서 →
				for (int i = (0 + frame); i < N - frame; i++) {
					arr[frame][i] = count++;
				}
				
				if (count >= N*N) {
					break;
				}
				
				// 오른쪽에서 ↓
				for (int i = frame + 1; i < N - frame; i++) {
					arr[i][N - 1 - frame] = count++;
				}
				
				// 아래쪽에서 ←
				for (int i = (N - 2 - frame); i >= frame; i--) {
					arr[N - 1 - frame][i] = count++;
				}
				
				if (count >= N*N) {
					break;
				}
				
				// 왼쪽에서 ↑
				for (int i = (N - 2 - frame); i >= frame + 1; i--) {
					arr[i][frame] = count++;
				}
				
				frame++;
			}
			
			// 출력
			System.out.println("#" + test_case);
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
