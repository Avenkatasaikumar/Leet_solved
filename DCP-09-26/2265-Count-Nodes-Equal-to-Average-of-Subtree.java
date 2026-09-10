/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int mc=0;
    public int averageOfSubtree(TreeNode root) {
        fun(root);
        return mc;
    }

    public int[] fun(TreeNode n){
        if(n==null)
            return new int[] {0,0};

        int[] l=fun(n.left);
        int[] r=fun(n.right);

        int currv=n.val+l[0]+r[0];
        int currc=1+l[1]+r[1];

        if(n.val==currv/currc)
            mc++;

        return new int[] {currv,currc};        
    }
}