import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String B = st.nextToken();

        int minDiff = 51;
        for (int i = 0; i <= B.length() - A.length(); i++) {
            minDiff = Math.min(minDiff, countDiff(A, B.substring(i)));
        }

        System.out.println(minDiff);
    }

    static int countDiff(String X, String Y) {
        int diff = 0;   // 두 문자열의 차이
        for (int i = 0; i < X.length(); i++) {
            if (X.charAt(i) != Y.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
}
