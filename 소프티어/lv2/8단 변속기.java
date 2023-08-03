import java.util.*;
import java.io.*;


public class Main
{
    public static int size = 8;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());

        if (x == 1) {
            int[] asc = {1, 2, 3, 4, 5, 6, 7, 8};

            for (int i = 1; i < size; i++) {
                x = Integer.parseInt(st.nextToken());
                if (x != asc[i]) {
                    System.out.println("mixed");
                    return;
                }
            }

            System.out.println("ascending");
            return;

        } else if (x == size) {
            int[] des = {8, 7, 6, 5, 4, 3, 2, 1};

            for (int i = 1; i < size; i++) {
                x = Integer.parseInt(st.nextToken());
                if (x != des[i]) {
                    System.out.println("mixed");
                    return;
                }
            }

            System.out.println("descending");
            return;

        } else {

            System.out.println("mixed");
            return;

        }
    }
}