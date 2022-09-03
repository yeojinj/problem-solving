package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1697_숨바꼭질_정여진 {
	static int ans = Integer.MAX_VALUE;
	static int visited[] = new int[100_001];	// 걸린 시간 저장 (0: 방문 X, 1 이상: 방문함)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 수빈이의 위치
		int K = Integer.parseInt(st.nextToken());	// 동생의 위치
		
		System.out.println(bfs(N, K));
	}
	
	private static int bfs(int N, int K) {
		Queue<Integer> q = new LinkedList<>();
		visited[N] = 1;
		q.add(N);
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			if(temp == K) return visited[temp] - 1;		// 동생을 찾으면 메서드 종료
			
			if(temp - 1 >= 0 && visited[temp - 1] == 0) {
				visited[temp - 1] = visited[temp] + 1;
				q.add(temp - 1);
			} 
			if(temp + 1 <= 100_000 && visited[temp + 1] == 0) {
				visited[temp + 1] = visited[temp] + 1;
				q.add(temp + 1);
			} 
			if(temp * 2 <= 100_000 && visited[temp * 2] == 0) {
				visited[temp * 2] = visited[temp] + 1;
				q.add(temp * 2);
			}
		}
		return -1;		// 동생을 찾지 못하는 경우(이 문제에서는 없음)
	}
}
