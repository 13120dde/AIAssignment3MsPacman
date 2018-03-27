package pacman.controllers.id3Controller.generic.data.values;

/**
 * Base class for all values in tuples and classifications
 */
public abstract class Value
{
    /**
     * Custom equals method
     * @param o
     * @return whether the Object o equals this object or not
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Value)
            if (o.hashCode() != hashCode())
                return false;
        return o.equals(this);
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
