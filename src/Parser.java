import java.util.ArrayDeque;
import java.util.Queue;

/*
Implement the class Parser, the syntax analyzer (parser).
This should be a top-down recursive-descent parser for the grammar G above,
i.e. a program which recognizes valid infix expressions.
The output of the parser is the stack-based intermediate code S,
corresponding to the given expression, written to standard output (stdout).
If the expression is not in the language (or if an ERROR token is returned by the Lexer)
then the parser should output the error message "Syntax error!"
(at the point when the error is recognized) and immediately quit.
*/
public class Parser {
    private Queue<Token> TheQ;
    private Lexer lexer;
    private Token token;
    public Parser(Lexer lexer )
    {
        TheQ = new ArrayDeque<Token>();
        this.lexer=lexer;
        token = this.lexer.nextToken();
        TheQ.add(token);
    }

    public void expr() {

        term();
        while ( token.gettCode() == TokenCode.PLUS ) {
            token = lexer.nextToken();
            TheQ.add(token);
            term();
        }
    }

    public void term() {
        factor(); /* parses the first factor */
        while ( token.gettCode() == TokenCode.MULT) {
            token = lexer.nextToken();
            TheQ.add(token);
            factor();
        }
    }

    public void factor() {
        /* Decide what rule to use */
        if (token.gettCode() == TokenCode.INT)
        {
            token = lexer.nextToken(); /* get the next token */
            TheQ.add(token);
        }
        else if (token.gettCode() == TokenCode.LPAREN)
        {
            token = lexer.nextToken();
            TheQ.add(token);
            expr();
            if (token.gettCode() == TokenCode.RPAREN) {
                token = lexer.nextToken();
                TheQ.add(token);
            }
        }


        else error(); /* neither and id nor a left parenthesis */
    }

    public void error() {
        System.out.println("Syntax error!");
        System.exit(1);
    }

    public void end() {
        System.out.println("End!");

    }

    public void print() {
          Token it = TheQ.poll();
          while (it != null)
          {
               System.out.println(it.gettCode());
               it = TheQ.poll();
          }
    }

    public void parse() {
        expr();
       // end();
        print();
    }
}
