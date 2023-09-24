import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 시험장의 개수
		int arr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 각 시험장에 있는 응시자의 수
		}

		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken()); // 총감독관이 감시할 수 있는 응시자의 수
		int C = Integer.parseInt(st.nextToken()); // 부감독관이 감시할 수 있는 응시자의 수

		long ans = 0;
		for (int i = 0; i < N; i++) {
			arr[i] -= B;
			ans++;
			if (arr[i] > 0) {
				ans += (int) Math.ceil((double) arr[i] / C);
			}
		}

		System.out.println(ans);
	}
}