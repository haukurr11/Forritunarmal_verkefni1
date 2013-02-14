import java.util.*;

/*
Implement the class Parser, the syntax analyzer (parser).
This should be a top-down recursive-descent parser for the grammar G above,
i.e. a program which recognizes valid infix expressions.
The output of the parser is the stack-based intermediate code S,
corresponding to the given expression, written to standard output (stdout).
If the expression is not in the language (or if an ERROR token is returned by the Lexer)
then the parser should output the error message "Syntax error!"
(at the point when the error is recognized) and immediately quit.
WTF
*/
public class Parser {
    private Lexer lexer;
    private Token token;
    Node root;
    Node current;
    private class Node {
        Node parent;
        public List<Token> operators;
        public List<Token> nums;
        public List<Node> children;
        public Node(Node parent)
        {
            operators = new ArrayList<Token>();
            nums = new ArrayList<Token>();
            children = new ArrayList<Node>();
            this.parent=parent;
        }
    }
    public Parser(Lexer lexer )
    {
        this.lexer=lexer;
        token = this.lexer.nextToken();
        root = new Node(null);
        current = root;
    }

    private void expr() {
        Node newNode = new Node(current);
        current.children.add(newNode);
        current = newNode;
        term();
        while ( token.gettCode() == TokenCode.PLUS ) {
            current.operators.add(token);
            token = lexer.nextToken();
            expr();
            return;
        }
        current=current.parent;
    }

    private void term() {
        Node newNode = new Node(current);
        current.children.add(newNode);
        current = newNode;
        factor(); /* parses the first factor */
        if(token.gettCode() == TokenCode.ERROR)
            error();
        if(token.gettCode() == TokenCode.END)
            print();
        while ( token.gettCode() == TokenCode.MULT) {
            current.operators.add(token);
            token = lexer.nextToken();
            term();
        }
        current=current.parent;
    }

    private void factor() {
        /* Decide what rule to use */
        if (token.gettCode() == TokenCode.INT)
        {
            Node newNode = new Node(current);
            newNode.nums.add(token) ;
            current.children.add(newNode);
            token = lexer.nextToken(); /* get the next token */
        }
        else if (token.gettCode() == TokenCode.LPAREN)
        {
            Node newNode = new Node(current);
            current.children.add(newNode);
            current = newNode;
            token = lexer.nextToken();
            expr();
            if (token.gettCode() == TokenCode.RPAREN) {
                    current = newNode.parent;
            }
            token = lexer.nextToken();
        }
        else
            error();
    }

    private void error() {
        print();
        System.out.println("Syntax error!");
        System.exit(1);
    }

    private void print(Node node) {
        if(node == null) return;
        for(Token it : node.nums)
        {
            System.out.println("PUSH " + it.getLexeme());
        }
        for(Node ch : node.children)
        {
            print(ch);
        }
        Collections.reverse(node.operators);
        for(Token it : node.operators )
        {
            if(it.gettCode() == TokenCode.MULT)
                System.out.println("MULT");
            else if(it.gettCode()==TokenCode.PLUS)
                System.out.println("ADD");
        }
    }

    private void print() {
        print(root);
        System.out.println("PRINT");
    }
    public void parse() {
        expr();
    }
}
