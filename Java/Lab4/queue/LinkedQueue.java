package queue;

import interfaces.Queue;
import stack.LinkedStack;

import java.util.EmptyStackException;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> rear;
    private Node<E> front;
    private int size;

    private class Node<E>{
        E data;
        Node<E> next;
        private Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    public LinkedQueue(){
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public void enqueue(E element){
        Node<E> newNode = new Node<>(element);
        if(isEmpty()){
            front = newNode;
            rear = newNode;
        }else{
            rear.next =  newNode;
            rear = newNode;
        }
        size++;
    }

    @Override
    public E dequeue(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        E removeData = front.data;;
        front = front.next;
        size--;
        if(isEmpty()){
            rear=null;
        }
        return removeData;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    @Override
    public void clear(){
        front = null;
        rear = null;
        size = 0;
    }
}
