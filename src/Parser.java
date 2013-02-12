import java.util.regex.PatternSyntaxException;

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
    public Parser(Lexer lexer )
    {
        this.lexer=lexer;
        token = lexer.nextToken();
    }

    public void expr() {
        while ( token.gettCode() == TokenCode.PLUS ) {
            token = lexer.nextToken();
            term();
        }
    }

    public void term() {
        factor(); /* parses the first factor */
        while ( token.gettCode() == TokenCode.MULT) {
            token = lexer.nextToken();
            factor();
        }
    }

    public void factor() {
        /* Decide what rule to use */
        if (token.gettCode() == TokenCode.INT)
            token = lexer.nextToken(); /* get the next token */
        else if (token.gettCode() == TokenCode.LPAREN)
        {
            token = lexer.nextToken();
            expr();
            if (token.gettCode() == TokenCode.RPAREN)
                token = lexer.nextToken();
        }
        else error(); /* neither and id nor a left parenthesis */
    }

    public void error() {
        System.out.println("Syntax error!");
        System.exit(1);
    }

    public void parse() {
        expr();
    }
}
