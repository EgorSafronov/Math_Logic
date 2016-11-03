import java.util.HashMap;
import java.util.*;


/**
 * Created by egorsafronov on 00.10.16.
 */
public class BinaryOperation extends Node{

    Node left;
    Node right;
    int type;

    public BinaryOperation(Node left, Node right, int type) {
        this.left = left;
        this.right = right;
        this.type = type;
//        System.out.println(toString());
        str = left.str + type + "(" + right.str + ")";
    }

    @Override
    public boolean difEquals(Node node, HashMap<String, String> hash_map) {
        if (node == null || getClass() != node.getClass()) return false;
        return type == ((BinaryOperation) node).type &&
                left.difEquals(((BinaryOperation) node).left, hash_map) &&
                right.difEquals(((BinaryOperation) node).right, hash_map);
    }
}
