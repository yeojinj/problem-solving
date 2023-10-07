import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static final int R = 3;
    static String[] mbti;
    static int[] comb;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());    // 테스트 케이스의 수

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            mbti = new String[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                mbti[i] = st.nextToken();
            }

            if (N > 32) {
                sb.append(0).append('\n');
            } else {
                comb = new int[R];
                ans = 12;
                combination(0, 0);
                sb.append(ans).append('\n');
            }
        }

        System.out.println(sb);
    }

    /**
     * @param start 뽑기 시작할 인덱스
     * @param cnt 지금까지 뽑은 개수
     */
    static void combination(int start, int cnt) {
        if (cnt == R) {
            int sum = 0;
            sum += getDist(mbti[comb[0]], mbti[comb[1]]);
            sum += getDist(mbti[comb[1]], mbti[comb[2]]);
            sum += getDist(mbti[comb[2]], mbti[comb[0]]);
            ans = Math.min(ans, sum);
        } else {
            for (int i = start; i < N; i++) {
                comb[cnt] = i;
                combination(i + 1, cnt + 1);
            }
        }
    }

    static int getDist(String a, String b) {
        int dist = 0;
        for (int i = 0; i < 4; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                dist++;
            }
        }
        return dist;
    }
}