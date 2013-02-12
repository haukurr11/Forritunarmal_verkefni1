/*
Write a main class, Compiler, which only does the following:
 */
public class Compiler {
    public void main(String args[])
    {
        Lexer myLexer = new Lexer();
        Parser myParser = new Parser(myLexer);
        myParser.parse();
    }
}
