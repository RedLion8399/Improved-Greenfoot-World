/**
 * An enumeration representing the priority levels of messages.
 * They are used to control the display of messages based on their importance.
 * 
 * @link MessagePriority#INFO
 * @link MessagePriority#WARNING
 * @link MessagePriority#ERROR
 * @link MessagePriority#NONE
 * 
 * @version 24 September 2025
 * @author Paul Jonas Dohle
 */
public enum MessagePriority {
    /** Informational messages, lowest priority */
    INFO(0),
    /** Warning messages, medium priority */
    WARNING(1),
    /** Error messages, highest priority */
    ERROR(2),
    /** No messages, disables message display */
    NONE(3);

    private final int PRIORITY;

    MessagePriority(int PRIORITY) {
        this.PRIORITY = PRIORITY;
    }

    /**
     * Returns the priority of the message as an integer.
     * 
     * @return the priority of the message
     */
    public int getPriority() {
        return PRIORITY;
    }
}