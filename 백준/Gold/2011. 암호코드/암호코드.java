import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	final static int MOD = 1000000;
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		if (str.charAt(0) == '0') { // 맨 처음이 0으로 시작하는 경우
			System.out.println(0);
			System.exit(0);
		}

		int size = str.length();
		dp = new int[size + 1];
		dp[0] = 1;

		for (int i = 1; i < size; i++) {
			if (str.charAt(i) == '0') {
				if (str.charAt(i - 1) == '1' || str.charAt(i - 1) == '2') {
					dp[i] = dp[i - 1] % MOD;
				} else {
					System.out.println(0);
					System.exit(0);
				}
			} else {

				if (str.charAt(i - 1) == '0') {
					dp[i] = dp[i - 1] % MOD;
				} else if (i + 1 < size && str.charAt(i + 1) == '0') {
					if (str.charAt(i) == '1' || str.charAt(i) == '2') {
						dp[i] = dp[i - 1] % MOD;
					} else {
						System.out.println(0);
						System.exit(0);
					}
				} else {
					if (str.charAt(i - 1) == '1') {
						if (i - 2 >= 0) {
							dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
						} else {
							dp[i] = (dp[i - 1] + 1) % MOD;
						}
					} else if (str.charAt(i - 1) == '2') {
						if (str.charAt(i) - '0' <= 6) {
							if (i - 2 >= 0) {
								dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
							} else {
								dp[i] = (dp[i - 1] + 1) % MOD;
							}
						} else {
							dp[i] = dp[i - 1] % MOD;
						}
					} else {
						dp[i] = dp[i - 1] % MOD;
					}
				}

			}
		}

		System.out.println(dp[size - 1]);
	}
}
