package pacman.controllers.id3Controller;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

/**Utility methods for id3Controller
 *
 *TODO: build a grapgh to visualize the tree
 */
public class Utilities {

    public static final boolean LOG = true;
    public static final boolean ALL_GHOSTS = false;
    public static final boolean POWER_PILLS = false;
    private static Graph graph = new MultiGraph("Decision Tree ID3");
    private static int x=1;


/*
    public static void visualizeTreeDFS(pacman.controllers.id3.TreeNode tree) {

        graph.setStrict(false);
        graph.setAutoCreate(true);
        dfs(tree);
        Viewer v = graph.display();
        v.disableAutoLayout();
    }

    private static void dfs(pacman.controllers.id3.TreeNode root) {
        if (root==null){
            return;
        }

        //process root
        graph.addNode(root.label+"-"+root.id);
        Node n = graph.getNode(root.label+"-"+root.id);

        if(root.parent==null)
            n.setAttribute("ui.label","<ROOT>"+root.label);

        else if(root.isLeaf)
            n.setAttribute("ui.label", "<LEAF>"+root.label);
        else
            n.setAttribute("ui.label",root.label);


        if(root.parent==null){
            root.xPos=x;
        }else{
            x = root.parent.xPos;
            int offset =  root.parent.children.size()/2;
            int indexOfNode = root.parent.children.indexOf(root);
            x += -offset+indexOfNode-root.children.size();
            root.xPos=x;
            //kolla -antal barn /-> offset

        }
        if(root.parent!=null){
            graph.addEdge(
                    root.id+"-"+root.parent.id,
                    root.label+"-"+root.id,
                    root.parent.label+"-"+root.parent.id);
            graph.getEdge(root.id+"-"+root.parent.id).setAttribute("ui.label",root.edge);
        }


        n.setAttribute("xyz",x,-root.depthOfNode,0);

        ArrayList<pacman.controllers.id3Controller.DecisionTree.TreeNode> children = root.children;
        for(pacman.controllers.id3.TreeNode next : children){

           dfs(next);

        }
    }*/

   /* public static void visualizeTreeBFS(pacman.controllers.id3Controller.DecisionTree.TreeNode tree) {
        LinkedList<pacman.controllers.id3Controller.DecisionTree.TreeNode> stack = new LinkedList<>();
        LinkedList<pacman.controllers.id3Controller.DecisionTree.TreeNode> nodesToAddToGraph = new LinkedList<>();

        //BFS add to a list
        stack.add(tree);
        int depth =0;
        while (!stack.isEmpty()){
            pacman.controllers.id3Controller.DecisionTree.TreeNode currentNode = stack.removeFirst();
            nodesToAddToGraph.addLast(currentNode);
            for(pacman.controllers.id3Controller.DecisionTree.TreeNode child : currentNode.children){
                stack.addLast(child);

            }

        }

        int edgeCount = 0;
        Graph graph = new MultiGraph("Decision Tree ID3");

        graph.setStrict(false);
        graph.setAutoCreate(true);

        //Coords
        int x=1;
        //Create nodes
        for(int i = 0;i< nodesToAddToGraph.size();i++){
            pacman.controllers.id3Controller.DecisionTree.TreeNode node = nodesToAddToGraph.get(i);

            graph.addNode(node.label+"-"+node.id);
            Node n = graph.getNode(i);

            if(node.parent==null)
                n.setAttribute("ui.label","<ROOT>"+node.label);

            else if(node.isLeaf)
                n.setAttribute("ui.label", "<LEAF>"+node.label);
            else
                n.setAttribute("ui.label",node.label);


            if(node.parent==null){
                node.xPos=x;
            }else{
                x = node.parent.xPos;
                int offset =  node.parent.children.size()/2;
                int indexOfNode = node.parent.children.indexOf(node);
                x += -offset+indexOfNode;
                node.xPos=x;
                //kolla -antal barn /-> offset

            }



            n.setAttribute("xyz",x,-node.depthOfNode,0);

        }

        //Create edges
        for(DecisionTree.TreeNode node: nodesToAddToGraph ){
            if(node.parent!=null){
                graph.addEdge(
                        node.edge+"-"+edgeCount,
                        node.label+"-"+node.id,
                        node.parent.label+"-"+node.parent.id);
                graph.getEdge(node.edge+"-"+edgeCount).setAttribute("ui.label",node.edge);
            }
            edgeCount++;
        }

         Viewer v = graph.display();
        v.disableAutoLayout();
    }*/
}
