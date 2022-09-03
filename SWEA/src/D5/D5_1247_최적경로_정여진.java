package D5;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D5_1247_최적경로_정여진 {

	static int dist[][];
	static int DP[][];
	static int N;
	static boolean visited[];
	static int permutation[];
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	// 고객의 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			Point company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 회사 좌표
			Point home =  new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 집 좌표
			List<Point> points = new ArrayList<>();
			points.add(company);
			for(int i = 0; i < N; i++) {
				points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));	// 고객 좌표
			}
			points.add(home);
			
			dist = new int[N+2][N+2];	// 두 좌표 사이의 거리
			// 모든 좌표들에 대해 미리 계산
			for(int i = 0; i < N+2; i++) {
				for(int j = 0; j < N+2; j++) {
					if(dist[i][j] == 0) {
						dist[i][j] = Math.abs(points.get(i).x - points.get(j).x)
								+ Math.abs(points.get(i).y - points.get(j).y);
						dist[j][i] = dist[i][j];
					}
				}
			}
			
			visited = new boolean[N+1];		// 인덱스 1~N 사용
			permutation = new int[N];
			
			perm(0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			
			ans = Integer.MAX_VALUE;		// 초기화
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void perm(int cnt) {
		if(cnt == N) {		// N개 나열
			int total = 0;
			total += dist[0][permutation[0]];	// 회사 ~ 첫 번째 고객 사이 거리
			for(int i = 0; i < N - 1; i++) {
				total += dist[permutation[i]][permutation[i+1]];	// 고객 ~ 고객 사이 거리
				if(ans < total) return;		// 현재까지의 이동거리보다 경로가 길어지면 종료
			}
			total += dist[permutation[N - 1]][N+1];	// 마지막 고객 ~ 집 사이 거리
			ans = Math.min(ans, total);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i + 1]) {
				visited[i + 1] = true;
				permutation[cnt] = i + 1;		// 고객들의 조합을 구해서 permutation에 저장
				perm(cnt + 1);
				visited[i + 1] = false;
			}
		}
	}
}
