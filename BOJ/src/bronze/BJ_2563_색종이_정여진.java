package bronze;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2563_색종이_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 색종이의 수
		
		int arr[][] = new int[100][100];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), 
				y = Integer.parseInt(st.nextToken());		// 색종이를 붙인 위치
			for(int j = x; j < x + 10; j++) {
				for(int k = y; k < y + 10; k++) {
					arr[j][k] = 1;
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(arr[i][j] == 1) count++;
			}
		}
		
		System.out.println(count);
	}

}
