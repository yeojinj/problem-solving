package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2023_신기한소수_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());		// N자리 수
		
		if(N == 1) {
			System.out.println(2);
			System.out.println(3);
			System.out.println(5);
			System.out.println(7);
		} else {
			for (int i = (int) Math.pow(10, N-1) + 1; i < (int) Math.pow(10, N); i += 2) {
				int temp = i / (int) Math.pow(10, N-1);
				if(temp == 2 || temp == 3 || temp == 5 || temp == 7) {
					if(isStrangePrime(i, N)) System.out.println(i);
				}
			}
		}
	}
	
	public static boolean isStrangePrime(int x, int N) {
		for(int tempN = 1; tempN <= N; tempN++) {
			if(!isPrime((int) (x / Math.pow(10, N - tempN)))) return false;
		}
		return true;
	}
	
	public static boolean isPrime(int x) {
		if(x <= 1) {
			return false;
		}
		for (int i = 2; i <= (int) Math.sqrt(x); i++) {
			if(x != i && x % i == 0) return false;
		}
		return true;
	}

}
