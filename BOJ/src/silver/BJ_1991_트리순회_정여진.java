package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
	int left = -1;
	int right = -1;
	
	public Node(int left, int right) {
		this.left = left;
		this.right = right;
	}
}

public class BJ_1991_트리순회_정여진 {
	
	static Node tree[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 이진 트리의 노드의 개수
		tree = new Node[N];
		
		for(int i = 0; i < N; i++) {
			String str[] = br.readLine().split(" ");
			tree[str[0].charAt(0) - 'A'] = new Node(str[1].charAt(0) - 'A', str[2].charAt(0) - 'A');
		}
		
		preorder(0);
		System.out.println(sb.toString());
		sb.setLength(0);
		inorder(0);
		System.out.println(sb.toString());
		sb.setLength(0);
		postorder(0);
		System.out.println(sb.toString());
		
	}
	
	// root > left > right
	static void preorder(int root) {
		sb.append((char)(root + 'A'));
		if(tree[root].left > 0) preorder(tree[root].left);
		if(tree[root].right > 0) preorder(tree[root].right);
	}
	
	// left > root > right
	static void inorder(int root) {
		if(tree[root].left > 0) inorder(tree[root].left);
		sb.append((char)(root + 'A'));
		if(tree[root].right > 0) inorder(tree[root].right);
	}
	
	// left > right > root
	static void postorder(int root) {
		if(tree[root].left > 0) postorder(tree[root].left);
		if(tree[root].right > 0) postorder(tree[root].right);
		sb.append((char)(root + 'A'));
	}
}
