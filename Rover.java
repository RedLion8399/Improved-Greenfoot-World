import java.util.concurrent.Callable;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The rover is a movable object that can be controlled by driving and turning.
 * It can also interact with other objects in the world, such as picking up
 * stones and marks and dropping them.
 * It has the ability to check for hills, stones, and markers in its vicinity.
 * 
 * @version 24 September 2025
 * @author Paul Jonas Dohle
 */
public class Rover extends Actor {

    private Display display;
    private MessagePriority messagePriority = MessagePriority.INFO;

    /**
     * If the button "act" in the main window is clicked this method is executed.
     * The button "run" would execute this method as well, but in a loop.
     */
    public void act() {
        drive(2);
    }

    /**
     * The rover moves one field in the direction it is currently facing.
     * If there is a n Object in front of the Rover does not move and displays a
     * message.
     */
    public void drive() {
        int posX = getX();
        int posY = getY();

        if (isHill(Direction.FOR)) {
            message("Zu steil!", MessagePriority.ERROR);
        } else if (getRotation() == 270 && getY() == 1) {
            message("Ich kann mich nicht bewegen", MessagePriority.ERROR);
        } else {
            move(1);
            Greenfoot.delay(1);
            if (getOneIntersectingObject(Portal.class) != null) {
                Portal portal = (Portal) getOneIntersectingObject(Portal.class);
                portal.teleport(this);
            }
        }

        if (posX == getX() && posY == getY() && !isHill(Direction.FOR)) {
            message("Ich kann mich nicht bewegen", MessagePriority.ERROR);
        }
    }

    /**
     * The rover moves a given amount of steps in the direction it is currently
     * facing
     *
     * @param amount The amount of steps to move
     */
    public void drive(int amount) {
        for (int i = 0; i < amount; i++) {
            drive();
        }
    }

    /**
     * The rover moves a given amount of steps in the direction it is currently
     * facing. After each step, the given function is executed.
     * 
     * @param amount   The amount of steps to move
     * @param function The function to execute after each step
     */
    public void drive(int amount, Runnable function) {
        function.run();
        for (int i = 0; i < amount; i++) {
            drive();
            function.run();
        }
    }

