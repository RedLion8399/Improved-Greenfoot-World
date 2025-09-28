import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A portal can be placed in the world to teleport actors to another portal.
 * 
 * @author Paul Jonas
 * @version 27 September 2025
 */
public class Portal extends Actor {

    private Portal linkedPortal;

    /**
     * Creates a new blue portal.
     * This acts as the main portal where an other portal is linked to.
     */
    public Portal() {
        setImage("images/portalBlue.png");
    }

    private Portal(int x, int y) {
        setImage("images/portalYellow.png");
        setLocation(x, y);
    }

    /**
     * If the button "act" in the main window is clicked this method is executed.
     * The button "run" would execute this method as well, but in a loop.
     */
    public void act() {
    }

    /**
     * Creates a linked portal at the given offset from the current
     * portal's position. If a linked portal already exists, it is
     * not replaced.
     * 
     * @param xOffset the x offset from the current portal's position
     * @param yOffset the y offset from the current portal's position
     */
    public void createLinkedPortal(int xOffset, int yOffset) {
        if (linkedPortal == null) {
            linkedPortal = new Portal(getX() + xOffset, getY() + yOffset);
            getWorld().addObject(linkedPortal, getX() + xOffset, getY() + yOffset);
        }
    }

    /**
     * Creates a linked portal to the right of the current portal.
     */
    public void createLinkedPortal() {
        createLinkedPortal(1, 0);
    }
}
