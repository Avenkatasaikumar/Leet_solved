/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int mind=Integer.MAX_VALUE;
        int fi=-1,li=-1;

        ListNode prev=head,curr=head.next;
        int i=1;
        while(curr!=null && curr.next!=null){
            if((curr.val>prev.val && curr.val>curr.next.val) ||(curr.val<prev.val && curr.val<curr.next.val)){
                if(fi==-1)
                   fi=i;
                else{
                    mind=Math.min(mind,i-li);
                }
                li=i;   
            }

            prev=curr;
            curr=curr.next;
            i++;
        }

        if(fi==-1 || fi==li)
            return new int[]{-1,-1};
        return new int[]{mind,li-fi};    
    }
}