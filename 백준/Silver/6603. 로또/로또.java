import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int R = 6;     // 수 몇 개 고를지
    static int k;
    static int[] arr, comb;     // arr: 집합 S, comb: 고른 수
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }

            arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            comb = new int[R];
            sb = new StringBuilder();
            combination(0, 0);
            System.out.println(sb);
        }
    }

    static void combination(int cnt, int start) {
        if (cnt == R) {
            for (int i = 0; i < R; i++) {
                sb.append(comb[i]).append(' ');
            }
            sb.append('\n');
        } else {
            for (int i = start; i < k; i++) {
                comb[cnt] = arr[i];
                combination(cnt + 1, i + 1);
            }
        }
    }
}
