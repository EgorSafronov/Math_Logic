import java.util.HashMap;
import java.util.Objects;
import java.util.*;


/**
 * Created by egorsafronov on 00.10.16.
 */
public abstract class Node {

    String str;

    public Node() {}

    public Node(String s) {
        this.str = s;
    }

    public int hashCode() {
        return str.hashCode();
    }

    public String toString() {
        return str;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass().getName() != "Node") {
            return false;
        }
        return str.equals(((Node)obj).str);
    }

    abstract public boolean difEquals(Node node, HashMap<String, String> hash_map);
}
