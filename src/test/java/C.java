import test.A;
import test.B;

/**
 * Created by Administrator on 2019/12/4.
 */
public class C extends A {//子类C继承A中的所有属性和方法
    public static void main(String[] args) {
        Object a = new Object();
        a.hashCode();
        C c = new C();
        System.out.println(c.nonStaticStr);
        System.out.println(c.staticStr);
        c.staticMethod();//输出的结果都是父类中的非静态属性、静态属性和静态方法,推出静态属性和静态方法可以被继承

        System.out.println("-------------------------------");

        A c1 = new C();
        System.out.println(c1.nonStaticStr);
        System.out.println(c1.staticStr);
        c1.staticMethod();//结果同上，输出的结果都是父类中的非静态属性、静态属性和静态方法,推出静态属性和静态方法可以被继承

        System.out.println("-------------------------------");
        B b = new B();
        System.out.println(b.nonStaticStr);
        System.out.println(b.staticStr);
        b.staticMethod();

        System.out.println("-------------------------------");
        A b1 = new B();
        System.out.println(b1.nonStaticStr);
        System.out.println(b1.staticStr);
        b1.staticMethod();//结果都是父类的静态方法，说明静态方法不可以被重写，不能实现多态
    }
}
