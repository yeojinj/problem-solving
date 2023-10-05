import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int ans = 0;
		int count = 0;
		
		for (int i = 1; i < M - 1;) {
			if (count == N) {
				ans++;
				count--;
			}
			if (str.charAt(i - 1) == 'I' && str.charAt(i) == 'O' && str.charAt(i + 1) == 'I') {
				count++;
				i += 2;
			} else {
				i++;
				count = 0;
			}
		}
		
		if (count == N) {
			ans++;
		}
		
		System.out.println(ans);
	}
}