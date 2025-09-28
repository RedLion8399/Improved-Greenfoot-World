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

    private Portal(int x, int y, Portal linkedPortal) {
        setImage("images/portalYellow.png");
        setLocation(x, y);
        this.linkedPortal = linkedPortal;
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
            linkedPortal = new Portal(getX() + xOffset, getY() + yOffset, this);
            getWorld().addObject(linkedPortal, getX() + xOffset, getY() + yOffset);
        }
    }

    /**
     * Creates a linked portal to the right of the current portal.
     */
    public void createLinkedPortal() {
        createLinkedPortal(1, 0);
    }

    /**
     * Teleports the given actor to the linked portal's position.
     * If no linked portal exists, an error message is displayed.
     * 
     * @param actor the actor to teleport
     */
    public void teleport(Actor actor) {
        if (linkedPortal != null) {
            actor.setLocation(linkedPortal.getX(), linkedPortal.getY());
            Greenfoot.delay(1);
        } else {
            // Todo: Display error message
        }
    }
}
