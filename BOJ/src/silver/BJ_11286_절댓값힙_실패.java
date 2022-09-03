package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_11286_절댓값힙_실패 {
	static ArrayList<Integer> heap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		heap = new ArrayList<>();
		heap.add(0, 0); 	// 루트 노드는 인덱스 1부터 시작, 인덱스 0은 사용 X
		
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {	// 배열에서 절댓값이 가장 작은 값 출력
				System.out.println(delete());
			} else {		// 배열에 x를 추가
				upheap(x);
			}
			
			// ====================test===================
			System.out.println("================");
			System.out.println(heap);
		}
	}
	
	static void upheap(int x) {	// insert
		if(heap.size() <= 1) {		// 루트 노드가 없으면
			heap.add(1, x);
			return;
		}
		int childIdx = heap.size();	// 마지막 노드 + 1
		heap.add(childIdx, x);
		
		while(childIdx > 1) {			// upheap, 루트 노드 또는 알맞은 위치에 도착하면 종료
			int parentIdx = childIdx / 2;		// 부모 노드의 인덱스
			int parent = heap.get(parentIdx);	// 부모 노드의 값
			int child = heap.get(childIdx);		// 현재 노드(자식 노드)의 값
			if(Math.abs(parent) > Math.abs(child)) {	// 부모보다 내가 더 작으면 (절댓값)
				int temp = parent;
				heap.set(parentIdx, child);
				heap.set(childIdx, temp);
				childIdx /= 2;
			} else if(Math.abs(parent) == Math.abs(child) && parent > child) {	// 절댓값은 같지만 값이 내가 더 작으면
				int temp = parent;
				heap.set(parentIdx, child);
				heap.set(childIdx, temp);
				childIdx /= 2;
			} else {
				break;
			}
		}
		
		if(childIdx == 1) {
			downheap();
		}
	}
	
	static int delete() {
		if(heap.size() <= 1) {	// empty
			return 0;
		}
		
		int pop = heap.get(1);	// 루트 노드의 값 return
		
		int last = heap.get(heap.size() - 1);	// 마지막 값 
//		System.out.println("last: " + last);
		heap.set(1, last);		// 마지막 값을 루트 노드에 저장
//		System.out.println("root: " + heap.get(1));
		
		heap.remove(heap.size() - 1);				// 마지막 값 제거
		
		downheap();
		
		return pop;
	}
	
	static void downheap() {	// root부터 내려가기
		
		int idx = 1;
		
		while(idx * 2 < heap.size()) {	// downheap, 단말 노드 또는 알맞은 위치에 도착하면 종료
			if (idx * 2 + 1 < heap.size()) {	// 오른쪽 자식도 있을때
				int leftIdx = idx * 2;
				int left = heap.get(leftIdx);
				int rightIdx = idx * 2 + 1;
				int right = heap.get(rightIdx);
				
				if(left <= right) {	// 왼쪽이 더 작거나 같으면
					if(Math.abs(heap.get(idx)) > Math.abs(left)) {	// 왼쪽 자식보다 내가 더 크면
						int temp = heap.get(idx);
						heap.set(idx, left);
						heap.set(leftIdx, temp);
						idx = leftIdx;
					} else if(Math.abs(heap.get(idx)) == Math.abs(left) && heap.get(idx) > left) {	// 절댓값은 같지만 값이 내가 더 크면
						int temp = heap.get(idx);
						heap.set(idx, left);
						heap.set(leftIdx, temp);
						idx = leftIdx;
					} else break;
				}
				else {	// 오른쪽이 더 작으면
					if(Math.abs(heap.get(idx)) > Math.abs(right)) {	// 오른쪽 자식보다 내가 더 크면
						int temp = heap.get(idx);
						heap.set(idx, right);
						heap.set(rightIdx, temp);
						idx = rightIdx;
					} else if(Math.abs(heap.get(idx)) == Math.abs(right) && heap.get(idx) > right) {	// 절댓값은 같지만 값이 내가 더 크면
						int temp = heap.get(idx);
						heap.set(idx, right);
						heap.set(rightIdx, temp);
						idx = rightIdx;
					} else
						break;
				}
			} else {	// 왼쪽 자식만 있을때
				int leftIdx = idx * 2;
				int left = heap.get(leftIdx);
				
//				System.out.println("=========왼쪽 자식" + leftIdx);
				
				if(Math.abs(heap.get(idx)) > Math.abs(left)) {	// 자식보다 내가 더 크면
					int temp = heap.get(idx);
					heap.set(idx, left);
					heap.set(leftIdx, temp);
					idx = leftIdx;
				} else if(Math.abs(heap.get(idx)) == Math.abs(left) && heap.get(idx) > left) {	// 절댓값은 같지만 값이 내가 더 크면
					int temp = heap.get(idx);
					heap.set(idx, left);
					heap.set(leftIdx, temp);
					idx = leftIdx;
				} else 
					break;
			}
		}
	}
}
