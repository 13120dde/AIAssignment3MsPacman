package pacman.controllers.id3Controller.generic.learning.selectors;

import pacman.controllers.id3Controller.generic.data.DatasetPointer;
import pacman.controllers.id3Controller.generic.data.Tuple;
import pacman.controllers.id3Controller.generic.data.values.Value;

import java.util.HashMap;

/**This class is responsible for selecting the best attribute from the provided attribute list. The best attribute
 * is one which has highest calculated entropy, ie purest values in classifiedAs column of the data table.
 *
 * This class currently provides ID3 selection method.
 *TODO: implement CART, C4.5
 * Created by: Patrik Lind, 17-03-2018
 */
public class AttributeSelector{

    /**Selects the attribute with the highest entropy as calculated by the ID3 algorithm.
     *
     * @param data : DatasetPointer
     * @return selectedAttribute : String
     */
    public String id3(DatasetPointer data){

        /* Calculate amount of uniquew classes */
        HashMap<Value, Integer> uniqueClasses = new HashMap<>();
        for (int i : data.indexes) {
            Tuple tuple = data.dataTable.tuples.get(i);

            if (uniqueClasses.containsKey(tuple.classifiedAs))
                uniqueClasses.put(tuple.classifiedAs,
                        uniqueClasses.get(tuple.classifiedAs) + 1);
            else
                uniqueClasses.put(tuple.classifiedAs, 1);
        }

        /* Calculate the general entropy for the whole table*/
        double generalEntropy = 0;
        double tableSize = data.indexes.size();
        for (double i : uniqueClasses.values()) {
            double Pi = i / tableSize;
            generalEntropy -= Pi * log2(Pi);
        }

        /* Calculate entropies for each attribute */
        HashMap<String, Double> entropies = new HashMap<>();
        for (String attribute : data.attributes) {
            HashMap<Object, HashMap<Value, Integer>> attributes = new HashMap<>();

             /* Calculate unique values for the attribute in the dataTable*/
            for (int i : data.indexes) {
                Tuple tuple = data.dataTable.tuples.get(i);
                Object value = tuple.attributes.get(attribute);

                if (attributes.containsKey(value)) {
                    HashMap<Value, Integer> temp = attributes.get(value);
                    if (temp.containsKey(tuple.classifiedAs))
                        temp.put(tuple.classifiedAs,
                                 temp.get(tuple.classifiedAs) + 1);
                    else
                        temp.put(tuple.classifiedAs, 1);
                } else {
                    attributes.put(value, new HashMap<Value, Integer>() {{
                        put(tuple.classifiedAs, 1);
                    }});
                }
            }

            /* Calculate  entropy for the attribute */
            double entropy = 0;
            for (Object o : attributes.keySet()) {
                double count = 0.0;
                HashMap<Value, Integer> temp = attributes.get(o);

                /* Total amount of values for attribute */
                for (Value t : temp.keySet())
                    count += temp.get(t);

                /* Sum calculation */
                double infoTemp = 0;
                for (Value t : temp.keySet()) {
                    double a = (temp.get(t) / count);
                    double b = log2(temp.get(t) / count);
                    double c = a * b;
                    infoTemp -= c;
                }

                entropy += (count / (double)data.indexes.size()) * infoTemp;
            }

            entropies.put(attribute, generalEntropy - entropy);
        }

        /* Pick the attribute with the highest entropy*/
        double entropy = -1.0;
        String attribute = "";
        for (String s : entropies.keySet()) {
            if (entropies.get(s) > entropy) {
                attribute = s;
                entropy = entropies.get(s);
            }
        }

        return attribute;
    }

    /**Calculate the log2 of the value passed in as argument
     *
     * @param value : double
     * @return result : double
     */
    private double log2(double value)
    {
        return Math.log(value) / Math.log(2);
    }
}
