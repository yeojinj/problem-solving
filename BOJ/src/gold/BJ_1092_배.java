package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1092_배 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 크레인 수
		int crane[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			crane[i] = Integer.parseInt(st.nextToken());	// 각 크레인의 무게 제한
		}
		Arrays.sort(crane);
		
		int M = Integer.parseInt(br.readLine());	// 박스의 수
		ArrayList<Integer> box = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			box.add(Integer.parseInt(st.nextToken()));	// 각 박스의 무게
		}
		Collections.sort(box);
		
		if(box.get(M-1) > crane[N-1]) {		// 무게 제한이 가장 큰 크레인이 옮길 수 없는 박스가 있으면
			System.out.println(-1);
			System.exit(0);
		}
		
		int ans = 0;	// 모든 박스를 배로 옮기는데 드는 시간의 최솟값
		
		while(!box.isEmpty()) {
			int boxIdx = box.size() - 1;
			for(int c = N - 1; c >= 0;) {
				if(boxIdx == -1) break;
				if(box.get(boxIdx) <= crane[c]) {
					box.remove(boxIdx--);
					c--;
				}
				else boxIdx--;
			}
			ans++;	// 1분 경과
		}
		System.out.println(ans);
	}
}