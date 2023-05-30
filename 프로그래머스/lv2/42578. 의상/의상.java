import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int size = clothes.length;
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < size; i++) {
            String type = clothes[i][1];
            if (map.get(type) == null) {
                map.put(type, 1);
            } else {
                int temp = map.get(type);
                map.put(type, temp + 1);
            }
        }
        
        int answer = 1;
        
        for (String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }
        
        return --answer;
    }
}