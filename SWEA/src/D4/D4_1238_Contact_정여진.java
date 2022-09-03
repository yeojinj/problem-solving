package D4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_1238_Contact_정여진 {
	static int S; 		// 데이터의 길이
	static int start; 	// 시작점
	static int size = 100; 	// 최대연락 인원
	static int[][] graph; 	// 비상연락망
	static boolean[] visited; 	// 연락 받은 사람
	static Queue<Integer> queue;	// bfs를 위한 queue
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = 10;
    	for(int tc = 1; tc <= T; tc++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		graph = new int[size + 1][size + 1];
    		queue = new LinkedList<>();
    		visited = new boolean[size + 1];
    		
    		S = Integer.parseInt(st.nextToken());
    		start = Integer.parseInt(st.nextToken());
    		
    		st = new StringTokenizer(br.readLine());
    		
    		for(int i = 0; i < S/2; i++) {
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			graph[x][y] = 1;
    		}	
    		
    		System.out.print("#" + tc + " ");
    		bfs(start);
    	}
    }
    
    public static void bfs(int start) {
    	queue.offer(start);
    	visited[start] = true;
    	int max = 0; 	// 숫자가 가장 큰 사람
    	ArrayList<Integer> list = new ArrayList<>(); // max 저장하는 리스트
    	
    	while(!queue.isEmpty()) {
    		int qSize = queue.size();	// queue 크기가 중간에 변하므로 미리 저장
    		for(int t = 0; t < qSize; t++) {
				int cur = queue.poll();	// cur가 연락할 수 있는 사람들 찾기
				for(int i = 1; i < size; i++) {
					if(graph[cur][i] == 1 && visited[i] == false) {
						queue.offer(i);
						visited[i] = true;
						max = Math.max(max, i);
					}
				}
    		}
    		list.add(max);
    		max = 0;
    	}
    	
    	// 마지막에 연락 받은 사람들 중 최댓값 출력
    	System.out.println(list.get(list.size()-2));
    }
}