import stack.*;
public class Main {
        public static void main(String[] args) {
            // Create a dynamic array stack
            ArrayStack<Integer> arrayStack = new ArrayStack<>();

            // Push elements onto the stack
            arrayStack.push(5);
            arrayStack.push(10);
            arrayStack.push(15);

            // Test pop and peek operations
            System.out.println("Top element: " + arrayStack.peek()); // Should print 15
            System.out.println("Popped element: " + arrayStack.pop());  // Should print 15

            // Test size and isEmpty
            System.out.println("Size of the stack: " + arrayStack.size());  // Should print 2
            System.out.println("Is the stack empty? " + arrayStack.isEmpty()); // Should print false

            // Push more elements to trigger dynamic resizing
            for (int i = 0; i < 20; i++) {
                arrayStack.push(i * 5);
            }

            // Test size after dynamic resizing
            System.out.println("Size of the stack after resizing: " + arrayStack.size());// Should print 22
            arrayStack.clear();
            System.out.println("Size of the stack after clearing: " + arrayStack.size());



            System.out.println("LinkedStack");
            LinkedStack<Integer> stack = new LinkedStack<>();

            // Push elements onto the stack
            stack.push(1);
            stack.push(2);
            stack.push(3);

            // Test peek
            System.out.println("Peek: " + stack.peek()); // Output: Peek: 3

            // Test size and isEmpty
            System.out.println("Stack size: " + stack.size()); // Output: Stack size: 3
            System.out.println("Is the stack empty? " + stack.isEmpty()); // Output: Is the stack empty? false

            // Test pop
            System.out.println("Pop: " + stack.pop()); // Output: Pop: 3

            // Test clear
            stack.clear();
            System.out.println("Stack size after clearing: " + stack.size()); // Output: Stack size after clearing: 0
            System.out.println("Is the stack empty after clearing? " + stack.isEmpty()); // Output: Is the stack empty after clearing? true

        }
}
