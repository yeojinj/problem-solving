package D2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_2001_파리퇴치_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            int[][] arr = new int[N][N];
            int cnt = 0, ans = 0;
            for (int i = 0; i < N; i++) {
            	st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            for (int i = 0; i < N - M + 1; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    cnt = 0;
                    for (int k = i; k < i + M; k++) {
                        for (int l = j; l < j + M; l++) {
                        	cnt += arr[k][l];
                        }
                    }
                    if (ans < cnt) {
                    	ans = cnt;
                    }
                }
            }
            System.out.println("#" + tc + " " + ans);
		}
	}
}
