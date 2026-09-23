class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;
        
        Node curr = head;
        while (curr != null) {
            if (curr.child != null) {
                Node nextNode = curr.next;
                Node childHead = curr.child;
                curr.next = childHead;
                childHead.prev = curr;
                curr.child = null; 
                
                Node childTail = childHead;
                while (childTail.next != null) {
                    childTail = childTail.next;
                }
                
                if (nextNode != null) {
                    childTail.next = nextNode;
                    nextNode.prev = childTail;
                }
                
                curr = childHead;
            } else {
                curr = curr.next;
            }
        }
        
        return head;
    }
}