import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Display is a singleton that displays information
 * in abox as a console.
 * 
 * @author Paul Jonas
 * @version 28.09.2025
 */
public class Display extends Actor {

    private static Display instance = null;
    /** The priority of messages is displayed */
    public MessagePriority messagePriority;

    private Display(MessagePriority priority, World world) {
        setImage("images/nachricht.png");
        messagePriority = priority;
        world.addObject(this, 7, 0);
    }

    /**
     * Returns the single instance of the Display class.
     * If no instance exists, a new one is created.
     * 
     * @param priority The minimum priority of messages to display.
     * @param world    The world to add the display to.
     * 
     * @return The single instance of the Display class.
     */
    public static Display getInstance(MessagePriority priority, World world) {
        if (instance == null) {
            instance = new Display(priority, world);
        }
        return instance;
    }

    /**
     * Returns the single instance of the Display class.
     * If no instance exists, a new one is created with
     * the default priority of INFO.
     * 
     * @param world
     * 
     * @return The single instance of the Display class.
     */
    public static Display getInstance(World world) {
        return getInstance(MessagePriority.INFO, world);
    }

    /**
     * Clears the current text from the display and then
     * writes the given text onto the display.
     * 
     * @param messageText The text to write onto the display.
     */
    public void display(String messageText) {
        delete();
        getImage().drawImage(new GreenfootImage(messageText, 25, Color.BLACK, new Color(0, 0, 0, 0)), 10, 10);
    }

    /**
     * Clears the text from the display
     */
    public void delete() {
        getImage().clear();
        setImage("images/nachricht.png");
    }

    /**
     * The display is removed from the world.
     */
    public void shutdown() {
        instance = null;
    }

    /**
     * Writes the given text onto the display if the priority is high enough.
     * If the priority of the message is higher than the priority of the display,
     * the text is not written.
     * After writing the text, the method waits for 1 second and then clears the
     * display.
     * 
     * @param messageText The text to write onto the display.
     * @param priority    The priority of the message. If the priority of the
     *                    message is higher than the priority of the display, the
     *                    text is not written.
     */
    public void message(String messageText, MessagePriority priority) {
        if (!(this.messagePriority.getPriority() > priority.getPriority())) {
            display(messageText);
            Greenfoot.delay(1);
            delete();
        }
    }

    /**
     * Writes the given text onto the display with the default priority of INFO.
     * 
     * @param messageText The text to write onto the display.
     */
    public void message(String messageText) {
        message(messageText, MessagePriority.INFO);
    }
}
