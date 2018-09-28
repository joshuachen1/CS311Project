/**
 * Path.java
 * @author Joshua Chen
 * Class: CS 311.01
 * Date Created: Jun 5, 2018
 */
public class Path {

    String input, end, toPop;
    String[] toPush;

    public Path(String input, String toPop, String end, String[] toPush) {
        this.input = input;
        this.end = end;

        this.toPop = toPop;

        this.toPush = new String[toPush.length];

        for (int i = 0; i < toPush.length; i++) {
            this.toPush[i] = toPush[toPush.length - i - 1];
        }
    }
}

