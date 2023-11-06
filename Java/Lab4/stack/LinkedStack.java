package stack;

import interfaces.Stack;

import java.util.EmptyStackException;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> head;
    private int size;

    public static class Node<E>{
        E data;
        Node<E> next;
        public Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    public LinkedStack(){
        head = null;
        size = 0;
    }

    @Override
    public void push(E element){
        Node<E> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public E pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        E popped = head.data;
        head = head.next;
        size--;
        return popped;
    }

    @Override
    public E peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return head.data;
    }

    @Override
    public void clear(){
        head = null;
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return head == null;
    }
}
