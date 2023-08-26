// 크루스칼

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int left;
    int right;
    int weight;

    public Edge(int left, int right, int weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }
}

public class Main {
    static int root[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());     // 정점의 개수
        // 루트 초기화
        root = new int[V + 1];
        for(int i = 1; i <= V; i++) {
            root[i] = i;
        }

        int E = Integer.parseInt(st.nextToken());     // 간선의 개수
        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(A, B, C));
        }

        // 간선 정렬
        Collections.sort(edgeList, Comparator.comparingInt(a -> a.weight));

        int ans = 0;        // MST의 가중치

        // 가중치 작은 간선부터
        for (int i = 0; i < E; i++) {
            Edge edge = edgeList.get(i);
            if (find(edge.left) != find(edge.right)) {      // 사이클 검사
                union(edge.left, edge.right);               // MST에 포함시키기
                ans += edge.weight;
            }
        }

        System.out.println(ans);
    }

    // x를 포함하는 집합의 루트 찾기
    static int find(int x) {
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }

    // x, y를 포함하는 두 집합을 합집합
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x < y) root[x] = y;
        else root[y] = x;
    }
}