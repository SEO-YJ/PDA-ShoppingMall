package exercise.generics;

import java.util.ArrayList;

public class MyStackDemo {
    public static void main(String[] args) {
        MyStack<String> stackStr = new MyStack();
        System.out.println(stackStr.isEmpty());
        stackStr.push("a"); // 1
        stackStr.push("b"); // 2
        stackStr.push("c"); // 3

        System.out.println(stackStr.pop());
        System.out.println(stackStr.peek());

        System.out.println(stackStr.isEmpty());

        stackStr.printElements();
    }
}

class MyStack<T> {
    private ArrayList<T> stack;

    public MyStack() {
        this.stack = new ArrayList<>();
    }

    public void push(T data) {
        stack.add(data);
    }

    public T pop() {
        if(isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void printElements() {
        for(T data: stack) {
            System.out.println(data);
        }
    }
}

