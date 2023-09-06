import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int parentNode[];
    static boolean isLeaf[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());    // 노드의 개수
        parentNode = new int[N];                        // parent[i] = j : i번 노드의 부모는 j번 노드

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parentNode[i] = Integer.parseInt(st.nextToken());
        }

        // 노드 삭제
        int d = Integer.parseInt(br.readLine());   // 지울 노드의 번호
        deleteNode(d);

        // 리프 노드 표시
        isLeaf = new boolean[N];
        Arrays.fill(isLeaf, true);
        findLeafNode();

        // 리프 노드 개수 세기
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (isLeaf[i]) ans++;
        }
        System.out.println(ans);
    }

    static void deleteNode(int d) {
        parentNode[d] = -2;
        for (int i = 0; i < N; i++) {
            if (parentNode[i] == d) {
                deleteNode(i);
            }
        }
    }

    static void findLeafNode() {
        for (int i = 0; i < N; i++) {
            if (parentNode[i] >= 0) {
                isLeaf[parentNode[i]] = false;
            } else if (parentNode[i] == -2) {
                isLeaf[i] = false;
            }
        }
    }

}