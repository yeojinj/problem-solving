import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());        // 난이도 의견의 개수
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());    // 난이도 의견
        }
        Arrays.sort(arr);

        int trim = (int) Math.round(N * 0.15);
        int sum = 0;

        for (int i = trim; i < N - trim; i++) {
            sum += arr[i];
        }

        System.out.println(Math.round((double) sum / (N - trim * 2)));
    }

}