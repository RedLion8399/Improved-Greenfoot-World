import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The planet is the place where the actors can operate.
 * 
 * @version 24 September 2025
 * @author Paul Jonas Dohle
 */
public class Planet extends World {
    private static int zellenGroesse = 50;

    /**
     * Creates a new world with the given number of columns and rows,
     * where each cell is 50x50 pixels.
     * 
     * @param cols The number of columns
     * @param rows The number of rows
     */

    private Display display;

    public Planet(int cols, int rows) {
        super(cols, rows, zellenGroesse);
        setBackground("images/boden.png");
        setPaintOrder(String.class, Rover.class, Marker.class, Gestein.class, Huegel.class);
        Greenfoot.setSpeed(20);
        Display.instance = null;
        display = Display.getInstance(this);
    }

    /**
     * Creates a new world with 16 columns and 12 rows,
     * where each cell is 50x50 pixels.
     */
    public Planet() {
        this(16, 12);
    }
}