import java.util.HashMap;
import java.util.*;


/**
 * Created by egorsafronov on 00.10.16.
 */
public class Negate extends Node{

    Node curr;

    public Negate(Node curr) {
        super();
        this.curr = curr;
        str = "!" + str;
    }

    @Override
    public boolean difEquals(Node node, HashMap<String, String> hash_map) {
        if (node == null || getClass() != node.getClass()) return false;
        return curr.difEquals(((Negate)node).curr, hash_map);
    }
}
