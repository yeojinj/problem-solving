import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        int temp = score[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            if (temp <= score[i]) {
                ans += score[i] - (temp - 1);
                score[i] = temp - 1;
            }
            temp = score[i];
        }

        System.out.println(ans);
    }
}
