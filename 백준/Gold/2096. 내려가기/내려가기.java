import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int maxDP[][] = new int[N][3];
        int minDP[][] = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int temp = Integer.parseInt(st.nextToken());
                maxDP[i][j] = temp;
                minDP[i][j] = temp;
            }
        }

        // dp
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                // 좌상, 상, 우상 중 가장 큰(작은) 숫자 찾기
                int max = -1;
                int min = Integer.MAX_VALUE;
                // 좌상
                if (isIn(j - 1)) {
                    max = Math.max(max, maxDP[i - 1][j - 1]);
                    min = Math.min(min, minDP[i - 1][j - 1]);
                }
                // 상
                max = Math.max(max, maxDP[i - 1][j]);
                min = Math.min(min, minDP[i - 1][j]);
                // 우상
                if (isIn(j + 1)) {
                    max = Math.max(max, maxDP[i - 1][j + 1]);
                    min = Math.min(min, minDP[i - 1][j + 1]);
                }
                // dp 값 업데이트
                maxDP[i][j] += max;
                minDP[i][j] += min;
            }
        }

        // output
        int maxAns = -1, minAns = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            maxAns = Math.max(maxAns, maxDP[N - 1][i]);
            minAns = Math.min(minAns, minDP[N - 1][i]);
        }

        System.out.println(maxAns + " " + minAns);

    }

    private static boolean isIn(int x) {
        if (x < 0 || x >= 3) return false;
        else return true;
    }
}
