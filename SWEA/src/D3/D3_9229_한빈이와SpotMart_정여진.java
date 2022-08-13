package D3;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_9229_한빈이와SpotMart_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());	// 테스트 케이스의 수
		for(int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 과자 봉지의 개수
			int M = Integer.parseInt(st.nextToken());	// 무게 합 제한
			
			st = new StringTokenizer(br.readLine());
			int weight[] = new int[N];
			for(int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());	// 각 과자 봉지의 무게
			}
			
			int ans = -1;
			
			Loop1:
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					int temp = weight[i] + weight[j];
					if(temp <= M && temp > ans) {		// 두 봉지 합이 제한보다 작거나 같으면서 가장 가까운 값일 때
						ans = temp;
						if(ans == M) break Loop1;		// 이중 for문 빠져나감
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}