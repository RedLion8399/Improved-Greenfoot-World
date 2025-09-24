public enum MessagePriority {
    INFO(0),
    WARNING(1),
    ERROR(2),
    NONE(3);

    private final int PRIORITY;

    MessagePriority(int PRIORITY) {
        this.PRIORITY = PRIORITY;
    }

    public int getPriority() {
        return PRIORITY;
    }
}