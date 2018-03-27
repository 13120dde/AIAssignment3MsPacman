package pacman.controllers.id3Controller.generic.data;

import java.util.ArrayList;

public class DatasetPointer
{
    public DataTable dataTable;
    public ArrayList<Integer> indexes = new ArrayList<>();
    public ArrayList<String> attributes = new ArrayList<>();

    /**
     * Constructor
     */
    private DatasetPointer() {}

    /**
     * Constructor
     * @param dataTable construct pointer pointing to entire dataTable
     */
    public DatasetPointer(DataTable dataTable)
    {
        this.dataTable = dataTable;

        /* Add all tuples */
        for (int i = 0; i < dataTable.tuples.size(); i++)
            indexes.add(i);

        /* Add all attributes */
        attributes.addAll(dataTable.attributes);
    }

    /**
     * Create a shallow clone of the pointer
     * @return the cloned pointer
     */
    public DatasetPointer pointerClone()
    {
        DatasetPointer temp = new DatasetPointer();

        temp.dataTable = dataTable;
        temp.attributes = new ArrayList<>(attributes);

        return temp;
    }

    /**
     * Create a new pointer based on this instance
     * @param attribute remove this attribute
     * @param indexes only include these indexes
     * @return the new pointer
     */
    public DatasetPointer partitionPointer(String attribute, int... indexes)
    {
        DatasetPointer temp = this.pointerClone();

        temp.attributes.remove(attribute);

        temp.indexes = new ArrayList<>();
        for (int i : indexes)
            if (i < dataTable.tuples.size())
                temp.indexes.add(i);

        return temp;
    }

    /**
     * Pretty print the dataTable
     */
    public void print()
    {
        print(20);
    }

    /**
     * Pretty print the dataTable
     * @param length width of cells
     */
    public void print(int length)
    {
        printLine(attributes.size() + 1, length);


        for (String a : attributes) {
            System.out.print(String.format("%-" + length + "s",
                                           centerString(a, length)));
            System.out.print("|");
        }

        System.out.print(String.format("%-" + length + "s",
                                       centerString("Value", length)));
        System.out.println("|");

        for (int i : indexes) {
            printLine(attributes.size() + 1, length);

            Tuple tuple = dataTable.tuples.get(i);

            for (String s : attributes) {
                System.out.print(String.format("%-" + length + "s",
                        centerString(
                                tuple.attributes.get(s).toString(), length))
                );
                System.out.print("|");
            }

            System.out.print(String.format("%-" + length + "s",
                    centerString(tuple.classifiedAs.toString(), length)));
            System.out.println("|");
        }

        printLine(attributes.size() + 1, length);
    }

    /**
     * Center string in a cell
     * @param s string to center
     * @param size width of cell
     * @return the padded string
     */
    private String centerString(String s, int size)
    {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < (size - s.length()) / 2; i++)
            string.append(" ");

        string.append(s);

        for (int i = 0; i < size - string.length(); i++)
            string.append(" ");

        return string.toString();
    }

    /**
     * Print line separator
     * @param number amount of cells
     * @param length width of cell
     */
    private void printLine(int number, int length)
    {
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print("-");
            }
            System.out.print("|");
        }
        System.out.println();
    }
}
