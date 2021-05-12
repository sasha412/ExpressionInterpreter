import java.util.Scanner;

public class ExpressionInterpreter {
    public static void main(String[] args) {

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter expression: ");
            String expression = scanner.nextLine();

            var lexer = new Lexer(expression);
            var parser = new Parser(lexer);
            var interpreter = new Interpreter(parser);
            var result = interpreter.interpret();

            System.out.println(result);
        }
    }
}
