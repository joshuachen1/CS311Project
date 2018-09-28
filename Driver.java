import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

/**
 * Driver.java
 * @author Joshua Chen
 * Class: CS 311.01
 * Date Created: Jun 5, 2018
 */
public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        DPDA pda;
        Stack<String> stack = new Stack<>();

        stack.push("$");

        System.out.print("Number of States: ");
        pda = new DPDA(scan.nextInt());
        scan.nextLine();

        System.out.print("Alphabet: ");
        pda.setAlphabet(scan.nextLine());

        String[] transition = null;

        while (transition == null || !transition[0].equals("-1")) {
            System.out.print("Transition: ");
            transition = scan.nextLine().split("\\s+");
            if (transition[0].equals("-1")) {
                break;
            }
            pda.addTransitions(transition);
        }

        System.out.print("Final States: ");
        String[] finalStates = scan.nextLine().split("\\s+");
        pda.setFinalStates(finalStates);

        boolean validChar = false;
        int currentState = 0;

        while(true) {
            ArrayList<Path> currentPaths = pda.getPaths(currentState);

            System.out.print("Current status ");
            System.out.print(currentState);
            System.out.print(":");
            printStack(stack);
            System.out.print(", Enter input: ");

            String currentChar = scan.next();

            // If end of String and at final state and only $ remains on stack
            if (currentChar.equals(".") && stack.peek().equals("$") && pda.isFinalState(currentState)) {
                break;
            }

            validChar = false;

            for (int i = 0; i < currentPaths.size(); i++) {
                if(currentChar.equals(currentPaths.get(i).input) && currentPaths.get(i).toPop.equals(stack.peek())) {
                    // Dont pop if empty string
                    if (!currentPaths.get(i).toPop.equals(".")) {
                        stack.pop();
                    }
                    for (int j = 0; j < currentPaths.get(i).toPush.length; j++) {
                        // Dont push if empty string
                        if (!currentPaths.get(i).toPush[j].equals(".")) {
                            stack.push(currentPaths.get(i).toPush[j]);
                        }
                    }
                    validChar = true;
                    currentState = Integer.parseInt(currentPaths.get(i).end);
                    break;
                }
            }

            if (!validChar) {
                break;
            }
        }

        if (!validChar){
            System.out.println("String Rejected.");
        }
        else{
            System.out.println("String Accepted.");
        }
    }

    // Prints the stack from top to bottom
    public static void printStack(Stack<String> stack) {
        String[] temp = new String[stack.size()];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = stack.pop();
            System.out.print(temp[i]);
        }
        for (int i = temp.length - 1; i >= 0; i--) {
            stack.push(temp[i]);
        }
    }
}

