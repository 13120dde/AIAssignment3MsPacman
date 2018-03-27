package pacman.controllers.id3Controller.generic.data.values;

/**
 * A basic Value class storing a string
 */
public class StringValue extends Value
{
    public String value = "";

    /**
     * Constructor
     * @param s value to be stored
     */
    public StringValue(String s)
    {
        value = s;
    }

    /**
     * Custom equals method enabling subclasses to call super.equals()
     * @param o
     * @return whether the Object o equals this object or not
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof StringValue)
            return value.equals(((StringValue)o).value);
        return super.equals(o);
    }

    /**
     * Custom hashCode() implementation
     * @return the hashcode
     */
    @Override
    public int hashCode()
    {
        return value.hashCode();
    }

    /**
     * Custom toString() implementation
     * @return the string value of this value
     */
    @Override
    public String toString()
    {
        return value;
    }
}
