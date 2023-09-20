import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
	int idx;
	int value;
	
	public Point(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}
	
	@Override
	public int compareTo(Point o) {
		return this.value - o.value;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			pq.add(new Point(i, arr[i]));
		}
		
		int cnt = 0;
		Point prev = pq.poll();
		arr[prev.idx] = cnt;
		
		for (int i = 1; i < N; i++) {
			Point now = pq.poll();
			if (now.value != prev.value) {
				cnt++;
			}
			arr[now.idx] = cnt;
			prev = now;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(' ');
		}
		
		System.out.println(sb);
		
	}
}
