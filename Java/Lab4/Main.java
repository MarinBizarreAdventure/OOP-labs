import stack.ArrayStack;
public class Main {
        public static void main(String[] args) {
            // Create a dynamic array stack
            ArrayStack<Integer> stack = new ArrayStack<>();

            // Push elements onto the stack
            stack.push(5);
            stack.push(10);
            stack.push(15);

            // Test pop and peek operations
            System.out.println("Top element: " + stack.peek()); // Should print 15
            System.out.println("Popped element: " + stack.pop());  // Should print 15

            // Test size and isEmpty
            System.out.println("Size of the stack: " + stack.size());  // Should print 2
            System.out.println("Is the stack empty? " + stack.isEmpty()); // Should print false

            // Push more elements to trigger dynamic resizing
            for (int i = 0; i < 20; i++) {
                stack.push(i * 5);
            }

            // Test size after dynamic resizing
            System.out.println("Size of the stack after resizing: " + stack.size());// Should print 22
            stack.clear();
            System.out.println("Size of the stack after clearing: " + stack.size());
        }
}
