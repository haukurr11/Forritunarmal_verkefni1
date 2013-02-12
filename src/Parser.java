/*
Implement the class Parser, the syntax analyzer (parser).
This should be a top-down recursive-descent parser for the grammar G above,
i.e. a program which recognizes valid infix expressions.
The output of the parser is the stack-based intermediate code S,
corresponding to the given expression, written to standard output (stdout).
If the expression is not in the language (or if an ERROR token is returned by the Lexer)
then the parser should output the error message “Syntax error!”
(at the point when the error is recognized) and immediately quit.
 */
public class Parser {
    private Lexer lexer;
    private Token token;
    public Parser(Lexer lexer )
    {
        this.lexer=lexer;
    }
    public void parse() {

    }
}
