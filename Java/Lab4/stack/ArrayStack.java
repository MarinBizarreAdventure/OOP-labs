package stack;

import interfaces.Stack;

import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {
    private static final int INITIAL_SIZE = 10;
    private E[] array;
    private int size;

    public ArrayStack(){
        array = (E[]) new Object[INITIAL_SIZE];
        size = 0;
    }

    @Override
    public void push(E element){
        if(size == array.length){
            resizeArray(2* array.length);
        }
        array[size++] = element;
    }

    @Override
    public E pop(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        E element = array[--size];
        array[size] = null;
        return element;
    }

    @Override
    public E peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return array[size-1];
    }

    @Override
    public void clear(){
        for(int i=0;i<size;i++){
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    private void resizeArray(int newCapacity){
        E[] newArray = (E[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0 , size);
        array = newArray;
    }
}
