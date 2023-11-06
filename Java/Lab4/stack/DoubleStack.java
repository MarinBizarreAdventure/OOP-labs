package stack;

import interfaces.Stack;

public class DoubleStack<E>{
    private Object[] array;
    private int top1;
    private int top2;

    public DoubleStack(int capacity){
        array = new Object[capacity];
        top1 = -1;
        top2 = capacity;
    }

    public void pushStack1(E element){
        if(top1 < top2 -1){
            array[++top1] = element;
        }else{
            throw new IllegalStateException("Stack 1 is full.");
        }
    }

    public void pushStack2(E element){
        if(top2 > top1 +1){
            array[--top2] = element;
        }else{
            throw new IllegalStateException("Stack 2 is full.");
        }
    }

    @SuppressWarnings("unchecked")
    public E popStack1(){
        if(isEmptyStack1()){
            throw new IllegalStateException("Stack 1 is empty.");
        }else{
            return (E) array[top1--];
        }
    }

    @SuppressWarnings("unchecked")
    public E popStack2(){
        if(isEmptyStack2()){
            throw new IllegalStateException("Stack 2 is empty.");
        }else{
            return (E) array[top2++];
        }
    }

    @SuppressWarnings("unchecked")
    public E peekStack1() {
        if (isEmptyStack1()) {
            throw new IllegalStateException("Stack 1 is empty.");
        } else {
            return (E) array[top1];
        }
    }

    @SuppressWarnings("unchecked")
    public E peekStack2() {
        if (isEmptyStack2()) {
            throw new IllegalStateException("Stack 2 is empty.");
        } else {
            return (E) array[top2];
        }
    }

    public int sizeStack1(){
        return top1 + 1;
    }
    public int sizeStack2(){
        return array.length - top2;
    }
    public boolean isEmptyStack1(){
        return top1 == -1;
    }

    public boolean isEmptyStack2(){
        return top2 == array.length;
    }
}
