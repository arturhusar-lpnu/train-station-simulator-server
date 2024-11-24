package events;

public interface Event {
    String convert();

    default String getType() {
        return this.getClass().getSimpleName();
    }
}