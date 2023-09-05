import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Node {

    double x;
    double y;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int left;
    int right;
    double dist;

    public Edge(int left, int right, double dist) {
        this.left = left;
        this.right = right;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.dist < o.dist) {
            return -1;
        }
        return 1;
    }
}

public class Main {

    static int root[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());        // 별의 개수
        root = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
        }

        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            nodeList.add(new Node(x, y));
        }

        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double d = dist(nodeList.get(i).x, nodeList.get(i).y, nodeList.get(j).x, nodeList.get(j).y);
                edgeList.add(new Edge(i, j, d));
            }
        }
        Collections.sort(edgeList);

        double ans = 0;

        for (int i = 0; i < edgeList.size(); i++) {
            Edge e = edgeList.get(i);

            if (find(e.left) != find(e.right)) {
                ans += e.dist;
                union(e.left, e.right);
            }
        }

        System.out.printf("%.2f", ans);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) {
            root[y] = x;
        } else {
            root[x] = y;
        }
    }

    static int find(int x) {
        if (root[x] != x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    static double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}