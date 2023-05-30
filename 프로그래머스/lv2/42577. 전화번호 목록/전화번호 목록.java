import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book);
        
        int size = phone_book.length;
        
        for (int i = 0; i < size - 1 ; i++) {
            int length = phone_book[i].length();
            if (length <= phone_book[i + 1].length()) {
                String subStr = phone_book[i + 1].substring(0, length);
                if (subStr.compareTo(phone_book[i]) == 0) {
                    answer = false;
                    break;
                }
            }
        }
        
        return answer;
    }
}