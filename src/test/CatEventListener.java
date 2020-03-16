package test;

import java.util.EventListener;

public class CatEventListener implements EventListener {
    public void handleSleepEvent(SleepEvent event) {
        System.out.println("I see "+event.getSource().name+" sleep");
    }

    public void handleEatEvent(EatEvent event) {
        System.out.println("I see "+event.getSource().name+" eat "+event.getFood());
    }
}
