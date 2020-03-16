package test;

/**
 * Created by Administrator on 2020/3/16.
 */
public class C {
    public static C c1 = new C();
    public static C c2 = new C();
    {
        System.out.println("aaa");
    }
    static {
        System.out.println("jingtai");
    }

    public C() {
        System.out.println("gouzao");
    }

    public static void main(String[] args) {
        C c = new C();
    }
}
