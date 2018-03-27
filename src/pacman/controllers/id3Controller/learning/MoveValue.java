package pacman.controllers.id3Controller.learning;

import pacman.controllers.id3Controller.generic.data.values.Value;
import pacman.game.Constants;

/**
 * A value class representing the different MOVE values
 */
public class MoveValue extends Value
{
    public Constants.MOVE move;

    public MoveValue(Constants.MOVE move)
    {
        this.move = move;
    }

    /**
     * Custom equals method
     * @param o
     * @return whether the Object o equals this object or not
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof MoveValue)
            return ((MoveValue) o).move == move;
        return super.equals(o);
    }

    /**
     * Custom hashCode() implementation
     * @return the hashcode
     */
    @Override
    public int hashCode()
    {
        return move.hashCode();
    }

    /**
     * Custom toString() implementation
     * @return the string value of this value
     */
    @Override
    public String toString()
    {
        return move.toString();
    }

    public Constants.MOVE getMove() {
        return move;
    }
}