    /**
     * The rover moves until the given condition is false. After each step, the
     * given function is executed.
     * 
     * @param condition The condition to check
     * @param function  The function to execute after each step
     */
    public void drive(Callable<Boolean> condition, Runnable function) {
        function.run();
        try {
            while (condition.call()) {
                drive();
                function.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
            message("Bedingung nicht korrekt", MessagePriority.ERROR);
        }
    }

    /**
     * The rover moves until the given condition is false.
     * 
     * @param condition The condition to check
     */
    public void drive(Callable<Boolean> condition) {
        try {
            while (condition.call()) {
                drive();
            }
        } catch (Exception e) {
            e.printStackTrace();
            message("Bedingung nicht korrekt", MessagePriority.ERROR);
        }
    }

    /**
     * Thr rover turns 90 degrees in the given direction.
     * 
     * @param richtung The direction to turn as Enum (RIGHT, LEFT)
     */
    public void turn(Direction richtung) {
        switch (richtung) {
            case RIGHT -> turn(90);
            case LEFT -> turn(-90);
            default -> message("Befehl nicht korrekt", MessagePriority.ERROR);
        }
    }

    /**
     * Thr rover turns 90 degrees in the given direction.
     * 
     * This method is only meant to be executed from the GUI directly and not from
     * the code itself. Instead use the method with the Enum parameter to avoid
     * errors.
     * 
     * The direction should be one of the following strings:
     * "RIGHT", "right", "rechts", "R", "r"
     * "LEFT", "left", "links", "L", "l"
     * 
     * @param direction The direction to turn as a string
     */
    public void turn(String direction) {
        switch (direction) {
            case "RIGHT", "right", "rechts", "R", "r" -> turn(Direction.RIGHT);
            case "LEFT", "left", "links", "L", "l" -> turn(Direction.LEFT);
            default -> message(direction + " ist kein g\u00FCltiger Befehl", MessagePriority.ERROR);
        }
    }

    /**
     * The rover checks if there is a Gestein directly below it.
     * If the direction is not correct, a message is displayed.
     * 
     * @return True if there is a `Gestein`, false otherwise
     */
    public boolean isStoneBelow() {
        if (getOneIntersectingObject(Gestein.class) != null) {
            message("Gestein gefunden!");
            return true;

        }

        message("Kein Gestein vorhanden!", MessagePriority.WARNING);
        return false;
    }

    /**
     * The rover checks if there is a "Hill" in the given direction.
     * If the direction is not correct, a message is displayed.
     * 
     * @param direction The direction to check as Enum (RIGHT, LEFT, FOR)
     * 
     * @return True if there is a "Hill", false otherwise
     */
    public boolean isHill(Direction direction) {
        int rot = getRotation();

        if (Direction.getAbsoluteDirection(this, direction) == Direction.RIGHT) {
            if (getOneObjectAtOffset(1, 0, Huegel.class) != null) {
                return true;
            }
        }

        if (Direction.getAbsoluteDirection(this, direction) == Direction.LEFT) {
            if (getOneObjectAtOffset(-1, 0, Huegel.class) != null) {
                return true;
            }
        }

        if (Direction.getAbsoluteDirection(this, direction) == Direction.BACK) {
            if (getOneObjectAtOffset(0, 1, Huegel.class) != null) {
                return true;
            }

        }

        if (Direction.getAbsoluteDirection(this, direction) == Direction.FOR) {
            if (getOneObjectAtOffset(0, -1, Huegel.class) != null) {
                return true;
            }

        }

        if (direction != Direction.FOR && direction != Direction.LEFT && direction != Direction.RIGHT) {
            message("Befehl nicht korrekt!", MessagePriority.ERROR);
        }

        return false;
    }

    /**
     * The rover picks up a Stone if there is one directly below it.
     * If there is no Stone, a message is displayed.
     */
    public void takeStone() {
        if (isStoneBelow()) {
            Greenfoot.delay(1);
            removeTouching(Gestein.class);
        } else {
            message("Hier ist kein Gestein", MessagePriority.WARNING);
        }
    }

    /**
     * The rover places a "Marker" object on the current field.
     * If there is already one, the Marker is not placed.
     * 
     * @param forcePlace If true, the Marker is placed even if there is already one
     */
    public void setMarker(boolean forcePlace) {
        if (isMarker() && !forcePlace) {
            return;
        }
        getWorld().addObject(new Marker(), getX(), getY());
    }

    /**
     * Places a "Marker" object on the current field if there isn't already one.
     */
    public void setMarker() {
        setMarker(false);
    }

    /**
     * The rover checks if there is a "Marker" object on the current field.
     * 
     * @return True if there is a "Marker", false otherwise
     */
    public boolean isMarker() {
        if (getOneIntersectingObject(Marker.class) != null) {
            message("Marke gefunden!");
            return true;
        }

        message("Keine Marke vorhanden!", MessagePriority.WARNING);
        return false;
    }

    /**
     * The rover removes a "Marker" object from the current field if there is one.
     */
    public void removeMarker() {
        if (isMarker()) {
            removeTouching(Marker.class);
        }
    }

    private void message(String messageText, MessagePriority priority) {
        if (display == null) {
            return;
        } else if (this.messagePriority.getPriority() > priority.getPriority()) {
            return;
        }

        display.display(messageText);
        Greenfoot.delay(1);
        display.delete();
    }

    private void message(String messageText) {
        message(messageText, MessagePriority.INFO);
    }

    private void displayAusschalten() {
        getWorld().removeObject(display);

    }

    protected void addedToWorld(World world) {

        setImage("images/rover.png");
        world = getWorld();
        display = new Display();
        display.setImage("images/nachricht.png");
        world.addObject(display, 7, 0);
        if (getY() == 0) {
            setLocation(getX(), 1);
        }
        display.display("Ich bin bereit");

    }

    class Display extends Actor {
        GreenfootImage bild;

        public Display() {
            bild = getImage();
        }

        public void act() {
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

    }
}
