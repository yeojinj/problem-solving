import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());   // 에르다 노바 사용 횟수
        int M = Integer.parseInt(st.nextToken());   // 오리진 스킬 사용 횟수

        // 에르다 노바
        int arr1[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);

        int count = 0, coolDownTime = 0;
        for (int i = 0; i < N; i++) {
            if (coolDownTime <= arr1[i]) {
                count++;
                coolDownTime = arr1[i] + 100;
            }
        }
        sb.append(count).append(" ");

        // 오리진 스킬
        int arr2[] = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr2);

        count = 0; coolDownTime = 0;
        for (int i = 0; i < M; i++) {
            if (coolDownTime <= arr2[i]) {
                count++;
                coolDownTime = arr2[i] + 360;
            }
        }
        sb.append(count);

        System.out.println(sb);
    }

}