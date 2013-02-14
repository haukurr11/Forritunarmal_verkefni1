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
           System.out.println(results);
    }

    private static int calc(List<String> commands){
        Stack<Integer> numbers = new Stack<Integer>();
        int results = 0;
        int register = 0;

        return 0;
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
}
