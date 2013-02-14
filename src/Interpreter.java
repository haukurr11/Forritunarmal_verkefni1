import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Interpreter {

    public static void main(String args[]) throws IOException {
           List<String> commands = getInput();
           int results = calc(commands);

    }

    private static int calc(List<String> commands){
        Stack<Integer> numbers = new Stack<Integer>();
        int results = 0;
        int register = 0;
        for(String cmd : commands) {
            if(cmd.startsWith("PUSH ")) {
                String number = cmd.split("PUSH ")[1];
                if( isInteger(number) )
                {
                    int num = Integer.parseInt(number);
                    numbers.push(num);
                }
                else {
                    error();
                }
            }
            else
            {
                if(cmd.equals("ADD"))
                {
                   int a = numbers.pop();
                   int b = numbers.pop();
                   int c = a+b;
                   numbers.push(c);
                }
                else if(cmd.equals("MULT"))
                {
                    int a = numbers.pop();
                    int b = numbers.pop();
                    int c = a*b;
                    numbers.push(c);
                }
                else if(cmd.equals("PRINT"))
                {
                    System.out.println(numbers.pop());
                }
                else {
                    error();
                }
            }
        }
        return results;
    }
    private static List<String> getInput() throws IOException {
        List<String> commands = new ArrayList<String>();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        while(input != null && !input.isEmpty())
        {
            commands.add(input);
            input = br.readLine();
        }
        return commands;
    }
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    private static void error() {
        System.out.println("SYNTAX ERROR");
        System.exit(1);
    }
}
