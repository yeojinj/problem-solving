import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;

        int min = Integer.MAX_VALUE;
        int minStart = -1;
        int minEnd = -1;

        while (start < end) {
            int temp = Math.abs(arr[start] + arr[end]);
            if (min > temp) {
                min = temp;
                minStart = start;
                minEnd = end;
            }
            if (arr[start] + arr[end] < 0) {
                start++;
            } else if (arr[start] + arr[end] > 0) {
                end--;
            } else {
                break;
            }
        }

        System.out.println(arr[minStart] + " " + arr[minEnd]);
    }
}
