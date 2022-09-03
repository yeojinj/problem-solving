package D4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D4_3124_최소스패닝트리_정여진 {
	static int root[];
	
	static class Edge implements Comparable<Edge>{
		int n1;		// 정점
		int n2;		// 정점
		int weight;	// 가중치
		public Edge(int n1, int n2, int weight) {
			super();
			this.n1 = n1;
			this.n2 = n2;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);	// 가중치 내림차순 정렬
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());	// 정점의 개수
			
			root = new int[V + 1];
			for(int i = 1; i <= V; i++) {
				root[i] = i;
			}
			
			int E = Integer.parseInt(st.nextToken());	// 간선의 개수
			Edge edges[] = new Edge[E];
			
			for(int i = 0; i < E; i++) {	
				st = new StringTokenizer(br.readLine());	// 각 간선에 대한 정보
				int a = Integer.parseInt(st.nextToken());	// 정점
				int b = Integer.parseInt(st.nextToken());	// 정점
				int c = Integer.parseInt(st.nextToken());	// 가중치
				edges[i] = new Edge(a, b, c);
			}
			
			Arrays.sort(edges); 	// 가중치 내림차순 정렬
			
			int cnt = 0;
			long ans = 0;
			
			for(int i = 0; i < E; i++) {
				if(union(edges[i].n1, edges[i].n2)) {
					ans += edges[i].weight;
					if(++cnt == V - 1) break;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		if(a < b) root[b] = a;
		else root[a] = b;
		return true;
	}
	
	private static int find(int x) {
		if(root[x] == x) return x;
		else return root[x] = find(root[x]);
	}
}