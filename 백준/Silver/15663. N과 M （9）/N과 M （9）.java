import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr, perm;
    static LinkedHashSet<String> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        perm = new int[M];
        ans = new LinkedHashSet<>();
        permutation(0, 0);

        for (String str : ans) {
            sb.append(str).append('\n');
        }
        System.out.print(sb);
    }

    static void permutation(int cnt, int visited) {
        if (cnt == M) {
            String str = "";
            for (int p : perm) {
                str += (p + " ");
            }
            ans.add(str);
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & 1<<i) == 0) {
                perm[cnt] = arr[i];
                permutation(cnt + 1, visited | 1<<i);
            }
        }
    }
}