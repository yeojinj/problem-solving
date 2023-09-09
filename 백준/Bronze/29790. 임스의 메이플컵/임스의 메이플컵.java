import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 해결한 문제 수
        int U = Integer.parseInt(st.nextToken());   // 유니온 레벨
        int L = Integer.parseInt(st.nextToken());   // 최고 레벨

        StringBuilder sb = new StringBuilder();
        if (N >= 1000) {
            if (U >= 8000 || L >= 260) {
                sb.append("Very Good");
            } else {
                sb.append("Good");
            }
        } else {
            sb.append("Bad");
        }

        System.out.println(sb);
    }

}