class Solution {
    public int[] solution(int[] arr) {
        
        if (arr.length == 1) {
            int[] answer = {-1};
            return answer;
        }
        
        int minIdx = -1;
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIdx = i;
            }
        }
        
        int[] answer = new int[arr.length - 1];
        int idx = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (i == minIdx) continue;
            else {
                answer[idx++] = arr[i];
            }
        }
        
        return answer;
    }
}