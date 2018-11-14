package effectivejava.chapter2.item3.staticfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Singleton with static factory (Page 17)
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { }
    public static Elvis getInstance() { return INSTANCE; }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();

        Elvis elvis2 = Elvis.getInstance();
        System.out.println(elvis);
        System.out.println(elvis2);
        System.out.println();

        // invoke private constructor with reflection
        Constructor<?> con = Elvis.class.getDeclaredConstructors()[0];
        con.setAccessible(true);
        Elvis elvisInvoked = (Elvis) con.newInstance();
        System.out.println(elvis);
        System.out.println(elvisInvoked);

        /*

        Whoa baby, I'm outta here!
        effectivejava.chapter2.item3.staticfactory.Elvis@2471cca7
        effectivejava.chapter2.item3.staticfactory.Elvis@2471cca7
        effectivejava.chapter2.item3.staticfactory.Elvis@2471cca7
        effectivejava.chapter2.item3.staticfactory.Elvis@5fe5c6f

        */
    }
}
