package silver;
import java.io.StringReader;
import java.util.*;

/*
## BJ_1260_DFS와BFS
https://www.acmicpc.net/problem/1260

-- 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과 출력
-- 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
-- 더 이상 방문할 수 있는 점이 없는 경우 종료
-- 정점 번호는 1번부터 N번
-- output
	>> 시작정점(V) 부터 방문된 정점 순서대로 출력
	>> dfs 수행결과
	>> bfs 수행결과

## input.txt
//tc1
4 5 1	// N:정점갯수 M:간선갯수 V:시작정점번호
1 2		// 간선열결 두 정점 번호, 간선 양방향
1 3
1 4
2 4
3 4
//tc2
5 5 3
5 4
5 2
1 2
3 4
3 1
//tc3
1000 1 1000
999 1000

## output.txt
//tc1
1 2 4 3
1 2 3 4
//tc2
3 1 2 5 4
3 1 4 2 5
//tc3
1000 999
1000 999
*/

public class BJ_1260_DFS와BFS_배포_debug {
	static String src = "4 5 1\r\n" + 
			"1 2\r\n" + 
			"1 3\r\n" + 
			"1 4\r\n" + 
			"2 4\r\n" + 
			"3 4";
	
	static int map[][];
	static boolean [] visited;
	static int N, M, V;
	
	static StringBuilder answer = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in = new Scanner(new StringReader(src));
		
		N = in.nextInt();			// 정점 개수
		M = in.nextInt();			// 간선 개수
		V = in.nextInt();			// 탐색 시작할 정점의 번호
		map = new int[N+1][N+1];	// 각 정점간 탐색 경로 저장(인접행렬) 
		visited = new boolean[N+1];	// 정점 탐색 여부 체크 배열
		
		for(int i = 1; i <= M; i++) {	// 정점 연결정보 초기설정
			int x = in.nextInt();
			int y = in.nextInt();
			map[x][y] = map[y][x] = 1;	// 양방향 설정: 연결시 1 설정
		}
		
		//-- break point
		//-- input: debug 간선연결정보
		//System.out.println("--------------");
		//for(int[] m : map) System.out.println(Arrays.toString(m));
		
		dfs(V);	 // break point: 시작 정점 번호 인자 전달
		answer.append("\n");
		
		Arrays.fill(visited, false);	
		//for(int i = 1; i <= N; i++) visited[i] = false;
		//visited = new boolean[N+1];
		
		bfs(V);  //-- break point: 시작 정점 번호 인자 전달
		
		System.out.println(answer);
		in.close();
	}

	/* 인접행렬 dfs : 재귀 */
	public static void dfs(int s) {
		if (visited[s]) {	//-- break point	
			return;
		}
		
		visited[s] = true;	
		answer.append(s).append(" ");
		
		for(int j = 1; j <= N; j++) {  
			if(map[s][j] == 1) {    
				dfs(j);		
			}
		}
	}
	
	/* 인접행렬 bfs : Queue */
	public static void bfs(int s) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(s);					
		visited[s] = true;			
		
		while(!queue.isEmpty()) {		
			int vertex = queue.poll();	
			answer.append(vertex).append(" "); //-- break point
			
			for(int k = 1; k <= N; k++) {	
				if(map[vertex][k] == 1 
						&& visited[k] == false) { 
					queue.offer(k);			
					visited[k] = true;	
				}
			}
		}
	}
}

/*
// input
[0, 0, 0, 0, 0]
[0, 0, 1, 1, 1]
[0, 1, 0, 0, 1]
[0, 1, 0, 0, 1]
[0, 1, 1, 1, 0]

// output
1 2 4 3 
1 2 3 4 

*/