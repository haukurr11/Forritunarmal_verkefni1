/*
Write a main class, Compiler, which only does the following:
 */
public class Compiler {
    public static void main(String args[])
    {
        Lexer myLexer = new Lexer();
        myLexer.nextToken();
        Parser myParser = new Parser(myLexer);
        myParser.parse();
    }
}
