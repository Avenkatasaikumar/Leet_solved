class Solution {
    public int maxArea(int[] height) {
        int l=0,r=height.length-1,max=0;
        while(l<r){
            int he=Math.min(height[l],height[r]);
            int wd=r-l;
            int area=he*wd;
            max=Math.max(max,area);
            if(height[l]<height[r])
                  l++;
            else
                r--;      
        }
        return max;
    }
}