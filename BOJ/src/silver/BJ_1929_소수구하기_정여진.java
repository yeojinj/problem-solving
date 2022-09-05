package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1929_소수구하기_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N + 1];		// 0: 소수 , 1: 합성수
		int sq = (int)Math.sqrt(N);		
		
		for (int n = 2; n <= sq; n++) {
			for (int a = M; a <= N; a++) {
				if(a != n && a % n == 0) {
					arr[a] = 1;
				}
			}
		}
		
		arr[1] = 1;
		
		for (int i = M; i <= N; i++) {
			if(arr[i] == 0) {
				System.out.println(i);
			}
		}
	}
}
