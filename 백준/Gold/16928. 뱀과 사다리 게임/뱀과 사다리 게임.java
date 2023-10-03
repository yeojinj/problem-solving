import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

class Node {
	int n;		// 칸 번호
	int cnt;	// n번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수
	public Node(int n, int cnt) {
		this.n = n;
		this.cnt = cnt;
	}
}

public class Main {

	static int MAX = 100;
	static int N, M;
	static LinkedList<Integer>[] adj;
	
	public static void main(String[] args) throws IOException {
		adj = new LinkedList[MAX + 1];
		for (int i = 0; i <= MAX; i++) {
			adj[i] = new LinkedList<>();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 사다리의 수
		M = Integer.parseInt(st.nextToken());	// 뱀의 수
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x].add(y);	// x번 칸에 도착하면 y번 칸으로 이동
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);	// u번 칸에 도착하면 v번 칸으로 이동
		}
		
		System.out.println(bfs(1, 0));		// 1번 칸에서 출발
	}
	
	static int bfs(int n, int cnt) {
		int ans = MAX;
		int[] dist = new int[MAX + 1];
		
		for (int i = 1; i <= MAX; i++) {
			dist[i] = MAX;
		}
		
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(n, cnt));
		dist[n] = cnt;
		
		while (!q.isEmpty()) {
			Node temp = q.poll();
			
			if (temp.n == 100) {	// 100번 칸에 도착하면
				ans = Math.min(ans, temp.cnt);		// 최단 거리 보장이 안 되므로
			} else {
				if (!adj[temp.n].isEmpty()) {	// 뱀 또는 사다리가 있는지 확인
					int nn = adj[temp.n].get(0);
					if (dist[nn] > temp.cnt) {
						q.offer(new Node(nn, temp.cnt));
						dist[nn] = temp.cnt;
					}
				} else {						// 주사위 굴리기 (뱀 또는 사다리가 있으면 반드시 이동을 해야함)
					for (int d = 1; d <= 6; d++) {
						int nn = temp.n + d;
						if (nn <= MAX && dist[nn] > temp.cnt + 1) {
							q.offer(new Node(nn, temp.cnt + 1));
							dist[nn] = temp.cnt + 1;
						}
					}
				}
			}
		}
		
		return ans;
	}

}