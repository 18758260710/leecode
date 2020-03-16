package test;

import java.util.HashSet;
import java.util.Set;

public class Cat {

    public Cat(String name) {
        this.name = name;
    }

    String name;
    Set<CatEventListener> listeners = new HashSet<>();

    public void addCatListener(CatEventListener listener) {
        listeners.add(listener);
    }

    public void sleep(){
        System.out.println("cat "+name+ " is sleeping");
        SleepEvent sleepEvent = new SleepEvent(this);
        for (CatEventListener listener : listeners) {
            listener.handleSleepEvent(sleepEvent);
        }
    }
    public void eat(String food){
        System.out.println("cat "+name+ " is eating "+food);
        EatEvent eatEvent = new EatEvent(this);
        eatEvent.setFood(food);
        for (CatEventListener listener : listeners) {
            listener.handleEatEvent(eatEvent);
        }
    }
}
