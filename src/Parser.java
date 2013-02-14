import java.util.*;

public class Parser {
    private Lexer lexer;
    private Token token;
    Node root;
    Node current;
    int leftp;
    int rightp;

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
            this.parent = parent;
        }

    }

    public Parser(Lexer lexer )
    {
        leftp = 0;
        rightp = 0;
        this.lexer = lexer;
        token = this.lexer.nextToken();
        root = new Node(null);
        current = root;
    }

    private void expr()
    {
        Node newNode = new Node(current);
        current.children.add(newNode);
        current = newNode;
        term();

        if(token.gettCode() == TokenCode.RPAREN) {
            rightp++;
            if(leftp < rightp)
                error();
        }

        while ( token.gettCode() == TokenCode.PLUS ) {
            current.operators.add(token);
            token = lexer.nextToken();
            expr();
            return;
        }

        if(token.gettCode() == TokenCode.END) {
            return;
        }

        current = current.parent;
    }

    private void term()
    {
        Node newNode = new Node(current);
        current.children.add(newNode);
        current = newNode;

        factor();

        if(token.gettCode() == TokenCode.ERROR)
            error();

        while ( token.gettCode() == TokenCode.MULT) {
            current.operators.add(token);
            token = lexer.nextToken();
            term();
        }

        current = current.parent;
    }

    private void factor()
    {
        if (token.gettCode() == TokenCode.INT) {
            Node newNode = new Node(current);
            newNode.nums.add(token) ;
            current.children.add(newNode);
            token = lexer.nextToken();
        }

        else if (token.gettCode() == TokenCode.LPAREN) {
            leftp++;
            Node newNode = new Node(current);
            current.children.add(newNode);
            current = newNode;
            token = lexer.nextToken();

            expr();

            if ( token.gettCode() == TokenCode.RPAREN ) {
                current = newNode.parent;
                token = lexer.nextToken();
            }

            else {
                error();
            }
        } else {
            error();
        }
    }

    private void error()
    {
        print();
        System.out.println("Syntax error!");
        System.exit(1);
    }

    private void print(Node node)
    {
        if(node == null)
            return;

        for(Token it : node.nums) {
            System.out.println("PUSH " + it.getLexeme());
        }

        for(Node ch : node.children) {
            print(ch);
        }

        Collections.reverse(node.operators);

        for(Token it : node.operators ) {
            if(it.gettCode() == TokenCode.MULT)
                System.out.println("MULT");
            else if(it.gettCode() == TokenCode.PLUS)
                System.out.println("ADD");
        }
    }

    private void print()
    {
        print(root);
        System.out.println("PRINT");
    }

    public void parse()
    {
        expr();
        print();
    }
}
