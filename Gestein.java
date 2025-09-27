import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The stone can be taken by a rover for but it cannot be placed again.
 * 
 * @author Paul Jonas Dohle
 * @version 27 September 2025
 */
public class Gestein extends Actor {

    public Gestein() {
        if (Greenfoot.getRandomNumber(2) == 0) {
            setImage("images/gesteinRot.png");
        } else {
            setImage("images/gesteinBlau.png");
        }
    }

    /**
     * If the button "act" in the main window is clicked this method is executed.
     * The button "run" would execute this method as well, but in a loop.
     */
    public void act() {
    }
}
