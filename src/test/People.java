package test;

public class People extends CatEventListener{
    String name;

    public People(String name) {
        this.name = name;
    }

    @Override
    public void handleSleepEvent(SleepEvent event) {
        System.out.println(name+" see "+event.getSource().name+" sleep");
    }

    @Override
    public void handleEatEvent(EatEvent event) {
        System.out.println(name+" see "+event.getSource().name+" eat "+event.getFood());
    }
}
