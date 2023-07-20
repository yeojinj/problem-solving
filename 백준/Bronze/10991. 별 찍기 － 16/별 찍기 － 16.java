import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // output
        for (int i = 1; i <= N; i++) {
            // 공백
            for (int j = i; j < N; j++) {
                System.out.print(" ");
            }
            // 별
            for (int j = 1; j <= 2 * i - 1; j++) {
                if (j % 2 == 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }
}