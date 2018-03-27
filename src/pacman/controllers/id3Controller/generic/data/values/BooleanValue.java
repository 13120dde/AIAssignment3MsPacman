package pacman.controllers.id3Controller.generic.data.values;

/**
 * A basic Value class storing a boolean
 */
public class BooleanValue extends Value
{
    public boolean value;

    /**
     * Constructor
     * @param value value to be stored
     */
    public BooleanValue(boolean value)
    {
        this.value = value;
    }

    /**
     * Custom equals method
     * @param o
     * @return whether the Object o equals this object or not
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof BooleanValue)
            return ((BooleanValue)o).value == value;
        return super.equals(o);
    }

    /**
     * Custom hashCode() implementation
     * @return the hashcode
     */
    @Override
    public int hashCode()
    {
        return Boolean.hashCode(value);
    }

    /**
     * Custom toString() implementation
     * @return the string value of this value
     */
    @Override
    public String toString()
    {
        return value ? "Yes" : "No";
    }
}
