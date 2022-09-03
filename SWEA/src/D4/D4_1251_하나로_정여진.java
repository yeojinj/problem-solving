package D4;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int n1;
	int n2;
	long dist2;
	
	public Edge(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public void setDist2(Point p1, Point p2) {
		this.dist2 = (long)Math.abs(p1.x - p2.x) * (long)Math.abs(p1.x - p2.x) 
				+ (long)Math.abs(p1.y - p2.y) * (long)Math.abs(p1.y - p2.y);
	}
	
	@Override
	public int compareTo(Edge o) {
		return Long.compare(this.dist2, o.dist2);
	}
}

public class D4_1251_하나로_정여진 {
	static int root[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스의 수
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());	// 섬의 개수
			root = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				root[i] = i;
			}
			Point islands[] = new Point[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				islands[i] = new Point();		// 객체 생성
				islands[i].x = Integer.parseInt(st.nextToken());	// 각 섬들의 X 좌표
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				islands[i].y = Integer.parseInt(st.nextToken());	// 각 섬들의 Y 좌표
			}
			double E = Double.parseDouble(br.readLine());	// 해저터널 건설의 환경 부담 세율
			
			Edge graph[] = new Edge[N*(N-1)/2];
			
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j < N; j++) {
					graph[cnt] = new Edge(i, j);
					graph[cnt].setDist2(islands[i], islands[j]);
					cnt++;
				}
			}
			
			Arrays.sort(graph);
			
			long ans = 0;
			for(int i = 0; i < graph.length; i++) {
				if(getRoot(graph[i].n1) != getRoot(graph[i].n2)) {
					union(graph[i].n1, graph[i].n2);
					ans += graph[i].dist2;
				}
			}

			System.out.println("#" + tc + " " + Math.round(ans * E));
		}
	}
	
	private static void union(int x, int y) {
		x = getRoot(x);
		y = getRoot(y);
		
		if(x < y) root[y] = x;
    	else root[x] = y;
	}
	
	private static int getRoot(int x) {
		if(root[x] == x) return x;
		else return root[x] = getRoot(root[x]);
	}
}
