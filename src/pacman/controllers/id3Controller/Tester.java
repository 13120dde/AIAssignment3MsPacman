package pacman.controllers.id3Controller;

import pacman.controllers.id3Controller.generic.data.DataTable;
import pacman.controllers.id3Controller.generic.data.DatasetPointer;
import pacman.controllers.id3Controller.generic.data.values.Value;
import pacman.controllers.id3Controller.generic.data.values.BooleanValue;
import pacman.controllers.id3Controller.generic.data.values.StringValue;
import pacman.controllers.id3Controller.generic.learning.DecisionTree;
import pacman.controllers.id3Controller.generic.learning.selectors.AttributeSelector;

import java.util.HashMap;

/**
 * Just a class testing the ID3 algorithm with
 * the example from the lecture slides
 */
public class Tester
{
    public static void main(String[] args)
    {
        DataTable testSet = new DataTable();
        testSet.addAttributes("age", "income", "student", "credit_rating");

        testSet.addTuple(new BooleanValue(false), new HashMap<String, Value>() {{
            put("age", new StringValue("youth"));
            put("income", new StringValue("high"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("fair"));
        }});
        testSet.addTuple(new BooleanValue(false), new HashMap<String, Value>() {{
            put("age", new StringValue("youth"));
            put("income", new StringValue("high"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("excellent"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("middle_aged"));
            put("income", new StringValue("high"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("fair"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("senior"));
            put("income", new StringValue("medium"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("fair"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("senior"));
            put("income", new StringValue("low"));
            put("student", new StringValue("yes"));
            put("credit_rating", new StringValue("fair"));
        }});
        testSet.addTuple(new BooleanValue(false), new HashMap<String, Value>() {{
            put("age", new StringValue("senior"));
            put("income", new StringValue("low"));
            put("student", new StringValue("yes"));
            put("credit_rating", new StringValue("excellent"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("middle_aged"));
            put("income", new StringValue("low"));
            put("student", new StringValue("yes"));
            put("credit_rating", new StringValue("excellent"));
        }});
        testSet.addTuple(new BooleanValue(false), new HashMap<String, Value>() {{
            put("age", new StringValue("youth"));
            put("income", new StringValue("medium"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("fair"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("youth"));
            put("income", new StringValue("low"));
            put("student", new StringValue("yes"));
            put("credit_rating", new StringValue("fair"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("senior"));
            put("income", new StringValue("medium"));
            put("student", new StringValue("yes"));
            put("credit_rating", new StringValue("fair"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("youth"));
            put("income", new StringValue("medium"));
            put("student", new StringValue("yes"));
            put("credit_rating", new StringValue("excellent"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("middle_aged"));
            put("income", new StringValue("medium"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("excellent"));
        }});
        testSet.addTuple(new BooleanValue(true), new HashMap<String, Value>() {{
            put("age", new StringValue("middle_aged"));
            put("income", new StringValue("high"));
            put("student", new StringValue("yes"));
            put("credit_rating", new StringValue("fair"));
        }});
        testSet.addTuple(new BooleanValue(false), new HashMap<String, Value>() {{
            put("age", new StringValue("senior"));
            put("income", new StringValue("medium"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("excellent"));
        }});

        System.out.println("TestPointer 1:");
        DatasetPointer testPointer = new DatasetPointer(testSet);
        testPointer.print();

        DecisionTree t = new DecisionTree(testSet, new AttributeSelector());

        System.out.println();
        System.out.println();
        System.out.println();
        for (int i = 0; i < 500; i++)
            System.out.print("-");
        System.out.println();

        for (int i = 0; i < 500; i++)
            System.out.print("-");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Should be 'no'");
        System.out.println("Test: " + t.classify(new HashMap<String, Value>() {{
            put("age", new StringValue("youth"));
            put("income", new StringValue("high"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("fair"));
        }}));
        System.out.println("Test: " + t.classify(new HashMap<String, Value>() {{
            put("age", new StringValue("senior"));
            put("income", new StringValue("medium"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("excellent"));
        }}));
        System.out.println("Should be 'yes':");
        System.out.println("Test: " + t.classify(new HashMap<String, Value>() {{
            put("age", new StringValue("middle_aged"));
            put("income", new StringValue("high"));
            put("student", new StringValue("no"));
            put("credit_rating", new StringValue("fair"));
        }}));

        System.out.println("Accuracy: " + t.test(testSet));
    }
}
