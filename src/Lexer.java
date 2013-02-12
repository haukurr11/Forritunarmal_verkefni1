/*
Implement the class Lexer, a lexical analyzer.
It only needs a single public method, nextToken(),
which scans the standard input (stdin), looking for patterns
that match one of the tokens from 1).
Note that lexemes which are operators (PLUS, MULT) contain only one letter
whereas the lexemes for the token INT can contain more than one digit.  The
lexical analyzer returns a token with TokenCode=ERROR if some illegal lexeme
is found and TokenCode=END denoting the end of an expression.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lexer {
    String input;
    int location;
    public Lexer() {
        location = 0;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Token nextToken() {
        String s = input.substring(location,location+1);
        if( isInteger(s) )
        {
            StringBuilder sb = new StringBuilder();
            for(; location < input.length() && isInteger(input.substring(location,location+1));location++)
            {
               sb.append(input.substring(location,location+1));
            }

            System.out.println(sb.toString());
        }
        return null;
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
}
