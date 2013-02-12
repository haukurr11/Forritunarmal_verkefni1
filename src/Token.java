enum TokenCode {
    INT,
    PLUS,
    MULT,
    LPAREN,
    RPAREN,
    ERROR,
    END
}
class Token{
    String lexeme;
    TokenCode tCode;
}
