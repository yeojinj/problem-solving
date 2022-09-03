package D4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class D4_3234_준환이의양팔저울_정여진 {
	static int N, weights[];
	static int ans = 0;
	static int permutation[];
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스의 수
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	// 무게추의 수
			weights = new int[N];	// 각 무게추의 무게
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = 0;
			visited = new boolean[N];
			permutation = new int[N];
			perm(0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 추의 번호 0 ~ N-1 대한 순열을 permutation에 저장 */
	private static void perm(int cnt) {
		if(cnt == N) {
			weigh(0, 0, 0);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				permutation[cnt] = i;
				perm(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	/** permutation 순서대로 왼쪽 left 또는 오른쪽 right에 올리며 무게 비교  */
	private static void weigh(int idx, int left, int right) {
		if(left < right) {	// 오른쪽 무게의 총합이 왼쪽 무게의 총합보다 더 커지면 종료
			return;
		}
		
		if(idx == N) {	// 종료되지 않고 N개 모두 올리는데 성공하면 경우의 수 1 증가
			ans++;
			return;
		}
		
		weigh(idx + 1, left + weights[permutation[idx]], right);	// 왼쪽에 추가
		weigh(idx + 1, left, right + weights[permutation[idx]]);	// 오른쪽에 추가
	}
}
