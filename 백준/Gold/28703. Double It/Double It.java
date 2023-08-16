import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int firstMax = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            pq.add(temp);
            firstMax = Math.max(firstMax, temp);
        }

        int max = firstMax;
        int minAns = max - pq.peek();
        while (pq.peek() < firstMax) {
            int min = pq.poll();
            minAns = Math.min(minAns, max - min);
            pq.offer(min * 2);
            max = Math.max(max, min * 2);
        }

        minAns = Math.min(minAns, max - pq.peek());
        System.out.println(minAns);

    }

}
