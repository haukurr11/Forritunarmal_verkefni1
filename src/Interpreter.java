import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Interpreter {

    public static void main(String args[])
    {
        interpret( getInput() );
    }

    private static void interpret(List<String> commands)
    {
        if(commands == null) return;

        Stack<Integer> numbers = new Stack<Integer>();

        for(String cmd : commands) {
            if(cmd.startsWith("PUSH ")) {
                String number = cmd.split("PUSH ")[1];

                if( isInteger(number) ) {
                    int num = Integer.parseInt(number);
                    numbers.push(num);
                } else {
                    System.out.println("Error: " + number + " is not an integer");
                }
            } else {
                if(cmd.equals("ADD")) {
                    if( numbers.size() >= 2 ) {
                        int a = numbers.pop();
                        int b = numbers.pop();
                        int c = a + b;
                        numbers.push(c);
                    }
                } else if(cmd.equals("MULT")) {
                    if( numbers.size() >= 2 ) {
                        int a = numbers.pop();
                        int b = numbers.pop();
                        int c = a * b;
                        numbers.push(c);
                    }
                } else if(cmd.equals("PRINT")) {
                    if(!numbers.empty())
                        System.out.println(numbers.pop());
                } else {
                    System.out.println("Error for operator: " + cmd.split(" ")[0]);
                }
            }
        }
    }

    private static List<String> getInput()
    {
        try {
            List<String> commands = new ArrayList<String>();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String input = br.readLine();
            while(input != null && !input.isEmpty()) {
                 commands.add(input);
                 input = br.readLine();
            }
                return commands;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
       return null;
    }

    private static boolean isInteger(String s)
    {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
