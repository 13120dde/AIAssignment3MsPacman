package pacman.controllers.id3Controller.learning;

import dataRecording.DataTuple;
import pacman.controllers.id3Controller.generic.data.Tuple;
import pacman.controllers.id3Controller.generic.data.values.StringValue;
import pacman.game.Constants;

/**Creates custom tuples that are further used to build the data table and to classify a move during game play.
 *
 * Created by: Patrik Lind, 17-03-2018
 */
public class TupleCreator {

    /**Creates a tuple from raw gameplay data
     *
     * @param inputTuple : DataTuple
     * @return tuple : Tuple
     */
    public Tuple createTuple(DataTuple inputTuple) {
        Tuple tuple = new Tuple();
        tuple.classifiedAs = new MoveValue(inputTuple.DirectionChosen);

        /* tuple.attributes.put("blinky_dist", new StringValue(distance(inputTuple.blinkyDist)));
        tuple.attributes.put("inky_dist", new StringValue(distance(inputTuple.inkyDist)));
        tuple.attributes.put("pinky_dist", new StringValue(distance(inputTuple.pinkyDist)));
        tuple.attributes.put("sue_dist", new StringValue(distance(inputTuple.sueDist)));

        tuple.attributes.put("blinky_edible", new BooleanValue(inputTuple.isBlinkyEdible));
        tuple.attributes.put("inky_edible", new BooleanValue(inputTuple.isInkyEdible));
        tuple.attributes.put("pinky_edible", new BooleanValue(inputTuple.isPinkyEdible));
        tuple.attributes.put("sue_edible", new BooleanValue(inputTuple.isSueEdible));
        tuple.attributes.put("blinky_dir", new StringValue(inputTuple.blinkyDir.toString()));
        tuple.attributes.put("inky_dir", new StringValue(inputTuple.inkyDir.toString()));
        tuple.attributes.put("pinky_dir", new StringValue(inputTuple.pinkyDir.toString()));
        tuple.attributes.put("sue_dir", new StringValue(inputTuple.sueDir.toString())); */

        /* Find closest ghost*/
        Constants.MOVE closestGhostDir = inputTuple.blinkyDir;
        int closestGhost = inputTuple.blinkyDist;
        if (inputTuple.inkyDist < closestGhost) {
            closestGhost = inputTuple.inkyDist;
            closestGhostDir = inputTuple.inkyDir;
        }
        if (inputTuple.pinkyDist < closestGhost) {
            closestGhost = inputTuple.pinkyDist;
            closestGhostDir = inputTuple.pinkyDir;
        }
        if (inputTuple.sueDist < closestGhost) {
            closestGhost = inputTuple.sueDist;
            closestGhostDir = inputTuple.sueDir;
        }

        /* Put the values about the ghost in the tuple */
        tuple.attributes.put("closest_ghost_direction", new StringValue(closestGhostDir.toString()));
        tuple.attributes.put("closest_ghost_distance", new StringValue(discreteDistanceGhost(closestGhost)));

        /* tuple.attributes.put("powerpill_dist", new StringValue(distance(inputTuple.powerpillDist)));
        tuple.attributes.put("powerpill_move", new StringValue(inputTuple.powerpillMove.toString())); */

        /* Put the values about the closest ghost in the tuple */
        tuple.attributes.put("pill_distance", new StringValue(discreteDistancePill(inputTuple.pillDist)));
        tuple.attributes.put("move_to_pill", new StringValue(inputTuple.pillMove.toString()));

        return tuple;
    }

    /**Parses continious values into discrete values for ghost distance
     *
     * @param distance : int
     * @return discreteValue : String
     */
    private String discreteDistanceGhost(int distance)
    {
        if (distance < 15)
            return "very_low";
        else if (distance < 30)
            return "low";
        else if (distance < 60)
            return "medium";
        else if (distance < 90)
            return "high";
        else
            return "very_high";
    }

    /**Parses continious values into discrete values for pill distance
     * @param distance : int
     * @return discreteValue : String
     */
    private String discreteDistancePill(int distance)
    {
        if (distance < 10)
            return "very_low";
        else if (distance < 20)
            return "low";
        else if (distance < 40)
            return "medium";
        else
            return "high";
    }
}
