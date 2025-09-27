import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Directions are all posible movements for the rover.
 * They are relative to the specific object instead of
 * an absolute direction of a refference system.
 * 
 * @link Direction#RIGHT
 * @link Direction#LEFT
 * @link Direction#FOR
 * @link Direction#BACK
 * 
 * @author Paul Jonas Dohle
 * @version 27 September 2025
 */
public enum Direction {
    /** Forward direction, relative to the object. Up in the world. */
    FOR(0),
    /** Right direction, relative to the object. Right in the world. */
    RIGHT(90),
    /** Backward direction, relative to the object. Down in the world. */
    BACK(180),
    /** Left direction, relative to the object. Left in the world. */
    LEFT(270);

    private final int ANGLE;

    Direction(int ANGLE) {
        this.ANGLE = ANGLE;
    }

    /**
     * Returns the direction with the given angle.
     * 
     * @param angle the angle of the direction
     * @return the direction with the given angle
     * @throws IllegalArgumentException if there is no direction with the given
     *                                  angle
     */
    public static Direction fromAngle(int angle) {
        for (Direction direction : values()) {
            if (direction.ANGLE == angle) {
                return direction;
            }
        }
        throw new IllegalArgumentException("No direction with angle " + angle);
    }

    /**
     * Returns the absolute direction of the given relative direction.
     * It returns the absolute direction from a rotation from an actor's
     * perspective.
     * 
     * @param relativeDirection the relative direction
     * @return the absolute direction of the given relative direction
     */
    public static Direction getAbsoluteDirection(Direction relativeDirection) {
        return fromAngle((90 + relativeDirection.ANGLE) % 360);
    }

    /**
     * Returns the absolute direction of the given relative direction.
     * It returns the absolute direction from the a rotation from an actors
     * persspective.
     * 
     * @param actor             the actor whose rotation should be used
     * @param relativeDirection the relative direction
     * @return the absolute direction of the given relative direction
     */
    public static Direction getAbsoluteDirection(Actor actor, Direction relativeDirection) {
        int rotation = actor.getRotation();
        int absoluteDirection = (rotation + 90 + relativeDirection.ANGLE) % 360;

        return fromAngle(absoluteDirection);
    }
}
