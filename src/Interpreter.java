public class Interpreter {
    private Parser parser;

    public Interpreter(Parser par) {
        parser = par;
    }

    public int interpret()
    {
        AST tree = parser.parse();
        return postOrder(tree);
    }

    public int postOrder(AST tree)
    {
        if(tree.value != null)
            return Integer.parseInt(tree.value);

        int leftRes = 0;
        int rightRes = 0;

        if(tree.left != null) {
            leftRes = postOrder(tree.left);
        }

        if(tree.right != null) {
            rightRes = postOrder(tree.right);
        }

        if(tree.token.type == TokenType.PLUS) {
            return leftRes + rightRes;
        }
        else if(tree.token.type == TokenType.MINUS) {
            return leftRes - rightRes;
        }
        else if(tree.token.type == TokenType.MUL) {
            return leftRes * rightRes;
        }
        else if(tree.token.type == TokenType.DIV) {
            return leftRes / rightRes;
        }

        return 0;
    }
}
