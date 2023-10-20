import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // A의 개수
            int M = Integer.parseInt(st.nextToken());   // B의 개수
            int[] A = new int[N];
            int[] B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(A);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(B);

            int ans = 0;
            for (int a : A) {
                // B에 대해 이분탐색
                int start = 0;
                int end = M - 1;
                int idx = 0;    // B 배열 인덱스 중
                                // 선택된 a보다 크기가 작음을 만족하지 않는 첫 위치

                while (start <= end) {
                    int mid = (start + end) / 2;
                    if (a <= B[mid]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                        idx = start;    // 조건(먹이 B가 a보다 작아야 함)을 만족할 때마다 업데이트
                    }
                }

                ans += idx;     // idx가 곧 해당 a가 먹을 수 있는 먹이의 개수를 의미함
            }

            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }

}
