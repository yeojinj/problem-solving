import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int depth[] = new int[N + 2];

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        treeSet.add(N + 1);

        depth[0] = -1;
        depth[N + 1] = -1;
        long count = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            depth[num] = Math.max(depth[treeSet.lower(num)], depth[treeSet.higher(num)]) + 1;
            treeSet.add(num);

            count += depth[num];
            System.out.println(count);
        }
    }
}