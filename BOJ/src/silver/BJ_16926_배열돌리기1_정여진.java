package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_16926_배열돌리기1_정여진 {
	
	static int di[] = {-1, 1, 0, 0};	// 상 하 좌 우
	static int dj[] = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int arr[][] = new int[N + 1][M + 1];
		int newArr[][] = new int[N + 1][M + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 1; r <= R; r++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					if(j >= i+(M-N) && j > -i+(M+1) && j > M/2) {
						newArr[i + di[0]][j + dj[0]] = arr[i][j];	// 위로
					} else if(i >= j && j < -i+(N+1)) {
						newArr[i + di[1]][j + dj[1]] = arr[i][j];	// 아래로
					} else if(i <= N/2) {
						newArr[i + di[2]][j + dj[2]] = arr[i][j];	// 왼쪽으로
					} else {
						newArr[i + di[3]][j + dj[3]] = arr[i][j];	// 오른쪽으로
					}
				}
			}
			for(int k = 0; k < newArr.length; k++) {	// arr <- newArr 복사
				System.arraycopy(newArr[k], 0, arr[k], 0, newArr[k].length);
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}