package pacman.controllers.id3Controller.generic.learning;

import pacman.controllers.id3Controller.generic.data.DataTable;
import pacman.controllers.id3Controller.generic.data.DatasetPointer;
import pacman.controllers.id3Controller.generic.data.Tuple;
import pacman.controllers.id3Controller.generic.data.values.Value;
import pacman.controllers.id3Controller.generic.learning.selectors.AttributeSelector;

import java.util.ArrayList;
import java.util.HashMap;

/**Decision tree classifier that is being built on the recorded data from the game. The tree builds itself on the provided
 * data and its attributes (columns) and the  id3 selection method.
 *
 * Created by: Patrik Lind, 17-03-2018
 *
 */
public class DecisionTree{

    /**Pojo for a tree node.
     */
    private class TreeNode{
        private String label = "";
        private  Value classifiedAs = null;
        private HashMap<Value, TreeNode> children = new HashMap<>();
    }

    private TreeNode root;
    private AttributeSelector selector;

    /**
     * Constructor
     * @param dataTable the dataTable to train with
     * @param selector the selector used for selecting attributes
     */
    public DecisionTree(DataTable dataTable, AttributeSelector selector)
    {
        this.selector = selector;
        root = buildTree(new DatasetPointer(dataTable));
    }

    /**
     * Recursive method for generating the tree
     * @param pointer the pointer pointing to a subset of the dataTable
     * @return a tree node
     */
    private TreeNode buildTree(DatasetPointer pointer){
        TreeNode node = new TreeNode();

        /*Base case: all tuples belong to the same class*/
        if (everyTupleInSameClass(pointer)) {
            node.classifiedAs = pointer.dataTable.tuples.get(pointer.indexes.get(0)).classifiedAs;
            node.label = node.classifiedAs.toString();
            return node;
        }

        /*Base case: all attributes have been selected*/
        if (attributeListIsEmpty(pointer)) {
            node.classifiedAs = majorityClassValue(pointer);
            node.label = node.classifiedAs.toString();
            return node;
        }

        /* Select the attribute with highest entropy*/
        String attribute = selector.id3(pointer);
        node.label = attribute;

        /* Get all unique values for the selected attribute */
        ArrayList<Value> values = new ArrayList<>();
        for (Tuple tuple : pointer.dataTable.tuples)
            if (!values.contains(tuple.attributes.get(attribute)))
                values.add(tuple.attributes.get(attribute));

        /* For each aj in A*/
        for (Value value : values) {
            /* Partition table w/o the selected attribute */
            DatasetPointer newPointer = pointer.partitionPointer(attribute);

            /* Add all tuples with value for attribute to the pointer */
            for (int i : pointer.indexes)
                if (pointer.dataTable.tuples.get(i).
                            attributes.get(attribute).equals(value))
                    newPointer.indexes.add(i);

            /*Base case: Dj is empty*/
            if (newPointer.indexes.size() == 0) {
                TreeNode n = new TreeNode();
                n.classifiedAs = majorityClassValue(pointer);
                n.label = n.classifiedAs.toString();
                node.children.put(value, n);
            } else {
                node.children.put(value, buildTree(newPointer));
            }
        }
        return node;
    }

    /**Checks if the attribute list is empty
     *
     * @param pointer : DatasetPointer
     * @return true/false
     */
    private boolean attributeListIsEmpty(DatasetPointer pointer){
        if(pointer.attributes.size() == 0)
            return true;
        return false;
    }

    /**Classify the current game state based on the tuple passed in as argument.
     *
     * @param values : HashMap<String, Value>
     * @return value : Value
     */
    public Value classify(HashMap<String, Value> values)
    {
        return traverseTree(root, values);
    }

    /**Traverses the tree until a leaf node is reached and returnes its value.
     *
     * @param node : TreeNode
     * @param values : HashMap<String, Value>
     * @return the value : Value
     */
    private Value traverseTree(TreeNode node, HashMap<String, Value> values)
    {
        if (node.children.size() == 0)
            return node.classifiedAs;

        TreeNode n = node.children.get(values.get(node.label));
        return traverseTree(n, values);
    }

    /**Calculates the accuracy of the decision tree. The input data table shouldn't be the same as the training table
     * to avoid over fitting.
     *
     * @param dataTable the dataTable to test
     * @return the accuracy
     */
    public double test(DataTable dataTable)
    {
        double hits = 0;

        for (Tuple tuple : dataTable.tuples)
            if (classify(tuple.attributes).equals(tuple.classifiedAs))
                hits++;

        return hits / (double) dataTable.tuples.size();
    }


    /**Checks if the dataset contains only 1 class
     *
     * @param pointer : DatasetPointer
     * @return boolean
     */
    private boolean everyTupleInSameClass(DatasetPointer pointer)
    {
        Value value = null;
        for (int i : pointer.indexes) {
            if (value == null)
                value = pointer.dataTable.tuples.get(i).classifiedAs;
            else if (!value.equals(pointer.dataTable.tuples.get(i).classifiedAs))
                return false;
        }

        return true;
    }

    /**Calculates the class value for which  the majority of tuples belong to
     *
     * @param pointer : DatasetPointer
     * @return value : Value
     */
    private Value majorityClassValue(DatasetPointer pointer)
    {
        HashMap<Value, Integer> classes = new HashMap<>();

        for (int i : pointer.indexes) {
            Tuple t = pointer.dataTable.tuples.get(i);

            if (classes.containsKey(t.classifiedAs))
                classes.put(t.classifiedAs,
                        classes.get(t.classifiedAs) + 1);
            else
                classes.put(t.classifiedAs, 1);
        }

        int c = -1;
        Value majorityClassValue = null;
        for (Value key : classes.keySet()) {
            if (classes.get(key) > c) {
                majorityClassValue = key;
                c = classes.get(key);
            }
        }

        return majorityClassValue;
    }
}
