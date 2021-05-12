public class Lexer {
    // get token one by one
    // represents stream of tokens
    int pos;
    String expression;
    Character currentChar;

    public Lexer(String exp)
    {
        pos = 0;
        expression = exp;
        currentChar = exp.charAt(pos);
    }

    public int getPos()
    {
        return pos;
    }

    public void throwError()
    {
        try {
            throw new Exception("Invalid Expression");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void advance()
    {
        pos++;
        if(pos > expression.length()-1)
            currentChar = null;
        else
            currentChar = expression.charAt(pos);
    }
    public void skipWhiteSpace()
    {
        while(Character.isWhitespace(currentChar))
            advance();
    }

    public String getIntegerString()
    {
        StringBuilder sb = new StringBuilder();
        while(currentChar != null && Character.isDigit(currentChar))
        {
            sb.append(currentChar);
            advance();
        }

        return sb.toString();
    }

    public Token getNextToken() {
        while(currentChar != null)
        {
            if(Character.isWhitespace(currentChar))
                skipWhiteSpace();
            else if(Character.isDigit(currentChar))
                return new Token(TokenType.INTEGER, getIntegerString());
            else if(currentChar == '+') {
                advance();
                return new Token(TokenType.PLUS, "+");
            }
            else if(currentChar == '-') {
                advance();
                return new Token(TokenType.MINUS, "-");
            }
            else if(currentChar == '*') {
                advance();
                return new Token(TokenType.MUL, "*");
            }
            else if(currentChar == '/') {
                advance();
                return new Token(TokenType.DIV, "/");
            }
            else if(currentChar == '(') {
                advance();
                return new Token(TokenType.LPAREN, "(");
            }
            else if(currentChar == ')') {
                advance();
                return new Token(TokenType.RPAREN, ")");
            }
            else
            {
                throwError();
                break;
            }
        }

        return new Token(TokenType.EOF, null);
    }
}
