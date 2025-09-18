class Solution {
    public int solution(int[][] sizes) {
        int maxH = 0;
        int maxW = 0;
        
        for(int[] size : sizes){
            int longer = size[0] >= size[1] ? size[0] : size[1];
            int shorter = size[0] <= size[1] ? size[0] : size[1];
            maxH = longer > maxH ? longer : maxH;
            maxW = shorter > maxW ? shorter: maxW;
        }
        return maxH*maxW;
    }
}