package queue;

import interfaces.Queue;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private List<E> heap;

    public PriorityQueue(){
        heap = new ArrayList<>();
    }

    public void enqueue(E element){
        heap.add(element);
        int currentIndex = heap.size() - 1;
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            if (heap.get(currentIndex).compareTo(heap.get(parentIndex)) < 0) {
                swap(currentIndex, parentIndex);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("PriorityQueue is empty");
        }

        E highestPriority = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int currentIndex = 0;
        while (true) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
            int smallest = currentIndex;

            if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(smallest)) < 0) {
                smallest = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(smallest)) < 0) {
                smallest = rightChildIndex;
            }

            if (smallest != currentIndex) {
                swap(currentIndex, smallest);
                currentIndex = smallest;
            } else {
                break;
            }
        }

        return highestPriority;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("PriorityQueue is empty");
        }
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        heap.clear();
    }
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }



}
