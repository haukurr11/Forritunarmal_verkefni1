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
    public Token nextToken() {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String s = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
