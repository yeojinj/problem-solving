package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1074_Z_정여진 {
	static int count = 0;	// 몇 번째 방문인지 저장하는 변수
							// 시작점(0, 0)에서는 0
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		find(N, r, c);
		System.out.println(count);
	}
	
	static void find(int n, int r, int c) {
		if(n == 0) {
			return;
		}

		int pow = (int) Math.pow(2, n - 1);	// 4등분할 때 가운데 경계값
		
		if(r < pow) {
			if(c < pow) {
				find(n - 1, r, c);		// 1구역
			}
			else {
				count += pow * pow;		// 1구역 count
				find(n - 1, r, c - pow);	// 2구역
			}
		} else {
			if(c < pow) {
				count += pow * pow * 2;	// 1~2구역 count
				find(n - 1, r - pow, c);	// 3구역
			}
			else {
				count += pow * pow * 3;	// 1~3구역 count
				find(n - 1, r - pow, c - pow);	// 4구역으로 이동
			}
		}
	}
}
