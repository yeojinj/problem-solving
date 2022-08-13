package silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2961_도영이가만든맛있는음식_정여진 {
	
	static int sour[];		// 신맛
	static int bitter[];	// 쓴맛
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 재료의 개수
		
		sour = new int[N];
		bitter = new int[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = Integer.MAX_VALUE;	// 신맛과 쓴맛의 차이 최솟값
		
		for(int subset = 1; subset < 1<<N; subset++) {
			int sourProd = 1;
			int bitterSum = 0;
			
			for(int i = 0; i < N; i++) {		// 부분집합 선택
				if((1 & subset>>i) == 1) {
					sourProd *= sour[i];		// 신맛의 곱
					bitterSum += bitter[i];		// 쓴맛의 합
				}
			}
			
			if(ans > (int) Math.abs(sourProd - bitterSum)) {	// 최솟값 찾기
				ans = (int) Math.abs(sourProd - bitterSum);
			}
		}
		
		System.out.println(ans);
		
	}
}
