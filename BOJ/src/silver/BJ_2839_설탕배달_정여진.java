package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2839_설탕배달_정여진 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 0; 	// 봉지 개수
		
		while(N >= 3) {			// 남은 설탕이 3보다 적어지면 종료
			if(N % 5 == 0) {	// 남은 설탕이 5로 나누어 떨어지면 남은 것 전부 5킬로그램으로 배달
				cnt += N / 5;
				N = 0;
			} else {
				N -= 3;			// 5로 나누어 떨어지지 않으면 3킬로그램 1개 배달
				cnt++;
			}
		}
		
		if(N == 0) System.out.println(cnt); // 남은 설탕이 없으면 봉지 개수 출력
		else System.out.println(-1);	// 남은 설탕이 있으면 -1 출력
	}
}