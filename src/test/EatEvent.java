package test;

import java.util.EventObject;

public class EatEvent  extends EventObject {
    private String food;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public EatEvent(Object source) {
        super(source);
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public Cat getSource() {
        return (Cat) super.getSource();
    }
}
