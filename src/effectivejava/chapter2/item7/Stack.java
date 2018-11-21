package effectivejava.chapter2.item7;
import java.util.*;

// Can you spot the "memory leak"?  (Pages 26-27)
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    // Corrected version of pop method (Page 27)
//    public Object pop() {
//        if (size == 0)
//            throw new EmptyStackException();
//        Object result = elements[--size];
//        elements[size] = null; // Eliminate obsolete reference
//        return result;
//    }

    public static void main(String[] args) throws InterruptedException {
        Stack stack = new Stack();

        System.out.println("push");

        //for (String arg : args)
        for(int i=0;i< 0x02ffffff ;i++)
            stack.push(String.valueOf(i));

        Thread.sleep(10000);

        System.out.println("pop");

        int sleepPeriod = 10000;
        int counter = 0;
        while (true) {
            stack.pop();
            counter++;
            if(counter % sleepPeriod == 0)
                Thread.sleep(100);
        }
            //System.err.println(stack.pop());
    }
}
