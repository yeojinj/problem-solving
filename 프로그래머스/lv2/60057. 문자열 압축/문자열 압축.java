class Solution {
    public int solution(String s) {
        int length = s.length();
        int answer = length;
        int count = 1;      // 반복 횟수
        
        for (int i = 1; i <= (length/2); i++) {
            StringBuilder result = new StringBuilder();
            String base = s.substring(0, i);
            for (int j = i; j < length; j += i) {
                String cur = s.substring(j, Math.min(j + i, length));
                if (base.compareTo(cur) == 0) {     // 같으면
                    count++;
                } else {                            // 다르면
                    if (count > 1) {
                        result.append(count);
                    }
                    result.append(base);
                    count = 1;
                    base = cur;
                }
            }
            if (count > 1) {
                result.append(count);
            }
            result.append(base);
            answer = Math.min(answer, result.toString().length());
            count = 1;
        }
        
        return answer;
    }
}