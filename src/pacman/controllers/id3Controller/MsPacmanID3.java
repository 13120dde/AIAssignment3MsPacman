package pacman.controllers.id3Controller;

import dataRecording.DataSaverLoader;
import dataRecording.DataTuple;
import pacman.controllers.id3Controller.generic.data.DataTable;
import pacman.controllers.id3Controller.generic.data.Tuple;
import pacman.controllers.id3Controller.generic.learning.DecisionTree;
import pacman.controllers.id3Controller.generic.learning.selectors.AttributeSelector;
import pacman.controllers.id3Controller.learning.MoveValue;
import pacman.controllers.id3Controller.learning.TupleCreator;
import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

import java.text.DecimalFormat;

/**AI controller based on ID3 decision tree algorithm.
 *
 * Created by: Patrik Lind, 17-03-2018
 */
public class MsPacmanID3 extends Controller<MOVE> {
    DecisionTree tree;
    TupleCreator tupleCreator;

	public MsPacmanID3() {
        tupleCreator = new TupleCreator();

        /* Load raw data to create a data structure and to test the classifier*/
        DataTable trainingSet = createDataSet(DataSaverLoader.LoadPacManData());
        DataTable testingSet = createDataSet(DataSaverLoader.LoadPacManData("testData.txt"));

        /* Create a decision tree based on the training dataTable */
        tree = new DecisionTree(trainingSet, new AttributeSelector());

        System.out.println("Accuracy of tree: " +new DecimalFormat("##0.00").format(tree.test(testingSet)*100)+"%");

        /* Visualise the tree */
        //Utilities.visualizeTreeDFS(tree);

    }

    private DataTable createDataSet(DataTuple[] rawData) {

        Tuple[] tuples = new Tuple[rawData.length];

        for (int i = 0; i < rawData.length; i++)
            tuples[i] = tupleCreator.createTuple(rawData[i]);

        DataTable dataTable = new DataTable();
        for (String attribute : tuples[0].attributes.keySet())
            dataTable.addAttributes(attribute);
        for (Tuple tuple : tuples)
            dataTable.addTuple(tuple);

        return  dataTable;
    }

    @Override
	public MOVE getMove(Game game, long timeDue) {

	    DataTuple tuple = new DataTuple(game, game.getPacmanLastMoveMade());
	    Tuple customTuple =  tupleCreator.createTuple(tuple);
	    MoveValue classifiedAs = (MoveValue) tree.classify(customTuple.attributes);

        return classifiedAs.getMove();

	}
}