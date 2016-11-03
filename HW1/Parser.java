import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

import java.util.Collections;

/**
 * Created by egorsafronov on 00.10.16.
 */
public class Parser {

    private String expression;
    private int position;
    private int[] type = new int[]{0, 1, 2};
    private char[] symbol = new char[]{'>', '&', '|'};

    /*
    public Parser(int position, String expression) {
        this.position = position;
        this.expression = expression;
    }
*/

    boolean isOperation(char c) {
        if (expression.charAt(position) == '-') ++position;
        return expression.charAt(position) == c;
    }

    Node getBinaryOperation(int level) {
        if (type.length == level)
            return getNotBinaryOperation();
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(getBinaryOperation(/*++level*/ level + 1));
        while (position < expression.length()) {
            while (position < expression.length() && Character.isWhitespace(expression.charAt(position)) ) ++position;
            if (!isOperation(symbol[level]) || position == expression.length()) break;
            ++position;
            nodes.add(getBinaryOperation(level + 1));
        }
        if (type[level] == 0) {
            Collections.reverse(nodes);
            return nodes.stream().skip(1)
                    .reduce(nodes.get(0), (node, node2) -> new BinaryOperation(node2, node, type[level]));
        } else {
            return nodes.stream().skip(1)
                    .reduce(nodes.get(0), (node, node2) -> new BinaryOperation(node, node2, type[level]));
        }

    }

    Node getNotBinaryOperation() {
        while (Character.isWhitespace(expression.charAt(position)) && position < expression.length()) ++position;
        if (expression.charAt(position) == '!') {
            ++position;
            return new Negate(getNotBinaryOperation());
        } else if (expression.charAt(position) == '(') {
            ++position;
            Node temp = getBinaryOperation(0);
            while (position < expression.length() && Character.isWhitespace(expression.charAt(position))) ++position;
            ++position;//')'
            return temp;
        } else {
            int start_position = position;
            while (position < expression.length() && Character.isLetterOrDigit(expression.charAt(position))) ++position;
            return new Variable(expression.substring(start_position, position));
        }
    }


    Node parseExpression(String expression) {
        this.expression = expression;
        this.position = 0;
        return getBinaryOperation(0);
    }
}