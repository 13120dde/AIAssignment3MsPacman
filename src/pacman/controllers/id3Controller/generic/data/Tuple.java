package pacman.controllers.id3Controller.generic.data;

import pacman.controllers.id3Controller.generic.data.values.Value;
import java.util.ArrayList;
import java.util.HashMap;

/**Class responsible for holding data for a row in the data table.
 *
 * Created by: Patrik Lind, 17-03-2018
 *
 */
public class Tuple{
    public HashMap<String, Value> attributes = new HashMap<>();
    public Value classifiedAs;

    public Tuple() {}

    /**Instantiate this object with passed in arguments
     *
     * @param classifiedAs : Value
     * @param attributes : HashMap<String, Value>
     * @param allowedAttributes : ArrayList<String>
     */
    public Tuple(Value classifiedAs, HashMap<String, Value> attributes, ArrayList<String> allowedAttributes){
        this.classifiedAs = classifiedAs;

        ArrayList<String> checkAttributes = new ArrayList<>(allowedAttributes);
        attributes.forEach((k, v) -> {
            if (checkAttributes.contains(k)) {
                checkAttributes.remove(k);
                this.attributes.put(k, v);
            }
        });

        for (String attributeKey : checkAttributes) {
            if (!this.attributes.containsKey(attributeKey))
                this.attributes.put(attributeKey, null);
        }
    }
}
