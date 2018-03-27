package pacman.controllers.id3Controller.generic.data;

import pacman.controllers.id3Controller.generic.data.values.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**This class represents the data structure for holding data used by the ID3 classifier. The data is stored in a
 * ArrayList where each element holds a tuple.
 *
 * Created by: Patrik Lind, 17-03-2018
 */
public class DataTable{

    public ArrayList<String> attributes = new ArrayList<>();
    public ArrayList<Tuple> tuples = new ArrayList<>();

    /**Create a new tuple and add it to the dataTable
     *
     * @param classifiedAs : Value
     * @param values : HashMap<String, Value>
     */
    public void addTuple(Value classifiedAs, HashMap<String, Value> values){
        HashMap<String, Value> attributeValues = new HashMap<>();

        values.forEach((k, v) -> {
            if (attributes.contains(k))
                attributeValues.put(k, v);
        });

        tuples.add(new Tuple(classifiedAs, attributeValues, attributes));
    }

    /** Add a tuple to the dataTable
     *
     * @param tuple : Tuple
     */
    public void addTuple(Tuple tuple) {
        addTuple(tuple.classifiedAs, tuple.attributes);
    }

    /**Add attributes to the dataTable
     *
     * @param attributes : String
     */
    public void addAttributes(String... attributes){
        this.attributes.addAll(Arrays.asList(attributes));
    }
}
