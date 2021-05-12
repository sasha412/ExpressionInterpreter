public class Token {
    // Building block of evaluator
    // Token can be Integer or Operator.
    TokenType type;
    String value;

    public Token(TokenType t, String v)
    {
        type = t;
        value = v;
    }
}


