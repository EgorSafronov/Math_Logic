import java.util.HashMap;
import java.util.Vector;
import java.util.*;

/**
 * Created by egorsafronov on 00.10.16.
 */
public class Axioms {

    private static Parser curParser = new Parser();
    private static String[] axioms = new String[] {
            "A->B->A",
            "(A->B)->(A->B->C)->(A->C)",
            "A->B->A&B",
            "A&B->A",
            "A&B->B",
            "A->A|B",
            "B->A|B",
            "(A->C)->(B->C)->(A|B->C)",
            "(A->B)->(A->!B)->!A",
            "!!A->A"}; 
    
    private static Vector<Node> parsedAxioms = new Vector<Node>() {{
        for (int i = 0; i < axioms.length; i++) {
            addElement(curParser.parseExpression(axioms[i]));
        }
    }};

    public static int isItAxiom(Node node) {
        for (int i = 0; i < parsedAxioms.size(); i++) {
            if (parsedAxioms.get(i).difEquals(node, new HashMap<String, String>())) return i + 1;
        }
        return -1;
    }
}

