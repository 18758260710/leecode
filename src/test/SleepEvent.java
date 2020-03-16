package test;

import java.util.EventObject;

public class SleepEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SleepEvent(Object source) {
        super(source);
    }

    @Override
    public Cat getSource() {
        return (Cat) super.getSource();
    }
}
