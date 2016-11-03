import java.util.HashMap;
import java.util.*;


/**
 * Created by egorsafronov on 00.10.16.
 */
public class Variable extends Node {

    public Variable(String s) {
        super(s);
    }

    @Override
    public boolean difEquals(Node node, HashMap<String, String> hash_map) {
        if (node == null) return false;
        if (hash_map.containsKey(str)) {
            return hash_map.get(str).equals(node.toString());
        } else {
            hash_map.put(str, node.toString());
            return true;
        }
    }


}
