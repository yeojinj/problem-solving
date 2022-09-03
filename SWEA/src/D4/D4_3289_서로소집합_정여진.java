package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_3289_서로소집합_정여진 {
	static int root[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스의 수
		for(int tc = 1; tc <= T; tc++) {
			System.out.print("#" + tc + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());	// 집합의 개수
			root = new int[n + 1];
			for(int i = 1; i <= n; i++) {
				root[i] = i;
			}
			int m = Integer.parseInt(st.nextToken());	// 연산의 개수
		
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());	// 합집합 0, 같은 집합인지 확인 1
				int a = Integer.parseInt(st.nextToken());	
				int b = Integer.parseInt(st.nextToken());	 
				if(x == 0) {
					union(a, b);
				} else if (x == 1) {
					if(getRoot(a) == getRoot(b)) System.out.print(1);
					else System.out.print(0);
				}
			}
			System.out.println();
		}	// end testcase
	}
	
	private static void union(int a, int b) {
		a = getRoot(a);
		b = getRoot(b);
		
		if(a < b) root[b] = a;
		else root[a] = b;
	}
	
	private static int getRoot(int x) {
		if(root[x] == x) return x;
		else return root[x] = getRoot(root[x]);
	}
}