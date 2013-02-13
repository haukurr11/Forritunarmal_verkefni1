import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

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
    private Lexer lexer;
    private Token token;
    Stack<Token> operators;
    Stack<Token> nums;
    public Parser(Lexer lexer )
    {
        this.lexer=lexer;
        token = this.lexer.nextToken();
        operators= new Stack<Token>();
        nums = new Stack<Token>();
    }

    public void expr(int mult) {
        term(mult);
        while ( token.gettCode() == TokenCode.PLUS ) {
            if( mult == 1 ) {
                print();
                operators = new Stack<Token>();
            nums = new Stack<Token>();
            }
            operators.push(token);
            token = lexer.nextToken();
            term(mult);

        }
    }

    public void term(int mult) {
        factor(mult); /* parses the first factor */
        while ( token.gettCode() == TokenCode.MULT) {
            operators.push(token);
            token = lexer.nextToken();
            factor(1);
        }
    }

    public void factor(int mult) {
        /* Decide what rule to use */
        if (token.gettCode() == TokenCode.INT)
        {
            nums.push(token);
            token = lexer.nextToken(); /* get the next token */
        }
        else if (token.gettCode() == TokenCode.LPAREN)
        {
            token = lexer.nextToken();
            expr(mult);
            if (token.gettCode() == TokenCode.RPAREN) {
                token = lexer.nextToken();
                if(token.gettCode() == TokenCode.MULT || (token.gettCode()==TokenCode.PLUS && mult==1))
                {
                   print();
                   operators = new Stack<Token>();
                   nums = new Stack<Token>();
                }
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

        for(Token it : nums)
        {
            System.out.println("PUSH " + it.getLexeme());

        }
        Collections.reverse(operators);
        for(Token it : operators )
        {
            if(it.gettCode() == TokenCode.MULT)
                System.out.println("MULT");
            else if(it.gettCode()==TokenCode.PLUS)
                System.out.println("ADD");
        }
    }

    public void parse() {
        expr(0);
       // end();
        print();
        System.out.println("PRINT");
    }
}
