package test;

public class EventTest {

    public static void main(String[] args) {
        People people1 = new People("tom");
        People people2 = new People("jack");
        Cat cat = new Cat("mimi");
        cat.addCatListener(people1);
        cat.addCatListener(people2);
        cat.eat("fish");
        cat.eat("egg");
    }
}
