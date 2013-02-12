public class Interpreter {
    public void main(String args[])
    {
        Lexer myLexer = new Lexer();
        Parser myParser = new Parser(myLexer);
        myParser.parse();
    }
}
