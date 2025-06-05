package rei;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String source = String.join("\n",
               " x = (2 + 3) * (4 - 1)"
                ,"print x"

        );
        ReiLexer lexer = new ReiLexer(source);
        List<Token> tokens = lexer.tokenize();
        ReiParser parser = new ReiParser(tokens);
        List<Stmt> statements = parser.parse();
        ReiInterpreter interpreter = new ReiInterpreter();
        for (Stmt stmt : statements) {
            interpreter.execute(stmt);
        }
    }
}