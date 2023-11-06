import stack.*;
import queue.ArrayQueue;
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


            // Create a DoubleStack with a capacity of 10
            System.out.println("DoubleStack");
            DoubleStack<Integer> doubleStack = new DoubleStack<>(10);

            // Push elements to Stack 1
            for (int i = 1; i <= 5; i++) {
                doubleStack.pushStack1(i);
            }

            // Push elements to Stack 2
            for (int i = 6; i <= 10; i++) {
                doubleStack.pushStack2(i);
            }

            // Test Stack 1 operations
            System.out.println("Stack 1 Operations:");
            System.out.println("Top element in Stack 1: " + doubleStack.peekStack1());
            System.out.println("Popped element from Stack 1: " + doubleStack.popStack1());
            System.out.println("Is Stack 1 empty? " + doubleStack.isEmptyStack1());
            System.out.println("Size of Stack 1: " + doubleStack.sizeStack1());

            // Test Stack 2 operations
            System.out.println("\nStack 2 Operations:");
            System.out.println("Top element in Stack 2: " + doubleStack.peekStack2());
            System.out.println("Popped element from Stack 2: " + doubleStack.popStack2());
            System.out.println("Is Stack 2 empty? " + doubleStack.isEmptyStack2());
            System.out.println("Size of Stack 2: " + doubleStack.sizeStack2());


            //Queues

            System.out.println("ArrayQueue");
            ArrayQueue<Integer> queue = new ArrayQueue<>(3); // Initial capacity is 3

            // Enqueue elements
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);

            // Check if resizing works by enqueuing more elements
            queue.enqueue(4); // This should trigger a resizing

            // Dequeue elements
            System.out.println("Dequeued: " + queue.dequeue()); // Should print "Dequeued: 1"
            System.out.println("Dequeued: " + queue.dequeue()); // Should print "Dequeued: 2"

            // Enqueue more elements after resizing
            queue.enqueue(5);
            queue.enqueue(6);

            // Dequeue the remaining elements
            System.out.println("Dequeued: " + queue.dequeue()); // Should print "Dequeued: 3"
            System.out.println("Dequeued: " + queue.dequeue()); // Should print "Dequeued: 4"
            System.out.println("Dequeued: " + queue.dequeue()); // Should print "Dequeued: 5"
            System.out.println("Dequeued: " + queue.dequeue()); // Should print "Dequeued: 6"

            // The queue is now empty

            // Check if the queue is empty
            System.out.println("Is the queue empty? " + queue.isEmpty()); // Should print "Is the queue empty? true"

            // Clear the queue
            queue.clear();

            // Check if the queue is empty after clearing
            System.out.println("Is the queue empty after clearing? " + queue.isEmpty()); // Should print "Is the queue empty after clearing? true"

        }

}
