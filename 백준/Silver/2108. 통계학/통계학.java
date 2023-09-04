import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());        // 난이도 의견의 개수
        int[] arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());    // 난이도 의견
            sum += arr[i];
        }
        System.out.println(Math.round((float) sum / N));    // 산술평균

        Arrays.sort(arr);
        System.out.println(arr[(N - 1) / 2]);       // 중앙값

        int count = 1;
        int maxCount = 1;
        int mode = arr[N - 1];   // 첫 번째로 작은 최빈값
        int mode2 = -4001;       // 두 번째로 작은 최빈값
        for (int i = N - 2; i >= 0; i--) {
            if (arr[i] == arr[i + 1]) {
                count++;
            } else {
                if (maxCount == count) {
                    mode2 = mode;
                    mode = arr[i + 1];
                } else if (maxCount < count) {
                    maxCount = count;
                    mode2 = -4001;
                    mode = arr[i + 1];
                }
                count = 1;
            }
        }

        if (maxCount == count) {
            mode2 = mode;
            mode = arr[0];
        } else if (maxCount < count) {
            maxCount = count;
            mode2 = -4001;
            mode = arr[0];
        }
        
        if (mode2 == -4001) {
            System.out.println(mode);
        } else {
            System.out.println(mode2);              // 최빈값
        }

        System.out.println(arr[N - 1] - arr[0]);    // 범위
    }

}