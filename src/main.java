import javafx.util.Pair;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by egorsafronov on 00.10.16.
 */
public class main {

    static BufferedReader in;
    static PrintWriter out;
    static Axioms curAxioms /*= new Axioms()*/;
    static Parser curParser = new Parser();
    static HashMap<String, Integer> hypothesis;
    static HashMap<String, Integer> stringsInProof = new HashMap<String, Integer>();
    static HashMap<String, ArrayList<Pair>> rightExpressions = new HashMap<String, ArrayList<Pair>>();

    public static boolean isHypothesis(String str) {
        if (hypothesis.containsKey(str)) {
            out.println(" (Предп. " + hypothesis.get(str) + ")");
            return true;
        }
        return false;
    }

    public static boolean isAxiom(Node node) {
        int temp_index = curAxioms.isItAxiom(node);
        if (temp_index == -1) return false;
        out.println(" (Сх. акс. " + temp_index + ")");
        return true;
    }

    public static boolean isMP(String str) {
        if (rightExpressions.containsKey(str)) {
//            boolean flag = false;
            ArrayList<Pair> temp_array = rightExpressions.get(str);
            for (int i = 0; i < temp_array.size(); i++) {
                Pair pair = temp_array.get(i);
                if (stringsInProof.containsKey(pair.getKey())) {
                    out.println(" (M.P. " + stringsInProof.get(pair.getKey()) + ", " + pair.getValue() + ")");
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new FileReader("src/input.txt"));
        out = new PrintWriter(new File("src/output.txt"));

        hypothesis = new HashMap<>();

        int number = 0;
        String first_line = in.readLine();
        String temp;
        StringTokenizer st = new StringTokenizer(first_line, ",| ");
        while (st.hasMoreTokens()) {
            temp = st.nextToken();
            if (temp.startsWith("-")) break;
            ++number;
            Node node = curParser.parseExpression(temp);
            hypothesis.put(node.toString(), number);
        }
        out.println(first_line);

        number = 0;
        while ((temp = in.readLine()) != null) {
            ++number;
            out.print("(" + (number) + ") " + temp);
            Node node = curParser.parseExpression(temp);
            if (!isHypothesis(node.toString()) && !isAxiom(node) && !isMP(node.toString())) {
                out.println(" (Не доказано)");
            }
            stringsInProof.put(node.toString(), number);
            if (node instanceof BinaryOperation && ((BinaryOperation)node).type == 0) {
                if (!rightExpressions.containsKey(((BinaryOperation) node).right.toString()))
                    rightExpressions.put(((BinaryOperation) node).right.toString(), new ArrayList<Pair>());
//                ArrayList<Pair> temp_array = new ArrayList<>();
//                temp_array = rightExpressions.get(((BinaryOperation) node).right.toString());
//                temp_array.add(new Pair(((BinaryOperation)  node).left.toString(), number));
//                rightExpressions.put(((BinaryOperation) node).right.toString(), temp_array);
                rightExpressions.get(((BinaryOperation) node).right.toString())
                        .add(new Pair(((BinaryOperation) node).left.toString(), number));
            }

        }

        in.close();
        out.close();
    }
}
