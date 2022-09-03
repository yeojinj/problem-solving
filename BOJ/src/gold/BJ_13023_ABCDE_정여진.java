package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_13023_ABCDE_정여진 {
	static int N, M;
	static List<Integer> arr[];		// 친구 관계 저장
	static boolean visited[];		
	static boolean ans = false;		// 문제의 조건에 맞는 친구 관계가 존재하면 true
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 사람의 수
		M = Integer.parseInt(st.nextToken());	// 친구 관계의 수
		arr = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// a와 b가 친구
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}
		
		int idx = 0;
		while(!ans && idx < N) {
			visited = new boolean[N];		// visited 초기화
			dfs(idx, 0);
			idx++;
		}
		// 출력
		if(ans == true) System.out.println(1);
		else System.out.println(0);
	}
	
	private static void dfs(int n, int depth) {
		visited[n] = true;
		
		if(depth == 4) {
			ans = true;
			return;
		}
		
		for(int i = 0; i < arr[n].size(); i++) {
			if(!visited[arr[n].get(i)]) {
				dfs(arr[n].get(i), depth + 1);
			}
		}
		
		visited[n] = false;
	}
}
