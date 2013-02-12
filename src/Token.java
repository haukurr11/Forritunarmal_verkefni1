/*
   Write the class Token,  which contains both a lexeme and a token code
 */
enum TokenCode {
    INT,
    PLUS,
    MULT,
    LPAREN,
    RPAREN,
    ERROR,
    END
};
public class Token{
    public TokenCode tCode;
    public String lexeme;

    public Token(TokenCode tCode, String lexeme)
    {
        this.tCode=tCode;
        this.lexeme = lexeme;
    }
    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }


    public TokenCode gettCode() {
        return tCode;
    }

    public void settCode(TokenCode tCode) {
        this.tCode = tCode;
    }

}
