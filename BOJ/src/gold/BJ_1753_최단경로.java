package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1753_최단경로 {
	static class Edge implements Comparable<Edge>{
		int v, weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		int K = Integer.parseInt(br.readLine());	// 시작 정점의 번호
		
		// 인접리스트 adj 초기화
		List<Edge>[] adj = new ArrayList[V + 1];	// 인덱스 0 사용 X
		for(int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {	// 간선 정보 입력
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());	// 정점
			int v = Integer.parseInt(st.nextToken());	// 정점
			int w = Integer.parseInt(st.nextToken());	// 가중치
			adj[u].add(new Edge(v, w));
		}
		
		boolean[] seen = new boolean[V + 1];		// 모든 정점 초기화 false: unseen / true: tree
		int[] dist = new int[V + 1];	// 시작 정점부터 각 정점으로 가는 최단 거리 저장, 인덱스 0 사용 X
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		
		// Dijkstra (unseen -> fringe -> tree)
		PriorityQueue<Edge> fringe = new PriorityQueue<>();		// adjacent한 정점들 저장
		fringe.add(new Edge(K, 0));
		
		while(!fringe.isEmpty()) {
			Edge v = fringe.poll();		// 최소 가중치인 edge
			if(!seen[v.v]) {
				seen[v.v]= true;	// v -> tree
				for(int i = 0; i < adj[v.v].size(); i++) {		
					if(dist[adj[v.v].get(i).v] > dist[v.v] + adj[v.v].get(i).weight) {
						dist[adj[v.v].get(i).v] = dist[v.v] + adj[v.v].get(i).weight;		// 
						fringe.add(new Edge(adj[v.v].get(i).v, dist[adj[v.v].get(i).v]));	// v와 adjacent한 정점들 -> fringe
					}
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
	        if (seen[i]) System.out.println(dist[i]);
	        else System.out.println("INF");
	    }
	}
}
