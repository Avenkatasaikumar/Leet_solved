class MinStack {
    public Stack<Long> stack;
    public long min;
    public MinStack() {
        stack = new Stack<>();
    }
    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = val;
        } else {
            stack.push((long) val - min);
            if (val < min) {
                min = val;
            }
        }
    }
    public void pop() {
        long top = stack.pop();
        if (top < 0) {
            min = min - top; 
        }
    }
    public int top() {
        long top = stack.peek();
        if (top < 0) {
            return (int) min;
        } else {
            return (int) (top + min);
        }
    }
    public int getMin() {
        return (int) min;
    }
}