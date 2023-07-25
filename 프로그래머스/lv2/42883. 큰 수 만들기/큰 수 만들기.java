class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int answerCnt = 0;
        int startIdx = 0;
        while (startIdx < number.length() && answerCnt < number.length() - k) {
            int max = -1;
            int maxIdx = -1;
            for (int i = startIdx; i < k + 1 + answerCnt; i++) {
                if (max < number.charAt(i) - '0') {
                    max = number.charAt(i) - '0';
                    maxIdx = i;
                }
            }
            answer.append(max);
            answerCnt++;
            startIdx = maxIdx + 1;
        }
        
        return answer.toString();
    }
}