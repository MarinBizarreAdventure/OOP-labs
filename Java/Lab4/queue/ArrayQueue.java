package queue;

import interfaces.Queue;
import stack.ArrayStack;

import java.util.EmptyStackException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ArrayQueue(int capacity){
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(E element){
        if (size == capacity){
            resizeArray();
        }
        rear = (rear + 1)%capacity;
        array[rear] = element;
        size++;
    }

    @Override
    public E dequeue(){
        if (isEmpty()){
            throw  new EmptyStackException();
        }
        E removedElement  = array[front];
        front = (front+1)%capacity;
        size--;
        return removedElement;
    }

    @Override
    public E peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return array[front];

    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void clear() {
        front = 0;
        rear = -1;
        size = 0;
        array = (E[]) new Object[capacity];
    }

    private void resizeArray(){
        int newCapacity = 2 * capacity;
        E[] newArray = (E[]) new Object[capacity*2];
        for(int i=0;i<size; i++){
            newArray[i] = array[(front+i)%capacity];
        }
        front =0;
        rear = size - 1;
        array = newArray;
        capacity = newCapacity;
    }

}
