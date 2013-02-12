/*
Implement the (main) class, Interpreter.
This class reads S, the stack-based intermediate code,
from standard input (stdin), interprets it and executes it “on-the-fly”.
The result is written to standard output.
The main program of the Interpreter should correspond
to the general function of an interpreter, i.e. the fetch-decode-execute cycle.
If the Interpreter encounters an invalid operator, or if there are not sufficiently
many arguments for an operator on the stack, then it should write out the error
message: “Error for operator:  nameOfOperator”
(where nameOfOperator is the operator in question) and immediately quit.
 */
public class Interpreter {
    public void main(String args[])
    {
        Lexer myLexer = new Lexer();
        Parser myParser = new Parser(myLexer);
        myParser.parse();
    }
}
