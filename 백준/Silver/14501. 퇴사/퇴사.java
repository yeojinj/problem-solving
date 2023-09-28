import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Schedule {
	int T;	// 상담을 완료하는데 걸리는 기간
	int P;	// 상담을 했을 때 받을 수 있는 금액
	
	public Schedule(int t, int p) {
		T = t;
		P = p;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Schedule[] s = new Schedule[N + 1];
		int[] dp = new int[N + 1];		// i일부터 N일까지 상담했을 때 최대 수익 (초기값 0)
		
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			s[n] = new Schedule(t, p);
			dp[n] = 0;
		}
		
		for (int n = N; n >= 1; n--) {	// n: 현재 날짜
			// n일에 잡혀있는 상담을 할 경우
			if (n + s[n].T <= N + 1) {		// n일에 잡혀있는 상담을 퇴사 전에 할 수 있는지
				dp[n] = s[n].P;
				int max = 0;
				for (int i = 0; ;i++) {
					int nextDay = n + s[n].T + i;	// n일에 잡혀있는 상담을 한 후 그 다음 상담 가능한 날
					if (nextDay <= N && dp[nextDay] != -1) {
						max = Math.max(max, dp[nextDay]);
					} else {
						break;
					}
				}
				dp[n] += max;
			}
			
			// n일에 잡혀있는 상담을 하지 않을 경우
			for (int m = n + 1; m <= N; m++) {
				dp[n] = Math.max(dp[n], dp[m]);
			}
		}
		
		System.out.println(dp[1]);
	}
}