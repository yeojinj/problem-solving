class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (int i = 0; i < skill_trees.length; i++) {
            String skillTree = skill_trees[i];  // 스킬트리 하나씩 탐색
            int skillIdx = 0;           // 현재 써야할 선행 스킬 인덱스 번호
            boolean isValid = true;    // 현재 스킬트리가 가능한 건지 여부
            
            for (int j = 0; j < skillTree.length(); j++) {  // 현재 스킬트리 탐색
                int k = skill.indexOf(skillTree.substring(j, j + 1));
                if (k != -1) {
                    if (k == skillIdx) {        // 선행 스킬 순서에 맞게 썼을 경우
                        skillIdx++;
                    } else {                    // 선행 스킬 순서에 안 맞게 썼을 경우
                        isValid = false;
                        break;
                    }
                }
            }
            
            if (isValid) {
                answer++;
            }
        }
        
        return answer;
    }
}