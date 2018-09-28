import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * DPDA.java
 * @author Joshua Chen
 * Class: CS 311.01
 * Date Created: Jun 5, 2018
 */
public class DPDA {

    private char[] alphabet;
    private int numStates;
    private boolean[] finalStates;
    private Map<Integer, ArrayList<Path>> transitions;

    public DPDA(int numberOfStates) {
        numStates = numberOfStates;
        finalStates = new boolean[numStates + 1];
        transitions = new HashMap<>();
    }

    public void setFinalStates (String[] states) {
        for (String state : states) {
            if (!state.equals("-1")) {
                finalStates[Integer.parseInt(state)] = true;
            }
        }
    }

    public void setAlphabet (String alpha) {
        alphabet = alpha.toCharArray();
    }

    public void addTransitions(String[] inputPath) {

        int key = Integer.parseInt(inputPath[0]);

        // If key exists
        if (transitions.containsKey(key)){
            ArrayList<Path> temp = transitions.get(key);
            String[] toPush = inputPath[4].split("");
            temp.add(new Path(inputPath[1], inputPath[2], inputPath[3], toPush));
            transitions.replace(key, temp);
        }
        // If key does not exist
        else {
            ArrayList<Path> temp = new ArrayList<>();
            String[] toPush = inputPath[4].split("");
            temp.add(new Path(inputPath[1], inputPath[2], inputPath[3], toPush));
            transitions.put(key, temp);
        }

    }

    public ArrayList<Path> getPaths(int currentState) {
        return transitions.get(currentState);
    }

    public boolean isFinalState(int state) {
        return finalStates[state];
    }
}

