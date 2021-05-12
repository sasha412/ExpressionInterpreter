class AST {
    public AST left;
    public AST right;
    public Token token;
    public String value;
}

class BinOp extends AST
{
    public BinOp(AST l, Token op, AST r)
    {
        left = l;
        right = r;
        token = op;
    }
}

class Num extends AST
{
    public Num(Token t)
    {
        token = t;
        value = t.value;
    }
}
public class Parser {
    private Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lex)
    {
        lexer = lex;
        currentToken = lexer.getNextToken();
    }

    public void throwError()
    {
        try {
            throw new Exception("Invalid Expression at "+ (lexer.getPos()-1));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void eatToken(TokenType tokenType) {
        if(currentToken.type.equals(tokenType))
            currentToken = lexer.getNextToken();
        else throwError();
    }

    public AST factor() {
        Token token = currentToken;

        if(token.type == TokenType.INTEGER)
        {
            eatToken(TokenType.INTEGER);
            return new Num(token);
        }
        else if(token.type == TokenType.LPAREN)
        {
            eatToken(TokenType.LPAREN);
            AST node = expr();
            eatToken(TokenType.RPAREN);
            return node;
        }
        else
        {
            throwError();
        }

        return new AST();
    }

    public AST term()
    {
        AST result = factor();
        while(currentToken.type.equals(TokenType.MUL)
                || currentToken.type.equals(TokenType.DIV))
        {
            var token = currentToken;
            if(token.type.equals(TokenType.MUL))
            {
                eatToken(token.type);
            }
            else if(token.type.equals(TokenType.DIV))
            {
                eatToken(token.type);
            }

            result = new BinOp(result, token, factor());
        }

        return result;
    }

    public AST expr() {
        AST result = term();

        while(currentToken.type.equals(TokenType.PLUS)
                || currentToken.type.equals(TokenType.MINUS))
        {
            var token = currentToken;
            if(token.type.equals(TokenType.PLUS))
            {
                eatToken(token.type);
            }
            else if(token.type.equals(TokenType.MINUS))
            {
                eatToken(token.type);
            }

            result = new BinOp(result, token, term());
        }

        return result;
    }

    public AST parse() {
        return expr();
    }
}
